package app.bot.messaging.data;

import lombok.Getter;

@Getter
public enum ButtonText {
    SIGNAL("\uD83C\uDFF0 Выдать сигнал \uD83C\uDFF0"),
    BACK_TO_MENU("🔙 Вернуться в меню"),
    CRYPTOWORK("Criptowork"),
    MILKYWAY("Mi1lkyway"),
    CHECK("Проверить"),
    REGISTRATION("📱 Регистрация"),
    MANUAL("📚 Инструкция"),
    BACK_TO_MAIN_MENU("🔙 Вернуться в главное меню"),
    REGISTER("📱 🔶 Зарегистрироваться"),
    GET_SIGNAL("\uD83C\uDFF0 Получить сигнал \uD83C\uDFF0"),
    CHOOSE_MINES("❓ Выбрать кол-во мин ❓"),

    TWO_FILED("2 поля"),
    THREE_FILED("3 поля"),
    FOUR_FILED("4 поля"),
    FIVE_FILED("5 полей"),
    SIX_FILED("6 полей");

    private final String text;

    ButtonText(String text) {
        this.text = text;
    }
}
