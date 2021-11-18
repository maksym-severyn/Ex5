package pl.isa;

import pl.isa.question.Question;
import pl.isa.question.QuestionCategory;
import pl.isa.question.QuestionPool;
import pl.isa.question.QuestionType;
import pl.isa.user.User;
import pl.isa.util.ReturnToStartException;
import pl.isa.util.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pl.isa.Main.QUESTION_SERVICE;
import static pl.isa.Main.USER_SERVICE;

public class Quiz {
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

    //all getters/setters needs for correct working of Jackson

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

    public void run(int countOfQuestionToBeDisplayed) throws ReturnToStartException {
        QuestionPool questionPool = new QuestionPool();
        List<Question> wholeQuestionBase = questionPool.getQuestionList();

        Display display = new Display();
        User user = null;

        user = new User(display.selectAuthorization());
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

            display.displayQuestion(parameterizedQuestionList.get(i), i+1);
            Boolean isCorrectAnswer = null;
            try {
                isCorrectAnswer = display.checkAnswer(display.getAnswerFromUser(typeOfQuestion, allAnswerLettersList),correctAnswerLettersList);
            } catch (ReturnToStartException e) {
                System.exit(0);
            }
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

        USER_SERVICE.writeObjectToBase(user, "U" + user.getId(), Main.USERS_BASE_PATH);
    }
}
