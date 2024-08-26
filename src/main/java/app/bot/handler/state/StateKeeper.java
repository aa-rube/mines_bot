package app.bot.handler.state;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;

@Getter
public class StateKeeper {

    public final static HashMap<Long, State> stateMap = new HashMap<>();
    public final static HashMap<Long, Message> spamMessages = new HashMap<>();

    public static void removeState(Long chatId) {
        stateMap.remove(chatId);
    }
}
