package pl.isa.util;

import java.util.Random;

public class Randomizer {
    private Random random;

    public Randomizer() {
        random = new Random();
    }

    public Integer randomize(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
