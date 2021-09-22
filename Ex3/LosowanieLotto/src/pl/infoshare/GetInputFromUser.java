package pl.infoshare;

import java.util.Scanner;

public class GetInputFromUser {

    public static int getNumberFromUser() {
        int rangeOfNumber = 0;
        boolean keepAskingUser = true;

        do {
            try {
                Scanner scanner = new Scanner(System.in);
                rangeOfNumber = scanner.nextInt();
                if (rangeOfNumber < 0) {
                    System.out.println("Negative number is not allowed. Please enter the positive number:");
                    continue;
                }
                keepAskingUser = false;
            } catch (Exception e) {
                System.out.println("This type of data is not allowed. Please enter the number:");
                continue;
            }
        } while (keepAskingUser);

        return rangeOfNumber;
    }


}
