package app.bot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@Getter
public class BotConfig {

    public BotConfig() {
        String rootDir = System.getProperty("user.dir");
        File dataFolder = new File(rootDir, "data");
        if (dataFolder.exists() && dataFolder.isDirectory()) {
            this.path = dataFolder.getAbsolutePath();
            System.out.println("Data folder found at: " + this.path);
        } else {
            throw new IllegalStateException("Data folder not found in root directory: " + rootDir);
        }
    }

    @Value("${bot.username}")
    private String botName;

    @Value("${bot.token}")
    private String token;

    @Value("${admin.chat.id}")
    private long adminChatId;

    private final String path;

}