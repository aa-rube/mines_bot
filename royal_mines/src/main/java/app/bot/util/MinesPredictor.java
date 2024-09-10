package app.bot.util;

import app.bot.messaging.data.MessageText;

import java.util.Random;

public class MinesPredictor {
    private final static Random random = new Random();

    public static String[] predict(String dataPath, int mine) {
        int pic = mine + 1;

        int gameNumber = random.nextInt(80000 - 10000 + 1) + 10000;
        int chanceWhole = random.nextInt(99 - 89 + 1) + 89;
        int chanceFraction = random.nextInt(99);
        int predictedPic = random.nextInt(pic) + 1;


        return new String[]{
                String.format(MessageText.GAME_INFO.getText(), gameNumber, chanceWhole, chanceFraction),
                String.format("%s/%s[%s].jpg", dataPath, mine, predictedPic)
        };
    }
}
