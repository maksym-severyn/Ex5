package pl.isa;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Question implements Comparable<Question> {

    private long id;
    private Map<String, TreeSet<Answer>> questionAndAnswers;
    private QuestionCategory questionCategory;
    private QuestionType questionType;

    //the default constructor needs for correct working of Jackson
    //TODO: be sure that this is private
    public Question() {
    }

    public Question(QuestionCategory questionCategory, String questionContent, Answer... answers) {
        this.id = generateId();
        this.questionCategory = questionCategory;

        TreeSet<Answer> answersForQuestion = new TreeSet<>();
        for (Answer answer : answers) {
            answersForQuestion.add(answer);
        }
        questionAndAnswers = new TreeMap<>();
        questionAndAnswers.put(questionContent,answersForQuestion);

        assignQuestionType();
        Main.QUESTION_SERVICE.writeObjectToBase(this, Main.QUESTIONS_BASE_PATH);
    }

    private void assignQuestionType(){
        AtomicInteger countOfCorrectAnswers = new AtomicInteger();
        this.questionAndAnswers.values().forEach(n -> {
            Iterator<Answer> answerIterator = n.iterator();
            while (answerIterator.hasNext()){
                if(answerIterator.next().isCorrect){
                    countOfCorrectAnswers.getAndIncrement();
                }
            }
        });

        if (countOfCorrectAnswers.get() > 1){
            this.questionType = QuestionType.MULTIPLE_CHOICE;
        } else {
            this.questionType = QuestionType.SINGLE_CHOICE;
        }
    }

    private long generateId(){
        List<Question> questionList = Main.QUESTION_SERVICE.readObjectsFromBase(Question.class, Main.QUESTIONS_BASE_PATH);
        Collections.sort(questionList, Collections.reverseOrder());
        if (questionList == null || questionList.size() == 0) {
            return 1;
        } else {
            return questionList.get(0).getId() + 1;
        }
    }

    public long getId() {
        return id;
    }

    public Map<String, TreeSet<Answer>> getQuestionAndAnswers() {
        return questionAndAnswers;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }


    @Override
    public int compareTo(Question o) {
        return Integer.valueOf(String.valueOf(this.id - o.getId()));
    }

    static class Answer implements Comparable<Answer>{
        private Character letter;
        private String answer;
        private boolean isCorrect;

        //the default constructor needs for correct working of Jackson
        private Answer() {
        }

        public Answer(char letter, String answer, boolean isCorrect) {
            this.letter = letter;
            this.answer = answer;
            this.isCorrect = isCorrect;
        }

        public Character getLetter() {
            return letter;
        }

        public void setLetter(Character letter) {
            this.letter = letter;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public boolean isCorrect() {
            return isCorrect;
        }

        public void setCorrect(boolean correct) {
            isCorrect = correct;
        }

        //answers with the same content will be considered as equals
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Answer a = (Answer) o;
            return answer.equals(a.answer);
        }

        @Override
        public int hashCode() {
            return Objects.hash( answer);
        }

        @Override
        public String toString() {
            return letter + ") " + answer;
        }

        @Override
        public int compareTo(Answer o) {
            return this.letter.compareTo(o.getLetter());
        }
    }
}


