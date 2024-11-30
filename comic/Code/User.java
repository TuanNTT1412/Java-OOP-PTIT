package comic.model;

public class User {
    private String userId;
    private String username;
    private String password;
    private Role role;

    private static int userCount = 1;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        if (role == Role.ADMIN) {
            this.userId = "#0";
        } else {
            this.userId = "#" + userCount;
            userCount++;
        }
    }

    public String getUserId() {
        return userId;
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
    
    public Role getRole() {
        return role;
    }


    public void displayUserInfo() {

    }

    public void updateUserInfo() {

    }

    public void deleteUser() {

    }

    public void register() {

    }

    public void login() {

    }

    public void logout() {

    }
}
