package pl.isa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionService {

    public static void loadQuestionToBase(Question question){
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new FileWriter(Main.QUESTIONS_BASE_PATH + "/Q" + question.getId() + ".json"), question);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Question> loadQuestionsFromBase(){
        List<Question> questionPool = new ArrayList<>();
        File file = new File(Main.QUESTIONS_BASE_PATH);

        ArrayList<File> directory = null;
        if (file.isDirectory()){
            directory = new ArrayList<>(Arrays.asList(file.listFiles()));
        }

        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        directory.forEach(f -> {
            try {
                questionPool.add(objectMapper.readValue(f, Question.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return questionPool;
    }

    public static void deleteQuestionsFromBase(long questionID){
        File directoryFile = new File(Main.QUESTIONS_BASE_PATH);
        ArrayList<File> directory = null;
        if (directoryFile.isDirectory()){
            directory = new ArrayList<>(Arrays.asList(directoryFile.listFiles()));
        }

        for (File file : directory) {
            if (file.getName().equals("Q" + questionID + ".json")){
                file.delete();
                break;
            }
        }
    }
}
