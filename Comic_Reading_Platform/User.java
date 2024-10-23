package Comic_Reading_Platform;

public class User {
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_MEMBER = "member";

    private static int cnt = 1;
    private String userID;    
    private String username;   
    private String password;   
    private String email;      
    private String role;       

    public User(String username, String password, String email, String role) {
        this.userID = String.format("#%05d", cnt++);
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
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
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public boolean login(String username, String password) {
        if (getUsername().equals(username) && this.password.equals(password)) {
            System.out.println("Login successful!");
            if (getRole().equals(ROLE_ADMIN)) {
                System.out.println("Welcome Admin!");
            } else {
                System.out.println("Welcome " + getUsername() + "!");
            }
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    public void logout() {
        System.out.println("User logged out.");
    }

    public void editUser(String newUsername, String newEmail) {
        if (newUsername != null && !newUsername.isEmpty()) {
            setUsername(newUsername);
        }
        if (newEmail != null && !newEmail.isEmpty()) {
            setEmail(newEmail);
        }
    }

    public void deleteUser() {
        // Logic to delete the user
        this.userID = null;
        this.username = null;
        this.email = null;
        this.password = null;
        // More logic can be added
    }
    
    public void viewPersonalInfo() {
        System.out.println("User ID: " + getUserID());
        System.out.println("Username: " + getUsername());
        System.out.println("Email: " + getEmail());
        System.out.println("Role: " + getRole());
    }
    // Other methods if needed...
}
