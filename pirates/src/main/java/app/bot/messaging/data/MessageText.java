package app.bot.messaging.data;

import app.bot.util.LinkWrapper;
import lombok.Getter;

@Getter
public enum MessageText {
    GREETING("Добро пожаловать в \uD83C\uDFF4\u200D☠PIRATES⚓\n\nPirates - это увлекательная игра, где вы отправляетесь в морские приключения в поисках сокровищ. Игра доступна в букмекерской конторе 1win. Соберите свою команду и находите клады!\n\nНаш бот основан на нейросети от OpenAI, помогая вам делать правильные ходы с вероятностью успеха 87%."),
    WELCOME_USER_NAME("Добро пожаловать, %s!\n\nДля использования бота - подпишись на наш канал🤝"),
    GAME_ANALYSIS("🌐 Анализирую базу данных"),
    GAME_RETRIEVAL("🛜 Получаю данные с сервера."),
    GAME_STUDY("⚠️ Изучаю запросы.."),
    USER_RECEIVED_SIGNAL("Пользователь @%s получил сигнал!"),
    GAME_INFO("\uD83C\uDFF4\u200D☠ <b>Игра №%d</b>\n\n<b>Шанс - %d.%d%%</b>"),
    MANUAL_CAPTION("<b>Бот основан и обучен на кластере нейросети от OpenAI\uD83D\uDDA5[ChatGPT-v4].</b>\n\nЧтобы достичь максимального выигрыша, придерживайтесь данной инструкции.\n\n1. Регистрация в 1WIN:\n<code>Зарегистрируйтесь в букмекерской конторе 1win. Если сайт не открылся, воспользуйтесь VPN.</code>\n\n2. Пополнение баланса:\n<code>Пополните свой игровой счет. Чем больше сумма, тем больше потенциальная прибыль. \uD83E\uDD11</code>\n\n3. Выбор игры:\n<code>Перейдите в раздел 1win games и выберите игру ‘PIRATES’.</code>\n\n4. Ищите клады:\n<code>Выбирайте острова, ищите сокровища и избегайте опасностей.</code>\n\n5. Получение сигнала:\n<code>Запросите сигнал у бота.</code>\n\n6. Ставки:\n<code>Делайте ставки точно так, как указано в сигнале от бота.</code> \uD83C\uDFAF"),
    SUBSCRIBE_REMINDER("Для использования бота - подпишись на наш канал🤝"),
    REGISTRATION_FOR_STARTING("\uD83D\uDD37 1. Для начала зарегистрируйтесь по ссылке на сайте промокод ROWAN " + LinkWrapper.wrapTextInLink("1WIN (CLICK)", "https://1wxxlb.com/casino/list?open=register&p=qwka") + "\n\uD83D\uDD37 2. После успешной регистрации cкопируйте ваш айди на сайте (Вкладка 'пополнение' и в правом верхнем углу будет ваш ID).\n\uD83D\uDD37 3. И отправьте его боту в ответ на это сообщение!"),
    INVALID_ID_ERROR("Введён неверный айди, пройдите регистрацию еще раз!"),
    REGISTRATION_SUCCESS("Вы успешно прошли регистрацию!\n\nДобро пожаловать в PIRATES!⚓\n\nPirates - это увлекательная игра, где вы отправляетесь в морские приключения в поисках сокровищ. Игра доступна в букмекерской конторе 1win. Соберите свою команду и находите клады!\n\nНаш бот основан на нейросети от OpenAI, помогая вам делать правильные ходы с вероятностью успеха 87%."),

    SCHEDULE_NOTIFICATION("Внимание!\n\nБот сделал рассылку уведомлений неактивным пользователям.\nВот результаты:\n\nВсего неактивных: %d\nУспешно отправлено сообщений:%d\n\n!!!Если успешных сообщений меньше, чем количество пользователей, значит пользователь заблокировал бота"),
    USER_SCHEDULE_NOTIFICATION("\uD83C\uDFAF Время снова испытать свою удачу!\nТы давно не заходил в бота \uD83C\uDFF4\u200D☠PIRATES\uD83C\uDFF4\u200D☠⚓, а на площадке уже кипят новые игры. Заходи и докажи, что ты лучший! \uD83D\uDCA3"),
    START_ADMIN("Привет, Админ!"),
    CREATE_SPAM_MSG("Отправь мне сообщение, которое хочешь разослать.\n\nПосле этого, я пришлю тебе кнопку, для рассылки твоего сообщения."),
    SPAM_MESSAGE_OK("Я получил ваше сообщение. Если все корректно нажмите запустить рассылку"),
    SPAM_MAILING_REPORT("Внимание!\n\nБот сделал рассылку сообщения!.\nВот результаты:\n\nВсего пользователей в БД: %d\nУспешно отправлено сообщений:%d\n\n!!!Если успешных сообщений меньше, чем количество пользователей, значит пользователь заблокировал бота");

    private final String text;

    MessageText(String text) {
        this.text = text;
    }
}
