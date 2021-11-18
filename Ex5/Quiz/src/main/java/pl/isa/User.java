package pl.isa;

import java.util.Collections;
import java.util.List;

import static pl.isa.Main.USER_SERVICE;

public class User implements Comparable<User> {
    private long id;
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

    public Quiz getQuiz() {
        return quiz;
    }

    private long generateId() {
        List<User> userList = USER_SERVICE.readObjectsFromBase(User.class, Main.USERS_BASE_PATH);
        Collections.sort(userList, Collections.reverseOrder());
        if (userList == null || userList.size() == 0) {
            return 1;
        } else {
            return userList.get(0).getId() + 1;
        }
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public int compareTo(User o) {
        return Integer.valueOf(String.valueOf(this.id - o.getId()));
    }
}
