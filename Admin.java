package Comic_Reading_Platform;

public class Admin extends User {
    private AdminManager adminManager;

    public Admin(String username, String password, String email) {
        super(username, password, email, User.ROLE_ADMIN); 
        adminManager = new AdminManager();
    }

    public void manageUserAccount() {
        // Manage member accounts
    }

    public void manageComic() {
        // Manage comic content
    }

    public void manageRatings() {
        // Manage ratings
    }
    // Other methods if needed...
}
