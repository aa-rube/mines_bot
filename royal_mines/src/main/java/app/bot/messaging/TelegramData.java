package app.bot.messaging;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TelegramData {

    public static Object getSendMessage(Long chatId, String string, ReplyKeyboard replyKeyboard, List<MessageEntity> messageEntities) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(string);
        msg.setReplyMarkup(replyKeyboard);
        msg.enableHtml(true);
        msg.setParseMode(ParseMode.HTML);

        if (messageEntities != null) {
            msg.setEntities(messageEntities);
        }

        return msg;
    }

    public static SendMediaGroup getSendMediaGroupMsg(Long chatId, List<InputMedia> media) {
        SendMediaGroup msg = new SendMediaGroup();
        msg.setChatId(chatId);
        msg.setMedias(media);
        msg.setProtectContent(true);
        return msg;
    }

    public static Object getSendMessage(Long chatId, String text, InlineKeyboardMarkup markup) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(text);
        msg.enableHtml(true);
        msg.setParseMode(ParseMode.HTML);
        msg.setReplyMarkup(markup);
        return msg;
    }


    public static Object getSendPhoto(Long chatId, String text, InlineKeyboardMarkup markup, File file) {
        SendPhoto msg = new SendPhoto();
        msg.setChatId(chatId);
        msg.setCaption(text);
        msg.setParseMode(ParseMode.HTML);
        msg.setReplyMarkup(markup);
        msg.setPhoto(new InputFile(file));
        return msg;
    }

    public static Object getSendVideoNote(Long chatId, InlineKeyboardMarkup markup, String fileId, List<MessageEntity> messageEntities) {
        SendVideoNote msg = new SendVideoNote();
        msg.setChatId(chatId);
        msg.setReplyMarkup(markup);
        msg.setVideoNote(new InputFile(fileId));
        return msg;
    }


    public static Object getSendVideo(Long chatId, String text, InlineKeyboardMarkup markup, String fileId, List<MessageEntity> messageEntities) {
        SendVideo msg = new SendVideo();
        msg.setChatId(chatId);
        msg.setCaption(text);
        msg.setParseMode(ParseMode.HTML);
        msg.setReplyMarkup(markup);
        msg.setVideo(new InputFile(fileId));

        if (messageEntities != null) {
            msg.setCaptionEntities(messageEntities);
        }

        return msg;
    }

    public static Object getSenAudio(Long chatId, String text, InlineKeyboardMarkup markup, String fileId, List<MessageEntity> messageEntities) {
        SendAudio msg = new SendAudio();
        msg.setChatId(chatId);
        msg.setCaption(text);
        msg.setParseMode(ParseMode.HTML);
        msg.setReplyMarkup(markup);
        msg.setAudio(new InputFile(fileId));

        if (messageEntities != null) {
            msg.setCaptionEntities(messageEntities);
        }

        return msg;
    }

    public static Object sendVoice(Long chatId, String text, InlineKeyboardMarkup markup, String fileId, List<MessageEntity> messageEntities) {
        SendVoice msg = new SendVoice();
        msg.setChatId(chatId);
        msg.setCaption(text);
        msg.setParseMode(ParseMode.HTML);
        msg.setReplyMarkup(markup);
        msg.setVoice(new InputFile(fileId));

        if (messageEntities != null) {
            msg.setCaptionEntities(messageEntities);
        }

        return msg;
    }

    public static Object sendDocument(Long chatId, String text, InlineKeyboardMarkup markup, String fileId, List<MessageEntity> messageEntities) {
        SendDocument msg = new SendDocument();
        msg.setChatId(chatId);
        msg.setCaption(text);
        msg.setParseMode(ParseMode.HTML);
        msg.setReplyMarkup(markup);
        msg.setDocument(new InputFile(fileId));

        if (messageEntities != null) {
            msg.setCaptionEntities(messageEntities);
        }

        return msg;
    }

    public static Object getSendPhoto(Long chatId, String text, InlineKeyboardMarkup markup, String fileId, List<MessageEntity> messageEntities) {
        SendPhoto msg = new SendPhoto();
        msg.setChatId(chatId);
        msg.setCaption(text);
        msg.setParseMode(ParseMode.HTML);
        msg.setReplyMarkup(markup);
        msg.setPhoto(new InputFile(fileId));

        if (messageEntities != null) {
            msg.setCaptionEntities(messageEntities);
        }

        return msg;
    }

    public static Object sendAnimation(Long chatId, String text, InlineKeyboardMarkup markup, String fileId, List<MessageEntity> messageEntities) {
        SendAnimation msg = new SendAnimation();
        msg.setChatId(chatId);
        msg.setCaption(text);
        msg.setParseMode(ParseMode.HTML);
        msg.setReplyMarkup(markup);
        msg.setAnimation(new InputFile(fileId));

        if (messageEntities != null) {
            msg.setCaptionEntities(messageEntities);
        }

        return msg;
    }

    public static Object getSendPhotoMarkdown(Long chatId, String text, InlineKeyboardMarkup markup, String fileId) {
        SendPhoto msg = new SendPhoto();
        msg.setChatId(chatId);
        msg.setCaption(text);
        msg.setParseMode(ParseMode.MARKDOWN);
        msg.setReplyMarkup(markup);
        msg.setPhoto(new InputFile(fileId));
        return msg;
    }

    public static Object getEditMessage(Long chatId, String text, InlineKeyboardMarkup markup, int msgId) {
        EditMessageText msg = new EditMessageText();
        msg.setChatId(chatId);
        msg.setMessageId(msgId);
        msg.setText(text);
        msg.enableHtml(true);
        msg.setParseMode(ParseMode.HTML);
        msg.setReplyMarkup(markup);
        return msg;
    }


    public static Object getDeleteMessage(Long chatId, int msgId) {
        DeleteMessage delete = new DeleteMessage();
        delete.setChatId(chatId);
        delete.setMessageId(msgId);
        return delete;
    }

    public static Object getEditMessageReplyMarkup(Long chatId, InlineKeyboardMarkup markup, int msgId) {
        EditMessageReplyMarkup msg = new EditMessageReplyMarkup();

        msg.setChatId(chatId);
        msg.setMessageId(msgId);
        msg.setReplyMarkup(markup);
        return msg;
    }

    public static Object getEditCaption(Long chatId, String text, InlineKeyboardMarkup markup, int msgId) {
        EditMessageCaption msg = new EditMessageCaption();

        msg.setChatId(chatId);
        msg.setMessageId(msgId);
        msg.setCaption(text);
        msg.setParseMode(ParseMode.HTML);
        msg.setReplyMarkup(markup);
        return msg;
    }

    public static Object getEditMessageMedia(Long chatId, InputMedia media, InlineKeyboardMarkup markup, int msgId) {
        EditMessageMedia msg = new EditMessageMedia();
        msg.setChatId(chatId);
        msg.setMessageId(msgId);
        msg.setReplyMarkup(markup);
        msg.setMedia(media);
        return msg;
    }

    public static Object getPopupMessage(String callbackQueryId, String text, boolean alert) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackQueryId);
        answer.setText(text);
        answer.setShowAlert(alert);
        return answer;
    }

    public static Object getCallbackQueryAnswer(String callbackQueryId) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackQueryId);
        return answer;
    }

    public static Object getCallbackQueryAnswer(Update update) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(update.getCallbackQuery().getId());
        return answer;
    }

    public static Object getForwardMessage(Long chatIdSendTo, Long chatIdFrom, int msgId) {
        ForwardMessage msg = new ForwardMessage();
        msg.setChatId(chatIdSendTo);
        msg.setFromChatId(chatIdFrom);
        msg.setMessageId(msgId);
        return msg;
    }


    public static InlineKeyboardMarkup createInlineKeyboardColumn(String[] buttonTexts, String[] callbackData) {
        InlineKeyboardMarkup inLineKeyBoard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardMatrix = new ArrayList<>();

        for (int i = 0; i < buttonTexts.length; i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(buttonTexts[i]);
            btn.setCallbackData(callbackData[i]);
            row.add(btn);
            keyboardMatrix.add(row);
        }

        inLineKeyBoard.setKeyboard(keyboardMatrix);
        return inLineKeyBoard;
    }

    public static InlineKeyboardMarkup createInlineKeyboardColumnAuto(String[] buttonTexts, String[] callbackData) {
        InlineKeyboardMarkup inLineKeyBoard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardMatrix = new ArrayList<>();

        for (int i = 0; i < buttonTexts.length; i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(buttonTexts[i]);

            String callBack = callbackData[i];

            if (callBack.contains("http")) {
                btn.setUrl(callBack);

            } else if(callBack.split(" ").length > 5) {
                btn.setSwitchInlineQueryCurrentChat(callBack);
            } else {
                btn.setCallbackData(callBack);
            }

            row.add(btn);
            keyboardMatrix.add(row);
        }

        inLineKeyBoard.setKeyboard(keyboardMatrix);
        return inLineKeyBoard;
    }


    public static InlineKeyboardMarkup createInlineKeyboardColumn(
            String[] buttonTexts,
            String[] callbackData,
            int inTheFirstLine) {
        InlineKeyboardMarkup inLineKeyBoard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardMatrix = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        if (inTheFirstLine > buttonTexts.length || inTheFirstLine <= 0) {
            inTheFirstLine = buttonTexts.length;
        }

        for (int i = 0; i < inTheFirstLine; i++) {
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(buttonTexts[i]);
            btn.setCallbackData(callbackData[i]);
            row.add(btn);
        }
        keyboardMatrix.add(row);

        row = new ArrayList<>();
        for (int i = inTheFirstLine; i < buttonTexts.length; i++) {
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(buttonTexts[i]);
            btn.setCallbackData(callbackData[i]);
            row.add(btn);

            if (row.size() == inTheFirstLine) {
                keyboardMatrix.add(row);
                row = new ArrayList<>();
            }
        }

        if (!row.isEmpty()) {
            keyboardMatrix.add(row);
        }

        inLineKeyBoard.setKeyboard(keyboardMatrix);
        return inLineKeyBoard;
    }
}