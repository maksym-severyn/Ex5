package pl.isa;

import java.util.ArrayList;
import java.util.List;

public class UsersBase {
    private List<User> users;

    public UsersBase() {
        users = loadUsersFromBase();
    }

    public UsersBase(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUserToList(User user){
        if (this.users == null){
            this.users = new ArrayList<>();
        }
        this.users.add(user);
    }

    public static List<User> loadUsersFromBase(){
        List<User> userList = new ArrayList<>();
        //TODO: create method to load users from base to list;
        return userList;
    }

    public void loadUsersToBase(List<User> users) {
        this.users = users;
    }
}
