package app.bot.messaging;

import app.bot.controller.UpdateReceivedController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.groupadministration.ApproveChatJoinRequest;
import org.telegram.telegrambots.meta.api.methods.groupadministration.CreateChatInviteLink;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.*;
import org.telegram.telegrambots.meta.api.objects.ChatInviteLink;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.image.PixelGrabber;

@Service
public class MessagingService {

    private final UpdateReceivedController updateReceivedController;

    public MessagingService(UpdateReceivedController updateReceivedController) {
        this.updateReceivedController = updateReceivedController;
    }

    public void processMessage(Object msg) {
        try {
            if (msg instanceof SendMessage) {
                updateReceivedController.executeAsync((SendMessage) msg);
            } else if (msg instanceof SendPhoto) {
                updateReceivedController.executeAsync((SendPhoto) msg);
            } else if (msg instanceof SendVideoNote) {
                updateReceivedController.executeAsync((SendVideoNote) msg);
            } else if (msg instanceof SendVoice) {
                updateReceivedController.executeAsync((SendVoice) msg);
            } else if (msg instanceof SendDocument) {
                updateReceivedController.executeAsync((SendDocument) msg);
            } else if (msg instanceof EditMessageText) {
                updateReceivedController.executeAsync((EditMessageText) msg);
            } else if (msg instanceof SendVideo) {
                updateReceivedController.executeAsync((SendVideo) msg);
            } else if (msg instanceof SendAnimation) {
                updateReceivedController.executeAsync((SendAnimation) msg);
            } else if (msg instanceof DeleteMessage) {
                updateReceivedController.executeAsync((DeleteMessage) msg);
            } else if (msg instanceof EditMessageReplyMarkup) {
                updateReceivedController.executeAsync((EditMessageReplyMarkup) msg);
            } else if (msg instanceof SendMediaGroup) {
                updateReceivedController.execute((SendMediaGroup) msg);
            } else if (msg instanceof EditMessageCaption) {
                updateReceivedController.executeAsync((EditMessageCaption) msg);
            } else if (msg instanceof ForwardMessage) {
                updateReceivedController.executeAsync((ForwardMessage) msg);
            } else if (msg instanceof AnswerCallbackQuery) {
                updateReceivedController.executeAsync((AnswerCallbackQuery) msg);
            } else if (msg instanceof EditMessageMedia) {
                updateReceivedController.executeAsync((EditMessageMedia) msg);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }


    public boolean processMailingMessage(Long chatId, Object msg) {
        try {
            if (msg instanceof SendMessage) {
                ((SendMessage) msg).setChatId(chatId);
                updateReceivedController.executeAsync((SendMessage) msg);
            } else if (msg instanceof SendPhoto) {
                ((SendPhoto) msg).setChatId(chatId);
                updateReceivedController.executeAsync((SendPhoto) msg);
            } else if (msg instanceof SendVideoNote) {
                ((SendVideoNote) msg).setChatId(chatId);
                updateReceivedController.executeAsync((SendVideoNote) msg);
            } else if (msg instanceof SendVoice) {
                ((SendVoice) msg).setChatId(chatId);
                updateReceivedController.executeAsync((SendVoice) msg);
            } else if (msg instanceof SendDocument) {
                ((SendDocument) msg).setChatId(chatId);
                updateReceivedController.executeAsync((SendDocument) msg);
            } else if (msg instanceof EditMessageText) {
                ((EditMessageText) msg).setChatId(chatId);
                updateReceivedController.executeAsync((EditMessageText) msg);
            } else if (msg instanceof SendVideo) {
                ((SendVideo) msg).setChatId(chatId);
                updateReceivedController.executeAsync((SendVideo) msg);
            } else if (msg instanceof SendAnimation) {
                ((SendAnimation) msg).setChatId(chatId);
                updateReceivedController.executeAsync((SendAnimation) msg);
            } else if (msg instanceof DeleteMessage) {
                ((DeleteMessage) msg).setChatId(chatId);
                updateReceivedController.executeAsync((DeleteMessage) msg);
            } else if (msg instanceof EditMessageReplyMarkup) {
                ((EditMessageReplyMarkup) msg).setChatId(chatId);
                updateReceivedController.executeAsync((EditMessageReplyMarkup) msg);
            } else if (msg instanceof SendMediaGroup) {
                ((SendMediaGroup) msg).setChatId(chatId);
                updateReceivedController.execute((SendMediaGroup) msg);
            } else if (msg instanceof EditMessageCaption) {
                ((EditMessageCaption) msg).setChatId(chatId);
                updateReceivedController.executeAsync((EditMessageCaption) msg);
            } else if (msg instanceof ForwardMessage) {
                ((ForwardMessage) msg).setChatId(chatId);
                updateReceivedController.executeAsync((ForwardMessage) msg);
            } else if (msg instanceof EditMessageMedia) {
                ((EditMessageMedia) msg).setChatId(chatId);
                updateReceivedController.executeAsync((EditMessageMedia) msg);
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public int getMessageIdFromMessage(Object msg) {
        try {
            return updateReceivedController.execute((SendMessage) msg).getMessageId();
        } catch (TelegramApiException e) {
            return -1;
        }
    }

    public void callBackAnswer(Update update) {
        try {
            updateReceivedController.executeAsync((AnswerCallbackQuery) TelegramData.getCallbackQueryAnswer(update));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public ChatMember getChatMember(GetChatMember member) {
        try {
            return updateReceivedController.execute(member);
        } catch (TelegramApiException telegramApiException) {
            return null;
        }
    }
}