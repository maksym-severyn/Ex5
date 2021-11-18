package pl.isa.user;

import pl.isa.Main;
import pl.isa.Quiz;

import java.util.Collections;
import java.util.List;

import static pl.isa.Main.COUNT_OF_USERS_IN_BASE;
import static pl.isa.Main.USER_SERVICE;

public class User implements Comparable<User> {
    private Integer id;
    private String nameAndSurname;
    private Quiz quiz;

    //the default constructor needs for correct working of Jackson
    //the same with all getters/setters
    private User() {
    }

    public User(String nameAndSurname) {
        this.id = generateId();
        this.nameAndSurname = nameAndSurname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    private Integer generateId() {
        if (COUNT_OF_USERS_IN_BASE == 0) {
            return 1;
        } else {
            return ++COUNT_OF_USERS_IN_BASE;
        }
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public int compareTo(User o) {
        return this.id - o.getId();
    }
}
