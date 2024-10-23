package Comic_Reading_Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MemberAction {
    private List<Comic> comicLibrary;
    private List<Comic> readingHistory; 
    private HashMap<Comic, Integer> comicRatings;
    private HashSet<String> genreHistory;

    public MemberAction() {
        comicLibrary = new ArrayList<>();
        readingHistory = new ArrayList<>(); 
        comicRatings = new HashMap<>(); 
        genreHistory = new HashSet<>(); 
    }

    public void readComic() {
        // Logic to read a comic and store the genre for recommendations
    }

    public void rateComic() {
        // Logic to save the rating
    }

    public void viewLibrary() {
        // Logic to display the comic library
    }

    public void addComicToLibrary() {
        // Logic to add comic to the library
    }

    public void removeComicFromLibrary() {
        // Logic to remove comic from the library
    }

    public void viewRecommendations() {
        // Logic to provide comic recommendations
    }

    public void viewReadingHistory() {
        // Logic to display the reading history
    }
    // Other methods if needed...
}
