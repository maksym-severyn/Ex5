package pl.isa;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.isa.util.ReturnToStartException;
import pl.isa.util.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pl.isa.Main.QUESTION_SERVICE;
import static pl.isa.Main.USER_SERVICE;

public class Quiz {
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime dateOfQuiz;
    private List<Question> assignedQuestions;
    private QuestionType selectedQuestionType;
    private QuestionCategory selectedQuestionCategory;
    private int correctAnswers;
    private int incorrectAnswers;

    public Quiz() {
        correctAnswers = 0;
        incorrectAnswers = 0;
        this.assignedQuestions = new ArrayList<>();
    }

    public LocalDateTime getDateOfQuiz() {
        return dateOfQuiz;
    }

    public void setDateOfQuiz(LocalDateTime dateOfQuiz) {
        this.dateOfQuiz = dateOfQuiz;
    }

    public List<Question> getAssignedQuestions() {
        return assignedQuestions;
    }

    public void setAssignedQuestions(List<Question> assignedQuestions) {
        this.assignedQuestions = assignedQuestions;
    }

    public QuestionType getSelectedQuestionType() {
        return selectedQuestionType;
    }

    public void setSelectedQuestionType(QuestionType selectedQuestionType) {
        this.selectedQuestionType = selectedQuestionType;
    }

    public QuestionCategory getSelectedQuestionCategory() {
        return selectedQuestionCategory;
    }

    public void setSelectedQuestionCategory(QuestionCategory selectedQuestionCategory) {
        this.selectedQuestionCategory = selectedQuestionCategory;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(int incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public void run(int countOfQuestionToBeDisplayed) {
        QuestionPool questionPool = new QuestionPool();
        List<Question> wholeQuestionBase = questionPool.getQuestionList();

        Display display = new Display();
        User user = null;

        try {
            user = new User(display.selectAuthorization());
        } catch (ReturnToStartException e) {
            e.getMessage();
            run(countOfQuestionToBeDisplayed);
        }
        this.selectedQuestionCategory = display.selectQuestionCategory();
        this.selectedQuestionType = display.selectQuestionType();

        List<Question> parameterizedQuestionList = QUESTION_SERVICE.specifyQuestionsAccordingToCategoryAndType(selectedQuestionCategory, selectedQuestionType, wholeQuestionBase);

        QUESTION_SERVICE.randomSortingQuestions(parameterizedQuestionList);

        System.out.println("Super! Gotowy(-a)?");
        Util.makeDelay(1000);

        for (int i = 0; i < countOfQuestionToBeDisplayed; i++) {
            this.assignedQuestions.add(parameterizedQuestionList.get(i));
            QuestionType typeOfQuestion = parameterizedQuestionList.get(i).getQuestionType();
            List<Question.Answer> answerList = Main.QUESTION_SERVICE.getAnswerListFromQuestion(parameterizedQuestionList.get(i));
            List<Character> allAnswerLettersList = Main.QUESTION_SERVICE.getAllAnswerLettersList(answerList);
            List<Character> correctAnswerLettersList = Main.QUESTION_SERVICE.getCorrectAnswerLettersList(answerList);
            boolean isCorrectAnswer;

            display.displayQuestionAndGetResult(parameterizedQuestionList.get(i), i+1);
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
        this.dateOfQuiz = LocalDateTime.now();

        user.setQuiz(this);


        //tutaj jest problem!!!!!
        USER_SERVICE.writeObjectToBase(user,Main.USERS_BASE_PATH);

    }
}
