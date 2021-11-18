package pl.isa.util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pl.isa.Display.BREAK_AND_CLOSE;

public class Util {
    public static final Scanner scanner = new Scanner(System.in);

    public static void makeDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Przerwałeś(-aś) działanie programu... :(");
            e.printStackTrace();
        }
    }

    public static String getInputFromUser(String messageForUser, String regex, String errorMessage) throws ReturnToStartException {
        String inputFromUser;

        System.out.println(messageForUser);
        System.out.println(BREAK_AND_CLOSE);
        System.out.println("______________________________");
        inputFromUser = scanner.nextLine();
        Util.userInputCheck(inputFromUser);
        if (!(isStringValid(inputFromUser, regex))) {
            System.out.println(errorMessage);
            inputFromUser = getInputFromUser(messageForUser, regex, errorMessage);
        }
        return inputFromUser;
    }

    private static boolean isStringValid(String userInput, String regex) {
        Pattern ptrn = Pattern.compile(regex);
        Matcher match = ptrn.matcher(userInput);
        return match.matches();
    }

    public static void userInputCheck(String inputUser) throws ReturnToStartException {
        if (inputUser.equals("0")) {
            throw new ReturnToStartException("Wybrałeś(-łaś) zamykanie...");
        }
    }
}
