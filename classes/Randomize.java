package classes;

import java.util.Random;

public class Randomize {

    public static Random random = new Random();

    public static Boolean getRandomBoolean() {
        return (random.nextBoolean());
    }

    public static int getRandomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
