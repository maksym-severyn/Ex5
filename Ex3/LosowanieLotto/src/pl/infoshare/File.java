package pl.infoshare;

import java.io.IOException;
import java.nio.file.Files;

public class File {
    public static void createNewFile() throws IOException {
        //check if this created file already exists and delete it
        deleteExistingFile();

        Files.createFile(Main.myPath);
    }

    private static void deleteExistingFile() {
        java.io.File myFile = new java.io.File(Main.FILE_NAME);
        myFile.delete();
    }

    public static void writeResultsToFile(RandomProgram myProgram) throws IOException {

        StringBuilder stringOfTableNumbers = new StringBuilder();
        for (int i = 0; i < myProgram.randomNumbersTable.length; i++) {
            stringOfTableNumbers.append(myProgram.randomNumbersTable[i]);
            stringOfTableNumbers.append(";");
        }

        byte[] resultsToBytes = stringOfTableNumbers.toString().getBytes();
        Files.write(Main.myPath, resultsToBytes);

    }
}
