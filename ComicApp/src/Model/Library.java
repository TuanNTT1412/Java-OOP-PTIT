package Model;

import java.util.Set;

public class Library {

    private String userID;
    private Set<String> followedComicIDs;

    public Library(String userID, Set<String> followedComicIDs) {
        this.userID = userID;
        this.followedComicIDs = followedComicIDs;
    }

    public String getUserID() {
        return userID;
    }

    public Set<String> getFollowedComicIDs() {
        return followedComicIDs;
    }

    public void setFollowedComicIDs(Set<String> followedComicIDs) {
        this.followedComicIDs = followedComicIDs;
    }

    @Override
    public String toString() {
        return "Library{" +
                "userID='" + userID + '\'' +
                ", followedComicIDs=" + followedComicIDs +
                '}';
    }
}