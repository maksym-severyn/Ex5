package pl.isa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pl.isa.Jackson.CreateObjectMapper;
import pl.isa.Question.Question;
import pl.isa.Question.QuestionCategory;
import pl.isa.Question.QuestionType;
import pl.isa.comparators.RandomSorting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ServiceQuestionsAndUsers<E> {
    public static final ObjectMapper OBJECT_MAPPER = new CreateObjectMapper().getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            E e;

    public void writeObjectToBase(E object, String path){
        try {
            if (object instanceof User){
                OBJECT_MAPPER.writeValue(new FileWriter(path + "/U" + ((User) object).getId() + ".json"), object);
            } else if (object instanceof Question){
                OBJECT_MAPPER.writeValue(new FileWriter(path + "/Q" + ((Question) object).getId() + ".json"), object);
            } else {
                OBJECT_MAPPER.writeValue(new FileWriter(path + "/" + object.toString() + ".json"), object);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<E> readObjectsFromBase(Class<E> theClass, String path){
        List<E> objectList = new ArrayList<>();

        File file = new File(path);
        ArrayList<File> directory = null;
        if (file.isDirectory()){
            directory = new ArrayList<>(Arrays.asList(file.listFiles()));
        }

        directory.forEach(f -> {
            try {
                objectList.add(OBJECT_MAPPER.readValue(f, theClass));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        return objectList;
    }

    public void randomSortingQuestions(List<E> list) {
        //randomize 3 times (just for fun :) )
        for (int i = 0; i < 3; i++) {
            Collections.sort(list, new RandomSorting());
        }
    }

    public List<Question> specifyQuestionsAccordingToCategoryAndType(QuestionCategory category, QuestionType type, List<Question> questions) {
        Iterator<Question> iterator = questions.iterator();
        while (iterator.hasNext()) {
            Question question = iterator.next();
            if (!(question.getQuestionType().equals(type)) || !(question.getQuestionCategory().equals(category))) {
                iterator.remove();
            }
        }
        return questions;
    }

    public List<Question.Answer> getAnswerListFromQuestion(Question question){
        List<Question.Answer> answers = new ArrayList<>();
        for (String answer : question.getQuestionAndAnswers().keySet()) {
            Iterator<Question.Answer> iterator =  question.getQuestionAndAnswers().get(answer).iterator();
            while (iterator.hasNext()){
                Question.Answer thisAnswer = iterator.next();
                answers.add(thisAnswer);
            }
        }
        return answers;
    }

    public List<Character> getCorrectAnswerLettersList(List<Question.Answer> answers){
        List<Character> correctAnswerLetters = new ArrayList<>();
        for (Question.Answer answer : answers) {
            if (answer.isCorrect()){
                correctAnswerLetters.add(answer.getLetter());
            }
        }
        return correctAnswerLetters;
    }

    public List<Character> getAllAnswerLettersList(List<Question.Answer> answers){
        List<Character> allAnswerLetters = new ArrayList<>();
        for (Question.Answer answer : answers) {
            allAnswerLetters.add(answer.getLetter());
        }
        return allAnswerLetters;
    }
}
