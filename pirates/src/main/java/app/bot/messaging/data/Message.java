package app.bot.messaging.data;

import app.bot.config.BotConfig;
import app.bot.handler.state.StateKeeper;
import app.bot.messaging.MessagingService;
import app.bot.messaging.TelegramData;
import app.bot.util.MinesPredictor;
import app.bot.util.SafetySleep;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

@Component
public class Message {
    private final MessagingService msgService;
    private final Keyboard keyboard;
    private final String[] waitingMessagesList;
    private final Long admin;
    private final String path;

    public Message(@Lazy MessagingService msgService,
                   Keyboard keyboard, BotConfig botConfig) {
        this.msgService = msgService;
        this.keyboard = keyboard;
        this.admin = botConfig.getAdminChatId();
        this.waitingMessagesList = new String[]{
                MessageText.GAME_ANALYSIS.getText(),
                MessageText.GAME_RETRIEVAL.getText(),
                MessageText.GAME_STUDY.getText()};
//        this.path = //"/root/pirates" + File.separator +
////                File.separator +
//                        "data" + File.separator;


        this.path = "C:\\Users\\Alex\\Desktop\\pirates\\data\\";
    }

    public void delete(Long chatId, int msgId) {
        msgService.processMessage(TelegramData.getDeleteMessage(chatId, msgId));
    }

    public void firstRegistrationMessage(Long chatId) {
        msgService.processMessage(TelegramData.getSendPhoto(chatId,
                MessageText.REGISTRATION_FOR_STARTING.getText(),
                keyboard.registration(),
                new java.io.File("C:\\Users\\Alex\\Desktop\\pirates\\data\\reg.jpg")));
    }

    public void startMessage(Update update, Long chatId, boolean hsSubscription) {

        if (hsSubscription) {

            msgService.processMessage(TelegramData.getSendMessage(chatId,
                    MessageText.GREETING.getText(), keyboard.start()));

        } else {
            String userFirstName = update.getMessage() == null ?
                    update.getCallbackQuery().getFrom().getFirstName() : update.getMessage().getFrom().getFirstName();

            msgService.processMessage(TelegramData.getSendMessage(chatId,
                    String.format(MessageText.WELCOME_USER_NAME.getText(), userFirstName),
                    keyboard.subscribe()));
        }
    }

    public void registrationComplete(Long chatId, boolean isCorrect) {
        if (isCorrect) {
            msgService.processMessage(TelegramData.getSendMessage(chatId, MessageText.REGISTRATION_SUCCESS.getText(),
                    keyboard.registrationComplete()));
            StateKeeper.removeState(chatId);
        } else {
            msgService.processMessage(TelegramData.getSendMessage(chatId,
                    MessageText.INVALID_ID_ERROR.getText(), keyboard.regMain()));
        }
    }

    public void manualMessage(Long chatId, String data) {
        msgService.processMessage(TelegramData.getSendPhoto(chatId,
                MessageText.MANUAL_CAPTION.getText(), keyboard.manualKeyboard(data),
                new java.io.File("C:\\Users\\Alex\\Desktop\\pirates\\data\\manual.jpg")));
    }

    public void prediction(Long chatId, String userName, String data) {
        int mines = Integer.parseInt(data.split("_")[0]);
        String[] strings = MinesPredictor.predict(path, mines);

        int i = 0;
        for (String string : waitingMessagesList) {
            if (i == 0) {
               i = msgService.getMessageIdFromMessage(TelegramData.getSendMessage(chatId, string, null));
            }
            msgService.processMessage(TelegramData.getEditMessage(chatId, string, null, i));
            SafetySleep.sleep();
        }

        msgService.processMessage(TelegramData.getDeleteMessage(chatId, i));

        msgService.processMessage(TelegramData.getSendPhoto(
                        chatId,
                        strings[0],
                        keyboard.minesCount(mines),
                        new java.io.File(strings[1])
                )
        );

        String correctForm = mines > 4 ? "мин" : (mines == 1 ? "мину" : "мины");
        msgService.processMessage(TelegramData.getSendMessage(admin,
                String.format(MessageText.USER_RECEIVED_SIGNAL.getText(),userName, mines, correctForm), null));
    }

    public boolean scheduleMessage(Long chatId) {
       return msgService.getMessageIdFromMessage(TelegramData.getSendMessage(chatId,
                MessageText.USER_SCHEDULE_NOTIFICATION.getText(), null)) > 0;
    }

    public void rapportAboutScheduleNotification(Long admin, int notActiveUsers, int success) {
        msgService.processMessage(TelegramData.getSendMessage(admin,
                String.format(MessageText.SCHEDULE_NOTIFICATION.getText(), notActiveUsers, success),
                null));
    }

    public void startAdmin(Long chatId) {
        msgService.processMessage(TelegramData.getSendMessage(chatId,
                MessageText.START_ADMIN.getText(), keyboard.startAdmin()));
    }

    public void createSpamMessage(Long chatId, int msgId) {
        msgService.processMessage(TelegramData.getEditMessage(
                chatId, MessageText.CREATE_SPAM_MSG.getText(), null, msgId));
    }

    public void checkTheSpamMessage(Long chatId) {
        msgService.processMessage(TelegramData.getSendMessage(chatId,
                MessageText.SPAM_MESSAGE_OK.getText(), keyboard.startMailing()));
    }

    public boolean sendTheSpamMessageToUser(Long chatId, Object botMessage) {
        return msgService.processMailingMessage(chatId, botMessage);
    }

    public void adminMailingReport(Long chatId, int usersLIstCount, int success) {
        msgService.processMessage(TelegramData.getSendMessage(chatId,
                String.format(MessageText.SPAM_MAILING_REPORT.getText(), usersLIstCount, success),
                null));
    }

    public void callBackAnswer(Update update) {
        msgService.callBackAnswer(update);
    }
}
