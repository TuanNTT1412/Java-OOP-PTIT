package comic.model;

import java.util.ArrayList;
import java.util.List;

public class Comic {
    private String comicId;
    private String title;
    private String author; 
    private String category;
    private Status status;
    private List<Chapter> chapterList;

    private static int comicCount = 0;

    public Comic(String title, String author, String category, Status status) {
        comicCount++;
        this.comicId = "#" + comicCount;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.chapterList = new ArrayList<>();
    }

    public String getComicId() {
        return comicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void addChapter(Chapter chapter) {
        chapterList.add(chapter);
    }

    public void removeChapter(Chapter chapter) {
        chapterList.remove(chapter);
    }
    
    public void displayComic() {

    }

    public void updateComic(String newTitle, String newAuthor, String newCategory, Status newStatus) {
        this.title = newTitle;
        this.author = newAuthor;
        this.category = newCategory;
        this.status = newStatus;
    }
}
