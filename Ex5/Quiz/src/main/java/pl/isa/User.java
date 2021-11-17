package pl.isa;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static pl.isa.Main.QUESTION_SERVICE;

public class User {
    private long id;
    private String nameAndSurname;
    private Quiz quiz;

    //the default constructor needs for correct working of Jackson
    //TODO: be sure that this is private
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

    private long generateId(){
        List<User> userList = Main.USER_SERVICE.readObjectsFromBase(User.class, Main.USERS_BASE_PATH);
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
}
