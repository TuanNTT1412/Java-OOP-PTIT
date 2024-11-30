package comic.model;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> userList = new ArrayList<>();

    public void addUser(User user) {
        userList.add(user);
    }

    public void removeUser(User user) {
        userList.remove(user);
    }

    public int getUserCount() {
        return userList.size();
    }
    
    public void displayUsersList() {

    }
}
