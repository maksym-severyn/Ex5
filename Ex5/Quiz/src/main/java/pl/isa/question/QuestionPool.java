package pl.isa.question;

import pl.isa.Main;

import java.util.List;


public class QuestionPool {
    private List<Question> questionList;

    public QuestionPool() {
        this.questionList = Main.QUESTION_SERVICE.readObjectsFromBase(Question.class, Main.QUESTIONS_BASE_PATH);
    }

    public List<Question> getQuestionList() {
        return questionList;
    }
}
