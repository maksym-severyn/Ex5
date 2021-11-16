package pl.isa;

import java.util.List;


public class QuestionPool {
    private List<Question> questionList;

    public QuestionPool() {
        this.questionList = QuestionService.loadQuestionsFromBase();
    }


}
