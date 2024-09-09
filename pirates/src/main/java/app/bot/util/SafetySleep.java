package app.bot.util;

import java.util.Random;

public class SafetySleep {
    private final static Random random = new Random();

    public static void sleep() {
//        int millis = random.nextInt(3000 - 1000 + 1) + 1000;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
