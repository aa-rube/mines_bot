package app.bot.util;

import app.bot.messaging.data.MessageText;

import java.util.Random;

public class MinesPredictor {
    private final static Random random = new Random();
    private final static String path = "/root/mines_bot/";

    public static String[] predict(int mine) {
        int pic = 0;

        if (mine == 1) {
            pic = 30;
        } else if (mine == 3) {
            pic = 40;
        } else if (mine == 5) {
            pic = 41;
        } else {
            pic = 40;
        }

        int gameNumber = random.nextInt(80000 - 10000 + 1) + 10000;
        int chanceWhole = random.nextInt(99 - 89 + 1) + 89;
        int chanceFraction = random.nextInt(99);
        int predictedPic = random.nextInt(pic) + 1;


        return new String[]{
                String.format(MessageText.GAME_INFO.getText(), gameNumber, chanceWhole, chanceFraction),
                String.format("%sdata/%s[%s].jpg", path, mine, predictedPic)
        };
    }
}
