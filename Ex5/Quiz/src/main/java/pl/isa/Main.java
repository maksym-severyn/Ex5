package pl.isa;

import pl.isa.Question.Question;
import pl.isa.util.Randomizer;

public class Main {
    public static final String QUESTIONS_BASE_PATH = "src/main/resources/QuestionsBase";
    public static final String USERS_BASE_PATH = "src/main/resources/UsersBase";
    public static final Integer COUNT_OF_QUESTION_TO_BE_DISPLAYED = 3;

    public static final ServiceQuestionsAndUsers<Question> QUESTION_SERVICE = new ServiceQuestionsAndUsers<>();
    public static final ServiceQuestionsAndUsers<User> USER_SERVICE = new ServiceQuestionsAndUsers<>();
    public static final Randomizer RANDOMIZER = new Randomizer();

    public static void main(String[] args) {
        Initialization.fillBaseWithDefaultQuestions();

        Quiz quiz = new Quiz();
        quiz.run(COUNT_OF_QUESTION_TO_BE_DISPLAYED);
    }
}