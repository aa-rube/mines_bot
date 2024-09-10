package app.bot.handler.state;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.concurrent.ConcurrentHashMap;

@Getter
public class StateKeeper {

    private final static ConcurrentHashMap<Long, State> stateMap = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<Long, Message> spamMessages = new ConcurrentHashMap<>();

    public static void removeState(Long chatId) {
        stateMap.remove(chatId);
    }

    public static boolean containsKey(Long chatId) {
        return stateMap.containsKey(chatId);
    }

    public static void putState(Long chatId, State state) {
        stateMap.put(chatId, state);
    }

    public static State getState(Long chatId) {
        return stateMap.get(chatId);
    }

    public static void removeSpamMessage(Long chatId) {
        spamMessages.remove(chatId);
    }

    public static void putSpamMessage(Long chatId, Message message) {
        spamMessages.put(chatId, message);
    }

    public static Message getSpamMessage(Long chatId) {
        return spamMessages.get(chatId);
    }
}
