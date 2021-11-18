package pl.isa.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import pl.isa.jackson.CreateObjectMapper;
import pl.isa.comparators.RandomSorting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class Service<E> {
    public static final ObjectMapper OBJECT_MAPPER = new CreateObjectMapper().getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            E e;

    public void writeObjectToBase(E object, String fileName, String path) {
        try {
            OBJECT_MAPPER.writeValue(new FileWriter(path + "/" + fileName + ".json"), object);
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
}
