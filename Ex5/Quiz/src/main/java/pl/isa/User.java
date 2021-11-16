package pl.isa;

import java.time.LocalDateTime;

public class User {
    private long id;
    private String nameAndSurname;
    private LocalDateTime dateOfQuiz;
    private Quiz quiz;

    public User(long id, String nameAndSurname) {
        this.id = id;
        this.nameAndSurname = nameAndSurname;
    }
}
