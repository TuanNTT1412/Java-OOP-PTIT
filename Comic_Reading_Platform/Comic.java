package Comic_Reading_Platform;

public class Comic {
    private String comicID;
    private String title;
    private String author;
    private String genre;
    private String status;
    private String content;

    private static int cnt = 1;

    public Comic(String title, String author, String genre, String status, String content) {
        this.comicID = String.format("#%06d", cnt++);
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.content = content;
    }

    public String getComicID() {
        return comicID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void viewComicInfo() {   
        System.out.println("Comic ID: " + comicID);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Status: " + status);
        System.out.println("Content: " + content);
    }
    // Other methods if needed...
}
