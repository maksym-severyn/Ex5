package pl.infoshare;

import java.io.IOException;
import java.util.Random;

import static pl.infoshare.File.writeResultsToFile;
import static pl.infoshare.GetInputFromUser.getNumberFromUser;
import static pl.infoshare.Sorting.sortDesc;


public class RandomProgram {

    public int[] randomNumbersTable;
    final private int numberToBeChanged = 0;

    private static int getRandomNumberFrom1ToInput(int rangeOfRandomizer) {
        Random randomizer = new Random();
        return randomizer.nextInt(rangeOfRandomizer) + 1;
    }

    private void createTableOfRandomNumbers(int sizeOfTable, int randomizerRange) {

        this.randomNumbersTable = new int[sizeOfTable];

        for (int i = 0; i < randomNumbersTable.length; i++) {
            randomNumbersTable[i] = getRandomNumberFrom1ToInput(randomizerRange);
        }

        this.checkAndReplaceZerosToNumbersInTable(randomizerRange);
    }

    private void checkAndReplaceZerosToNumbersInTable(int randomizerRange) {
        boolean keepLooping;

        do {
            setDuplicatedValuesAsZeroIfExists();
            keepLooping = false;
            for (int i = 0; i < this.randomNumbersTable.length; i++) {
                if (this.randomNumbersTable[i] == numberToBeChanged) {
                    updateTableByIndex(i, randomizerRange);
                    keepLooping = true;
                }
            }

        } while (keepLooping);

    }

    private void setDuplicatedValuesAsZeroIfExists() {
        int numberToVerify;

        for (int i = 0; i < this.randomNumbersTable.length; i++) {
            numberToVerify = this.randomNumbersTable[i];
            for (int j = 0; j < this.randomNumbersTable.length; j++) {
                if ((numberToVerify == this.randomNumbersTable[j]) && (j != i) && (this.randomNumbersTable[j] != numberToBeChanged)) {
                    this.randomNumbersTable[j] = numberToBeChanged;
                }
            }
        }

    }

    private void updateTableByIndex(int indexToChange, int rangeOfRandomNumbers) {
        this.randomNumbersTable[indexToChange] = getRandomNumberFrom1ToInput(rangeOfRandomNumbers);
    }

    public void run() throws InterruptedException, IOException {

        System.out.println("Please enter the range, where random numbers will be chosen from:");
        int rangeOfRandomNumbers = getNumberFromUser();
        System.out.println("How many numbers need to randomize?");
        int numberOfDraws = getNumberFromUser();

        while (numberOfDraws > rangeOfRandomNumbers) {
            System.out.println("Count of numbers to randomize cannot exceed the range, where random numbers will be chosen from.");
            System.out.println("Please enter how many numbers need to randomize again:");
            numberOfDraws = getNumberFromUser();
        }

        System.out.println("Start of draw " + numberOfDraws + " digits ranging from 1 to " + rangeOfRandomNumbers);

        RandomProgram myProgram = new RandomProgram();
        myProgram.createTableOfRandomNumbers(numberOfDraws, rangeOfRandomNumbers);

        System.out.println("Drawn numbers:");
        for (int i = 0; i < myProgram.randomNumbersTable.length; i++) {
            Thread.sleep(1000);
            System.out.println(myProgram.randomNumbersTable[i]);
        }

        sortDesc(myProgram.randomNumbersTable);

        System.out.println("Sorted drawn numbers:");
        for (int i = 0; i < myProgram.randomNumbersTable.length; i++) {
            Thread.sleep(1000);
            System.out.println(myProgram.randomNumbersTable[i]);
        }

        writeResultsToFile(myProgram);

    }


}
