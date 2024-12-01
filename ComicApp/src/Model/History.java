package Model;

import java.util.Set;

public class History {
    private String userID;
    private Set<String> comicIDs;

    // Constructor
    public History(String userID, Set<String> comicIDs) {
        this.userID = userID;
        this.comicIDs = comicIDs;
    }

    public String getUserID() {
        return userID;
    }

    public Set<String> getComicIDs() {
        return comicIDs;
    }

    public void setComicIDs(Set<String> comicIDs) {
        this.comicIDs = comicIDs;
    }

    @Override
    public String toString() {
        return "History{" +
                "userID='" + userID + '\'' +
                ", comicIDs=" + comicIDs +
                '}';
    }
}
