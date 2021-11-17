package pl.isa;

import pl.isa.util.ReturnToStartException;
import pl.isa.util.Util;

import java.util.List;

import static pl.isa.Main.QUESTION_SERVICE;

public class Quiz {
    private QuestionPool questionPool;
    private List<Question> assignedQuestions;
    private QuestionType selectedQuestionType;
    private QuestionCategory selectedQuestionCategory;
    private int correctAnswers;
    private int incorrectAnswers;

    public Quiz() {
        questionPool = new QuestionPool();
        correctAnswers = 0;
        incorrectAnswers = 0;
    }

    public void run(int countOfQuestionToBeDisplayed) {
        List<Question> wholeQuestionBase = questionPool.getQuestionList();

        Display display = new Display();
        User user = new User();

        try {
            user.setNameAndSurname(display.selectAuthorization());
        } catch (ReturnToStartException e) {
            e.getMessage();
            run(countOfQuestionToBeDisplayed);
        }
        this.selectedQuestionCategory = display.selectQuestionCategory();
        this.selectedQuestionType = display.selectQuestionType();

        this.assignedQuestions = QUESTION_SERVICE.specifyQuestionsAccordingToCategoryAndType(selectedQuestionCategory, selectedQuestionType, wholeQuestionBase);

        QUESTION_SERVICE.randomSortingQuestions(assignedQuestions);

        System.out.println("Super! Gotowy(-a)?");
        Util.makeDelay(1000);

        for (int i = 0; i < countOfQuestionToBeDisplayed; i++) {
            QuestionType typeOfQuestion = assignedQuestions.get(i).getQuestionType();
            List<Question.Answer> answerList = Main.QUESTION_SERVICE.getAnswerListFromQuestion(assignedQuestions.get(i));
            List<Character> allAnswerLettersList = Main.QUESTION_SERVICE.getAllAnswerLettersList(answerList);
            List<Character> correctAnswerLettersList = Main.QUESTION_SERVICE.getCorrectAnswerLettersList(answerList);
            boolean isCorrectAnswer;

            display.displayQuestionAndGetResult(assignedQuestions.get(i), i+1);
            isCorrectAnswer = display.checkAnswer(display.getAnswerFromUser(typeOfQuestion, allAnswerLettersList),correctAnswerLettersList);
            if(isCorrectAnswer){
                this.correctAnswers++;
            } else {
                this.incorrectAnswers++;
            }
            display.displayResults(isCorrectAnswer, answerList);
        }
        System.out.println("\n=======================KONIEC TESTU=======================");
        System.out.println("Na " + (correctAnswers + incorrectAnswers) + " pytań udzielono: " + correctAnswers + " poprawnych odpowiedzi");

    }
}
