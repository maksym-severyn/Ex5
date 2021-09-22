package pl.infoshare;

import static pl.infoshare.GetInputFromUser.getNumberFromUser;


public class Main {

    public static void main(String[] args) {

        System.out.println("Please enter the range, where random numbers will be chosen from:");
        int rangeOfRandomNumbers = getNumberFromUser();
        System.out.println("Haw many numbers need to randomize?");
        int numberOfDraws = getNumberFromUser();

        while (numberOfDraws > rangeOfRandomNumbers){
            System.out.println("Haw many numbers need to randomize?");
        }

        RandomProgram myProgram = new RandomProgram();

        myProgram.createTableOfRandomNumbers(numberOfDraws,rangeOfRandomNumbers);


    }

}
