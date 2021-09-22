package pl.infoshare;

import java.util.Scanner;

public class GetInputFromUser {

    public static int getRangeOfRandomNumber(){
        Scanner scanner = new Scanner(System.in);
        int rangeOfNumber;
        boolean keepAskingUser = false;

        try {
            rangeOfNumber = scanner.nextInt();
            keepAskingUser = true;
        } catch (Exception e){

        }
        return 1;
    }

}
