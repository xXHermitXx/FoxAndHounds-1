package hu.Progtech.Service;

import java.util.Random;

public class RandomGenerator {

    Random random = new Random();

    public int makeRandomNumber(int bound) {
        return random.nextInt(bound);
    }

    public boolean makeRandomBool() {
        return random.nextBoolean();
    }
}