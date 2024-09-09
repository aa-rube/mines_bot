package app.bot.handler;

import app.bot.config.BotConfig;
import app.bot.db.model.PirateUser;
import app.bot.db.service.PirateUserService;
import app.bot.handler.state.State;
import app.bot.handler.state.StateKeeper;
import app.bot.messaging.data.Message;
import app.bot.util.CheckSubscribeToChannel;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;

@Service
public class TextMsgHandler {

    private final CheckSubscribeToChannel checkSubscribeToChannel;
    private final PirateUserService pirateUserService;
    private final Message message;
    private final Long adminChatId;

    public TextMsgHandler(CheckSubscribeToChannel checkSubscribeToChannel,
                          PirateUserService pirateUserService,
                          Message message,
                          BotConfig botConfig) {
        this.checkSubscribeToChannel = checkSubscribeToChannel;
        this.pirateUserService = pirateUserService;
        this.message = message;
        this.adminChatId = botConfig.getAdminChatId();
    }

    public void updateHandler(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        int msgId = update.getMessage().getMessageId();

        System.out.println("msgId: " + msgId + ", chatId: " + chatId + ", text: " + text + ", time: " + LocalDateTime.now());

        if (text != null && text.equals("/start")) {
            pirateUserService.save(new PirateUser(update, true));
            boolean hsSubscription = checkSubscribeToChannel.checkChannelSubscription(chatId);

            message.startMessage(update, chatId, hsSubscription);
            StateKeeper.removeState(chatId);
            return;
        }

        if (text != null && StateKeeper.containsKey(chatId)
                && StateKeeper.getState(chatId).equals(State.REGISTRATION)) {

            boolean userIdIsCorrect = text.matches("\\d{8}");
            message.registrationComplete(chatId, userIdIsCorrect);
            if (userIdIsCorrect) StateKeeper.removeState(chatId);
        }

        if (text != null && text.equals("/admin") && chatId.equals(adminChatId)){
            message.startAdmin(chatId);
            return;
        }

        if (chatId.equals(adminChatId) && StateKeeper.containsKey(chatId)
                && StateKeeper.getState(chatId).equals(State.SPAM)) {
            StateKeeper.putSpamMessage(chatId, update.getMessage());
            message.checkTheSpamMessage(chatId);
        }
    }
}