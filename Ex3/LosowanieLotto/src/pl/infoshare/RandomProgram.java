package pl.infoshare;

import java.util.Random;

public class RandomProgram {
    public int[] randomNumbersTable;

    private static int getRandomNumberFrom1ToInput(int rangeOfRandomizer) {
        Random randomizer = new Random();
        return randomizer.nextInt(rangeOfRandomizer) + 1;
    }

    public void createTableOfRandomNumbers(int sizeOfTable, int randomizerRange) {

        this.randomNumbersTable = new int[sizeOfTable];

        for (int i = 0; i < randomNumbersTable.length; i++) {
            randomNumbersTable[i] = getRandomNumberFrom1ToInput(randomizerRange);
        }

        this.checkAndCorrectIfTableHasDuplicates(randomizerRange);
    }

    private void checkAndCorrectIfTableHasDuplicates(int randomizerRange) {
        int numberToVerify;
        final int numberToBeChanged = 0;
        boolean keepLooping;

        do {
            for (int i = 0; i < this.randomNumbersTable.length; i++) {
                numberToVerify = this.randomNumbersTable[i];
                for (int j = 0; j < this.randomNumbersTable.length; j++) {
                    if ((numberToVerify == this.randomNumbersTable[j]) && (j != i) && (this.randomNumbersTable[j] != numberToBeChanged)) {
                        this.randomNumbersTable[j] = numberToBeChanged;
                    }
                }
            }

            keepLooping = false;
            for (int i = 0; i < this.randomNumbersTable.length; i++) {
                if (this.randomNumbersTable[i] == numberToBeChanged) {
                    updateTableByIndex(i, randomizerRange);
                    keepLooping = true;
                }
            }

        } while (keepLooping);

    }

    private void updateTableByIndex(int indexToChange, int rangeOfRandomNumbers) {
        this.randomNumbersTable[indexToChange] = getRandomNumberFrom1ToInput(rangeOfRandomNumbers);
    }


}
