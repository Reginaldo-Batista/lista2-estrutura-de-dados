package classes;

import java.util.Random;

/**
 * A classe Randomize fornece métodos utilitários para aleatorizar.
 */
public class Randomize {

    public static Random random = new Random();

    public static Boolean getRandomBoolean() {
        return (random.nextBoolean());
    }

    public static int getRandomInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
