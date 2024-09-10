package app.bot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@Getter
public class BotConfig {


    @Value("${bot.username}")
    private String botName;

    @Value("${bot.token}")
    private String token;

    @Value("${admin.chat.id}")
    private long adminChatId;

    private String path = "/root/mines_bot/pirates/data/";

}