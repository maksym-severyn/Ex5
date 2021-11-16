package pl.isa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Question implements Comparable<Question> {
    private long id;
    private Map<String, Set<Answer>> questionAndAnswers;
    private Set<Map.Entry<String,Set<Answer>>> qwe;
    private QuestionCategory questionCategory;
    private QuestionType questionType;

    //the default constructor needs for correct working of Jackson
    private Question() {
    }

    public Question(QuestionCategory questionCategory, String questionContent, Answer... answers) {
        this.id = generateId();
        this.questionCategory = questionCategory;

        Set<Answer> answersForQuestion = new TreeSet<>();
        for (Answer answer : answers) {
            answersForQuestion.add(answer);
        }
        questionAndAnswers = new TreeMap<>();
        questionAndAnswers.put(questionContent,answersForQuestion);

        assignQuestionType();
        QuestionService.loadQuestionToBase(this);
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
        List<Question> questionList = QuestionService.loadQuestionsFromBase();
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

    public Map<String, Set<Answer>> getQuestionAndAnswers() {
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
        public int compareTo(Answer o) {
            return this.letter.compareTo(o.getLetter());
        }
    }
}


