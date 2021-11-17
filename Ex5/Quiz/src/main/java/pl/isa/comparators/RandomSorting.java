package pl.isa.comparators;

import pl.isa.Main;

import java.util.Comparator;

public class RandomSorting implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return Main.RANDOMIZER.randomize(-1,1);
    }
}
