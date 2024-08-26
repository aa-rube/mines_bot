package app.bot.mailing;

import app.bot.messaging.TelegramData;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.util.List;

public class MessageUtil {

    public static Object getAdminSpamMessage(Long chatId, org.telegram.telegrambots.meta.api.objects.Message spamMessage) {

        String text = spamMessage.getCaption() == null ?
                spamMessage.getText() : spamMessage.getCaption();

        List<MessageEntity> messageEntities = null;

        if (spamMessage.getText() != null) {
            return TelegramData.getSendMessage(chatId, text, null, messageEntities);
        }

        if (spamMessage.hasPhoto()) {
            return TelegramData.getSendPhoto(chatId, text,null,
                    spamMessage.getPhoto().getFirst().getFileId(), messageEntities);
        }

        if (spamMessage.hasVideoNote()) {
            return TelegramData.getSendVideoNote(chatId,
                    null, spamMessage.getVideoNote().getFileId(), messageEntities);
        }

        if (spamMessage.hasVideo()) {
            return TelegramData.getSendVideo(chatId, text,
                    null, spamMessage.getVideo().getFileId(), messageEntities);
        }

        if (spamMessage.hasAudio()) {
            return TelegramData.getSenAudio(chatId, text,
                    null, spamMessage.getAudio().getFileId(), messageEntities);
        }

        if (spamMessage.hasVoice()) {
            return TelegramData.sendVoice(chatId, text,
                    null, spamMessage.getVoice().getFileId(), messageEntities);
        }

        if (spamMessage.hasDocument()) {
            return TelegramData.sendDocument(chatId, text,
                    null, spamMessage.getDocument().getFileId(), messageEntities);
        }

        if (spamMessage.hasAnimation()) {
            return TelegramData.sendAnimation(chatId, text,
                    null, spamMessage.getAnimation().getFileId(), messageEntities);
        }

        return null;
    }

}
