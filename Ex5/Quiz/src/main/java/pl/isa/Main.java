package pl.isa;

public class Main {
    public static final String QUESTIONS_BASE_PATH = "src/main/resources/QuestionsBase";
    public static void main(String[] args) {
        Question question1 = new Question(1,QuestionCategory.JAVA_SE,"Język Java cechuje się tym, że:",
                new Question.Answer('a',"Jest językiem stricte programowania funkcyjnego",false),
                new Question.Answer('b',"Jest językiem stricte programowania obiektowego",true),
                new Question.Answer('c',"Zawiera elementy programowania funkcyjnego",true)
        );


    }
}
