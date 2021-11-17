package pl.isa;

import java.util.List;

public class Main {
    public static final String QUESTIONS_BASE_PATH = "src/main/resources/QuestionsBase";
    public static final String USERS_BASE_PATH = "src/main/resources/UsersBase";
    public static final Integer COUNT_OF_QUESTION_TO_BE_DISPLAYED = 3;

    public static final ServiceQuestionsAndUsers<Question> QUESTION_SERVICE = new ServiceQuestionsAndUsers<>();
    public static final ServiceQuestionsAndUsers<User> USER_SERVICE = new ServiceQuestionsAndUsers<>();
    public static final Randomizer RANDOMIZER = new Randomizer();

    public static void main(String[] args) {
        Initialization.fillBaseWithDefaultQuestions();

//        List<Question> questions = QUESTION_SERVICE.readObjectsFromBase(Question.class, QUESTIONS_BASE_PATH);
//        Display display = new Display();
//        QUESTION_SERVICE.randomSortingQuestions(questions);
//        display.displayQuestionAndGetResult(questions.get(0), 1);

        Quiz quiz = new Quiz();
        quiz.run(COUNT_OF_QUESTION_TO_BE_DISPLAYED);

    }
}
