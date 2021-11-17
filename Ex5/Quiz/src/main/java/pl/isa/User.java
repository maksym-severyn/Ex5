package pl.isa;

import java.time.LocalDateTime;

public class User {
    private long id;
    private String nameAndSurname;
    private LocalDateTime dateOfQuiz;
    private Quiz quiz;

    //the default constructor needs for correct working of Jackson
    //TODO: be sure that this is private
    public User() {
    }

    public User(long id, String nameAndSurname) {
        this.id = id;
        this.nameAndSurname = nameAndSurname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public LocalDateTime getDateOfQuiz() {
        return dateOfQuiz;
    }

    public void setDateOfQuiz(LocalDateTime dateOfQuiz) {
        this.dateOfQuiz = dateOfQuiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
