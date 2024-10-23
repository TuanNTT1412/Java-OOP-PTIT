package Comic_Reading_Platform;

import java.util.ArrayList;
import java.util.List;

public class AdminManager {
    private List<User> users;
    private List<Comic> comics;

    public AdminManager() {
        users = new ArrayList<>(); 
        comics = new ArrayList<>();
    }

    public void uploadComic(Comic comic) {
        // Method to upload a comic
        comics.add(comic);
    }

    public void editComic(String comicId, String newTitle, String newAuthor) {
        // Method to edit a comic
        for (Comic comic : comics) {
            if (comic.getComicID().equals(comicId)) {
                comic.setTitle(newTitle);
                comic.setAuthor(newAuthor);
                break;
            }
        }
    }

    public void deleteComic(String comicId) {
        // Method to delete a comic
        comics.removeIf(comic -> comic.getComicID().equals(comicId));
    }

    public void editUserAccount(String userId, String newUsername, String newEmail) {
        for (User user : users) {
            if (user.getUserID().equals(userId)) {
                user.editUser(newUsername, newEmail);
                break;
            }
        }
    }

    public void deleteUserAccount(String userId) {
        for (User user : users) {
            if (user.getUserID().equals(userId)) {
                user.deleteUser();
                users.remove(user);
                break;
            }
        }
    }

    public void modifyRating(Comic comic, int newRating) {
        // Method to modify comic rating
    }
    // Other methods if needed...
}
