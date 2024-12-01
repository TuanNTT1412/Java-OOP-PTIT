package Model;

public class User{
    public static int idCounter = 1;
    private String userID;
    private String username;
    private String password;

    public User() {
        this.userID = String.valueOf(idCounter++);
    }

    public User(String username, String password) {
        this.userID = String.valueOf(idCounter++);
        this.username = username;
        this.password = password;
    }

    public User(String userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return userID + "," + username + "," + password;
    }
}
