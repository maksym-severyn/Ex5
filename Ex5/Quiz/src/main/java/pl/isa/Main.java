package pl.isa;

import pl.isa.question.Question;
import pl.isa.question.QuestionService;
import pl.isa.user.User;
import pl.isa.user.UserService;
import pl.isa.util.Randomizer;
import pl.isa.util.ReturnToStartException;

public class Main {
    public static final String QUESTIONS_BASE_PATH = "src/main/resources/QuestionsBase";
    public static final String USERS_BASE_PATH = "src/main/resources/UsersBase";
    public static final Integer COUNT_OF_QUESTION_TO_BE_DISPLAYED = 3;

    public static final QuestionService QUESTION_SERVICE = new QuestionService();
    public static final UserService USER_SERVICE = new UserService();
    public static final Randomizer RANDOMIZER = new Randomizer();

    public static Integer COUNT_OF_USERS_IN_BASE = USER_SERVICE.readObjectsFromBase(User.class, Main.USERS_BASE_PATH).size();
    public static Integer COUNT_OF_QUESTIONS_IN_BASE = QUESTION_SERVICE.readObjectsFromBase(Question.class, Main.QUESTIONS_BASE_PATH).size();

    public static void main(String[] args) throws ReturnToStartException {
        Initialization.fillBaseWithDefaultQuestions();

        Quiz quiz = new Quiz();
        quiz.run(COUNT_OF_QUESTION_TO_BE_DISPLAYED);
    }
}