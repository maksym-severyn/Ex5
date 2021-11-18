package pl.isa;

import pl.isa.question.Question;
import pl.isa.question.QuestionCategory;
import pl.isa.question.QuestionType;
import pl.isa.util.ReturnToStartException;
import pl.isa.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static pl.isa.util.Util.getInputFromUser;
import static pl.isa.util.Util.makeDelay;

public class Display {
    public static final String BREAK_AND_CLOSE = "0 - Przerwij i zamknij";

    public String selectAuthorization() throws ReturnToStartException {
        System.out.println("\n--------------------WITAJ W QUIZ !!!--------------------\n");
        makeDelay(1000);
        return authorization();
    }

    private String authorization() throws ReturnToStartException {
        return Util.getInputFromUser("Podaj imię i nazwisko lub nickname:", "\\D*",
                "Źle wprowadzony typ danych. Spróbuj jeszcze raz");
    }

    public QuestionCategory selectQuestionCategory() throws ReturnToStartException {
        System.out.println("Wybierz temat, którego będą dotyczyć pytania:");
        System.out.println("______________________________");
        for (int i = 0; i < QuestionCategory.values().length; i++) {
            System.out.println(QuestionCategory.values()[i].getSequentialNumber() + " - " +
                    QuestionCategory.values()[i].getExplaining());
        }
        System.out.println(BREAK_AND_CLOSE);
        System.out.println("______________________________");

        String userInput = Util.scanner.nextLine();
        Util.userInputCheck(userInput);

        return validateAndAssignQuestionCategory(userInput);
    }

    private QuestionCategory validateAndAssignQuestionCategory(String userInput) throws ReturnToStartException {
        QuestionCategory questionCategory = null;
        for (QuestionCategory i : QuestionCategory.values()) {
            if (i.getSequentialNumber().equals(userInput)) {
                questionCategory = i;
                break;
            }
        }
        if (questionCategory == null) {
            System.out.println("Wprowadzono niepoprawne dane. Spróbuj jeszcze raz.");
            questionCategory = selectQuestionCategory();
        }
        return questionCategory;
    }

    public QuestionType selectQuestionType() throws ReturnToStartException {
        System.out.println("Wybierz rodzaj testu, jaki będzie wyświetlony:");
        System.out.println("______________________________");
        for (int i = 0; i < QuestionType.values().length; i++) {
            System.out.println(QuestionType.values()[i].getSequentialNumber() + " - " +
                    QuestionType.values()[i].getExplaining());
        }
        System.out.println(BREAK_AND_CLOSE);
        System.out.println("______________________________");

        String userInput = Util.scanner.nextLine();
        Util.userInputCheck(userInput);
        return validateAndAssignQuestionType(userInput);
    }

    private QuestionType validateAndAssignQuestionType(String userInput) throws ReturnToStartException {
        QuestionType questionType = null;
        for (QuestionType i : QuestionType.values()) {
            if (i.getSequentialNumber().equals(userInput)) {
                questionType = i;
                break;
            }
        }
        if (questionType == null) {
            System.out.println("Wprowadzono niepoprawne dane. Spróbuj jeszcze raz.");
            questionType = selectQuestionType();
        }
        return questionType;
    }

    public void displayQuestion(Question question, long counter) {
        List<Question.Answer> answerList = Main.QUESTION_SERVICE.getAnswerListFromQuestion(question);

        System.out.println("------------------------PYTANIE NR. " + counter + "------------------------");
        System.out.println("|");
        question.getQuestionAndAnswers().keySet().forEach(s -> System.out.println("|--" + s + ":"));
        System.out.println("|");
        for (Question.Answer answer : answerList) {
            System.out.println("| " + answer);
        }
        System.out.println("|");
        System.out.println("|--id pytania:" + question.getId());
        System.out.println("-------------------------------------------------------------\n");
    }

    public List<Character> getAnswerFromUser(QuestionType typeOfQuestion, List<Character> allAnswers) throws ReturnToStartException {
        Character smallFirstLetter = allAnswers.get(0).toString().toLowerCase(Locale.ROOT).toLowerCase(Locale.ROOT).charAt(0);
        Character bigFirstLetter = allAnswers.get(0).toString().toUpperCase(Locale.ROOT).charAt(0);
        Character smallLastLetter = allAnswers.get(allAnswers.size() - 1).toString().toLowerCase(Locale.ROOT).charAt(0);
        Character bigLastLetter = allAnswers.get(allAnswers.size() - 1).toString().toUpperCase(Locale.ROOT).charAt(0);
        String message;
        StringBuilder regex = new StringBuilder();
        if (QuestionType.SINGLE_CHOICE.equals(typeOfQuestion)) {
            message = "Wybierz jedną odpowiedź, wpisując literę";
            regex.append("[").append(allAnswers.get(0).toString().toLowerCase(Locale.ROOT)).append("-").append(allAnswers.get(allAnswers.size() - 1).toString().toLowerCase(Locale.ROOT)).append(allAnswers.get(0).toString().toUpperCase(Locale.ROOT)).append("-").append(allAnswers.get(allAnswers.size() - 1).toString().toUpperCase(Locale.ROOT)).append("]{1}");
        } else {
            message = "Wybierz jedną lub kilka odpowiedzi, wpisując literę, lub litery po przecinku, na przykład \"a\" lub \"a,c\"";

            regex.append("[").append(smallFirstLetter).append("-").append(smallLastLetter).append(bigFirstLetter).append("-").append(bigLastLetter).append("]{1}");
            for (int i = 0; i < allAnswers.size() - 1; i++) {
                regex.append("(,[").append(smallFirstLetter).append("-").append(smallLastLetter).append(bigFirstLetter).append("-").append(bigLastLetter).append("]{1})?");
            }
        }

        String userAnswer = getInputFromUser(message, regex.toString(), "Niepoprawny format odpowiedzi. Spróbuj jeszcze raz!");

        List<Character> userAnswerList = new ArrayList<>();
        for (String string : userAnswer.split(",")) {
            userAnswerList.add(string.charAt(0));
        }
        return userAnswerList;
    }

    public boolean checkAnswer(List<Character> userAnswerLetters, List<Character> correctAnswerLettersList) {
//        for (Character letter : correctAnswerLettersList) {
//            if (!userAnswerLetters.contains(letter)) {
//                return false;
//            }
//        }
//        for (Character letter : userAnswerLetters) {
//            if (!correctAnswerLettersList.contains(letter)) {
//                return false;
//            }
//        }
        return userAnswerLetters.containsAll(correctAnswerLettersList) && correctAnswerLettersList.containsAll(userAnswerLetters);
    }

    public void displayResults(boolean result, List<Question.Answer> answerList) {
        System.out.println("=============================================================");
        if (result) {
            System.out.println("Odpowiedź prawidłowa!");
            Util.makeDelay(1000);
        } else {
            System.out.println("Odpowiedź nie prawidłowa... :(");
            Util.makeDelay(1000);
            System.out.println("Poprawna(-e) odpowiedź(-i) to:");
            for (Question.Answer answer : answerList) {
                if (answer.isCorrect()) {
                    System.out.println(answer);
                }
            }
            Util.makeDelay(1500);
        }
        System.out.println("=============================================================");
        System.out.println("");
    }
}
