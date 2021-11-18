package pl.isa.util;

import java.util.Random;

public class Randomizer {
    private Random random;

    public Randomizer() {
        random = new Random();
    }

    public Integer randomize(int min, int max) {
        Integer result = null;
        try {
            result = random.nextInt((max - min) + 1) + min;
        } catch (IllegalArgumentException e){
            System.out.println("Min needs to be lower than max!");
            e.getMessage();
        }
        return result;
    }
}
