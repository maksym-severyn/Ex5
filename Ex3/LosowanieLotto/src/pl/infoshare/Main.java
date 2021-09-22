package pl.infoshare;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static final String FILE_NAME = "src/pl/infoshare/newFile.txt";
    public static Path myPath = Paths.get("src", "pl", "infoshare", "newFile.txt");

    public static void main(String[] args) throws InterruptedException, IOException {


        Path myPath = Paths.get("src", "pl", "infoshare", "newFile.txt");
        File.createNewFile();

        new RandomProgram().run();

    }

}
