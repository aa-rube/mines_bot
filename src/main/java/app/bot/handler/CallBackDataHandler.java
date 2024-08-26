package app.bot.handler;

import app.bot.db.model.MinerUser;
import app.bot.db.service.MinerUserService;
import app.bot.handler.state.State;
import app.bot.handler.state.StateKeeper;
import app.bot.mailing.MailingService;
import app.bot.messaging.data.Message;
import app.bot.util.CheckSubscribeToChannel;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CallBackDataHandler {

    private final Message message;
    private final CheckSubscribeToChannel checkSubscribeToChannel;
    private final MinerUserService minerUserService;
    private final ExecutorService executorService;
    private final MailingService mailingService;

    public CallBackDataHandler(Message message,
                               CheckSubscribeToChannel checkSubscribeToChannel,
                               MinerUserService minerUserService,
                               MailingService mailingService) {
        this.message = message;
        this.checkSubscribeToChannel = checkSubscribeToChannel;
        this.minerUserService = minerUserService;
        this.mailingService = mailingService;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public void updateHandler(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String data = update.getCallbackQuery().getData();
        int msgId = update.getCallbackQuery().getMessage().getMessageId();

        executorService.submit(() -> minerUserService.updateUserData(chatId, update, false));

        System.out.println("msgId: " + msgId + ", chatId: " + chatId + ", data: " + data + ", time: " + LocalDateTime.now());

        if (data.contains("_mina")) {
            message.delete(chatId, msgId);
            message.prediction(chatId,update.getCallbackQuery().getFrom().getUserName(), data);
        }

        if (data.equals("registration") || data.equals("registration1")) {
            StateKeeper.stateMap.put(chatId, State.REGISTRATION);
            message.delete(chatId, msgId);
            message.firstRegistrationMessage(chatId);
            return;
        }

        if (data.equals("proverka")) {
            boolean hsSubscription = checkSubscribeToChannel.checkChannelSubscription(chatId);
            if (hsSubscription) message.delete(chatId, msgId);
            message.startMessage(update, chatId, hsSubscription);
            StateKeeper.removeState(chatId);
            return;
        }

        if (data.contains("cancel")) {
            if (data.contains("1")) {
                message.delete(chatId, msgId);
                message.registrationComplete(chatId, true);
                return;
            }
            message.delete(chatId, msgId);
            message.startMessage(update, chatId, checkSubscribeToChannel.checkChannelSubscription(chatId));
            return;
        }

        if (data.contains("manual")) {
            message.delete(chatId, msgId);
            message.manualMessage(chatId, data);
            return;
        }

        if (data.contains("signal")) {
            message.delete(chatId, msgId);
            message.signalMessage(chatId);
            return;
        }

        if (data.contains("/admin_")) {

            if (data.contains("create_spam")) {
                message.createSpamMessage(chatId, msgId);
                StateKeeper.stateMap.put(chatId, State.SPAM);
                return;
            }

            if (data.contains("_start_spam")) {
                message.callBackAnswer(update);
                mailingService.adminMailing(chatId);
            }
        }


    }
}