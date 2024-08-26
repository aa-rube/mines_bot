package app.bot.controller;

import app.bot.config.BotConfig;
import app.bot.handler.CallBackDataHandler;
import app.bot.handler.TextMsgHandler;
import app.bot.messaging.TelegramData;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Controller
public class UpdateReceivedController extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final CallBackDataHandler callBackDataHandler;
    private final TextMsgHandler textMsgHandler;

    public UpdateReceivedController(BotConfig botConfig,
                                    CallBackDataHandler callBackDataHandler,
                                    TextMsgHandler textMsgHandler) {
        this.botConfig = botConfig;
        this.callBackDataHandler = callBackDataHandler;
        this.textMsgHandler = textMsgHandler;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()) {
            new Thread(() -> callBackDataHandler.updateHandler(update)).start();
        } else if (update.hasMessage()) {
            new Thread(() -> textMsgHandler.updateHandler(update)).start();
        }
    }
}