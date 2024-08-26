package app.bot.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "miner_users")
public class MinerUser {
    @Id
    private Long chatId;

    private String handle;

    private String name;

    private Long lastVisit;
    private Long lastNotification;

    public MinerUser(Update update, boolean hasMessage) {
        if (hasMessage) {
            this.chatId = update.getMessage().getChatId();
            this.handle = "@" + update.getMessage().getFrom().getUserName();
            this.name = update.getMessage().getFrom().getFirstName();
        } else {
            this.chatId = update.getCallbackQuery().getMessage().getChatId();
            this.handle = "@" + update.getCallbackQuery().getFrom().getUserName();
            this.name = update.getCallbackQuery().getFrom().getFirstName();
        }

        this.lastVisit = System.currentTimeMillis();
        this.lastNotification = System.currentTimeMillis();
    }
}