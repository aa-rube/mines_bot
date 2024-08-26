package app.bot.util;

import java.util.Random;

public class RandomNumber {
    private static final int[] numbers = {1, 3, 5, 7};
    private static final Random random = new Random();

    public static int getRandomNumber() {
        int index = random.nextInt(numbers.length);
        return numbers[index];
    }

}
