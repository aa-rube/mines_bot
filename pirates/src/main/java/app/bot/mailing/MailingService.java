package app.bot.mailing;

import app.bot.config.BotConfig;
import app.bot.db.model.PirateUser;
import app.bot.db.service.PirateUserService;
import app.bot.handler.state.StateKeeper;
import app.bot.messaging.data.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailingService {

    private final Message message;
    private final PirateUserService pirateUserService;
    private final Long admin;
    private final long weekInMillis;

    public MailingService(Message message,
                          PirateUserService pirateUserService,
                          BotConfig botConfig) {
        this.message = message;
        this.pirateUserService = pirateUserService;
        this.admin = botConfig.getAdminChatId();
        this.weekInMillis = 7 * 24 * 60 * 60 * 1000L;
    }

    @Scheduled(cron = "0 0/30 09-21 * * ?")
    public void mailingSchedule() {
        Long currentTime = System.currentTimeMillis();

        int success = 0;
        int notActive = 0;

        for (PirateUser user : pirateUserService.findAll()) {

            if (currentTime - user.getLastVisit() >= weekInMillis
                    && currentTime - user.getLastNotification() >= weekInMillis) {

                notActive++;

                if (message.scheduleMessage(user.getChatId())) {
                    user.setLastNotification(System.currentTimeMillis());
                    pirateUserService.save(user);
                    success++;
                }
            }
        }

        if (notActive == 0) return;
        message.rapportAboutScheduleNotification(admin, notActive, success);
    }

    public void adminMailing(Long chatId) {
        org.telegram.telegrambots.meta.api.objects.Message spamMessage = StateKeeper.getSpamMessage(chatId);

        List<PirateUser> userList = pirateUserService.findAll();
        int usersLIstCount = userList.size();
        int success = 0;

        for (PirateUser user : pirateUserService.findAll()) {
            Object botMessage = MessageUtil.getAdminSpamMessage(user.getChatId(), spamMessage);

            if (message.sendTheSpamMessageToUser(user.getChatId(), botMessage)) {
                success++;
            }
        }

        message.adminMailingReport(chatId, usersLIstCount, success);
    }

}
