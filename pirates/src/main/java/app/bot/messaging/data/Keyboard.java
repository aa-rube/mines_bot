package app.bot.messaging.data;

import app.bot.messaging.TelegramData;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Component
public class Keyboard {

    public InlineKeyboardMarkup subscribe() {
        return TelegramData.createInlineKeyboardColumnAuto(
                new String[]{
                        ButtonText.MILKYWAY.getText(),
                        ButtonText.CHECK.getText()
                },
                new String[]{
                        "https://t.me/mi1lkyway89",
                        "proverka"}
        );
    }

    public InlineKeyboardMarkup start() {
        return TelegramData.createInlineKeyboardColumn(
                new String[]{ButtonText.REGISTRATION.getText(), ButtonText.MANUAL.getText(), ButtonText.SIGNAL.getText()},
                new String[]{"registration", "manual", "registration1"},
                2);
    }

    public InlineKeyboardMarkup manualKeyboard(String data) {
        return TelegramData.createInlineKeyboardColumn(
                new String[]{
                        ButtonText.BACK_TO_MENU.getText()
                },
                new String[]{
                        data.contains("1") ? "cancel1" : "cancel"
                }
        );
    }

    public InlineKeyboardMarkup regMain() {
        return TelegramData.createInlineKeyboardColumn(
                new String[]{
                        ButtonText.REGISTRATION.getText(),
                        ButtonText.BACK_TO_MENU.getText()
                },
                new String[]{
                        "registration",
                        "cancel"
                }
        );
    }

    public InlineKeyboardMarkup registration() {
        return TelegramData.createInlineKeyboardColumnAuto(
                new String[]{
                        ButtonText.REGISTER.getText(),
                        ButtonText.BACK_TO_MAIN_MENU.getText()
                },
                new String[]{
                        "https://1wfqtr.life/?open=register&p=k31a",
                        "cancel"
                }
        );
    }

    public InlineKeyboardMarkup registrationComplete() {
        return TelegramData.createInlineKeyboardColumn(
                new String[]{ButtonText.MANUAL.getText(), ButtonText.SIGNAL.getText()},
                new String[]{
                        "manual1",
                        "1_mina"
                }
        );
    }

    public InlineKeyboardMarkup minesCount(int minesCount) {
        return TelegramData.createInlineKeyboardColumn(
                new String[]{
                        ButtonText.GET_SIGNAL.getText()
                },
                new String[]{
                        minesCount + "_mina",
                }
        );
    }

    public InlineKeyboardMarkup startAdmin() {
        return TelegramData.createInlineKeyboardColumnAuto(
                new String[] {"Создать сообщение для рассылки"},
                new String[] {"/admin_create_spam"}
        );
    }

    public InlineKeyboardMarkup startMailing() {
        return TelegramData.createInlineKeyboardColumnAuto(
                new String[] {"Запустить рассылку"},
                new String[] {"/admin_start_spam"}
        );
    }
}
