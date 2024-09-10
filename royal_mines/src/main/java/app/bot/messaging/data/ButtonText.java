package app.bot.messaging.data;

import lombok.Getter;

@Getter
public enum ButtonText {
    SIGNAL("\uD83C\uDFF4\u200D☠ Выдать сигнал \uD83C\uDFF4\u200D☠"),
    BACK_TO_MENU("🔙 Вернуться в меню"),
    CRYPTOWORK("Criptowork"),
    MILKYWAY("Mi1lkyway"),
    CHECK("Проверить"),
    REGISTRATION("📱 Регистрация"),
    MANUAL("📚 Инструкция"),
    BACK_TO_MAIN_MENU("🔙 Вернуться в главное меню"),
    REGISTER("📱 🔶 Зарегистрироваться"),
    GET_SIGNAL("\uD83C\uDFF4\u200D☠\uFE0F Получить сигнал \uD83C\uDFF4\u200D☠\uFE0F"),
    CHOOSE_MINES("❓ Выбрать кол-во мин ❓"),
    ONE_MINE("1 мина"),
    THREE_MINES("3 мины"),
    FIVE_MINES("5 мин"),
    SEVEN_MINES("7 мин");

    private final String text;

    ButtonText(String text) {
        this.text = text;
    }
}
