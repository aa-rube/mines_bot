package app.bot.util;

import app.bot.messaging.MessagingService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;

@Service
public class CheckSubscribeToChannel {

    private final MessagingService msgService;
    private final String[] partners;

    public CheckSubscribeToChannel(@Lazy MessagingService msgService) {
        this.msgService = msgService;
        this.partners = new String[]{"-1002126972926"};
    }

    public boolean checkChannelSubscription(Long chatId) {
        int partnerCount = partners.length;

        for (String partner : partners) {
            String status;

            try {
                status = msgService.getChatMember(new GetChatMember(partner, chatId)).getStatus();
            } catch (Exception e) {
                continue;
            }

            if (status != null && !status.equals("null")
                    && (status.equals("member") || status.equals("creator") || status.equals("administrator"))) {
                partnerCount--;
            }
        }
        return partnerCount == 0;
    }
}