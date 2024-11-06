package comic.model;

import java.util.ArrayList;
import java.util.List;

public class Member extends User {
    private List<Comic> library;
    private List<Comic> bookmark;
    private List<Comic> history;

    public Member(String username, String password, Role role) {
        super(username, password, role);
        this.library = new ArrayList<>();
        this.bookmark = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    public List<Comic> getLibrary() {
        return library;
    }

    public List<Comic> getBookmark() {
        return bookmark;
    }

    public List<Comic> getHistory() {
        return history;
    }

    public void search() {

    }

    public void read() {

    }

    public void addComicToLibrary(Comic comic) {
        library.add(comic);
    }

    public void removeComicFromLibrary(Comic comic) {
        library.remove(comic);
    }

    public void viewLibrary() {

    }

    public void addBookmark(Comic comic) {
        bookmark.add(comic);
    }

    public void removeBookmark(Comic comic) {
        bookmark.remove(comic);
    }

    public void viewBookmark() {

    }

    public void viewHistory() {

    }
}
