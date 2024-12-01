package Model;

public class Comic{

    private static int idCounter = 1;

    private String comicID;
    private String comicName;
    private String comicStatus; 
    private String comicCategory;
    private String comicAuthor; 


    public Comic() {
        this.comicID = String.valueOf(idCounter++);
    }

    public Comic(String comicName, String comicStatus, String comicCategory, String comicAuthor) {
        this.comicID = String.valueOf(idCounter++);
        this.comicName = comicName;
        this.comicStatus = comicStatus;
        this.comicCategory = comicCategory;
        this.comicAuthor = comicAuthor;
    }

    public Comic(String comicID, String comicName, String comicStatus, String comicCategory, String comicAuthor) {
        this.comicID = comicID;
        this.comicName = comicName;
        this.comicStatus = comicStatus;
        this.comicCategory = comicCategory;
        this.comicAuthor = comicAuthor;
    }

    public String getComicID() {
        return comicID;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getComicCategory() {
        return comicCategory;
    }

    public void setComicCategory(String comicCategory) {
        this.comicCategory = comicCategory;
    }

   public String getComicStatus() {
        return comicStatus;
    }

    public void setComicStatus(String comicStatus) {
        this.comicStatus = comicStatus;
    }

    public String getComicAuthor() {
        return comicAuthor;
    }

    public void setComicAuthor(String comicAuthor) {
        this.comicAuthor = comicAuthor;
    }

    @Override
    public String toString() {
        return "ComicDAO{"
                + "comicID='" + comicID + '\''
                + ", comicName='" + comicName + '\''
                + ", comicCategory='" + comicCategory + '\''
                + ", comicStatus=" + comicStatus + '\''
                + ", comicAuthor=" + comicAuthor
                + '}';
    }
}
