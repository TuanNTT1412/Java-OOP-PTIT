package Model;

public class Chapter {
    private String comicID;
    private int chapterNumber;
    private String title;

    public Chapter(String comicID, int chapterNumber, String title) {
        this.comicID = comicID;
        this.chapterNumber = chapterNumber;
        this.title = title;
    }

    public String getComicID() {
        return comicID;
    }
    
    public void setComicID(String comicID) {
        this.comicID = comicID;
    }
    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
