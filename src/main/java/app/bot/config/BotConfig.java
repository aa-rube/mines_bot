package app.bot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class BotConfig {
    @Value("${bot.username}")
    String botName;

    @Value("${bot.token}")
    String token;

    @Value("${admin.chat.id}")
    long adminChatId;
}