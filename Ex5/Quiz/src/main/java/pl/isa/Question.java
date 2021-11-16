package pl.isa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Question {
    private long id;
    private Map<String, Set<Answer>> questionAndAnswers;
    private QuestionCategory questionCategory;
    private QuestionType questionType;

    public Question(long id, QuestionCategory questionCategory, String questionContent, Answer... answers) {
        this.id = id;
        this.questionCategory = questionCategory;

        Set<Answer> answersForQuestion = new TreeSet<>();
        for (Answer answer : answers) {
            answersForQuestion.add(answer);
        }
        questionAndAnswers = new TreeMap<>();
        questionAndAnswers.put(questionContent,answersForQuestion);

        assignQuestionType();
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

//    private long generateId(){
//
//    }


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

    public void loadQuestionToBase(){
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new FileWriter(Main.QUESTIONS_BASE_PATH + "/Q" + getId() + ".json"), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    static class Answer implements Comparable<Answer>{
        private Character letter;
        private String answer;
        private boolean isCorrect;

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


