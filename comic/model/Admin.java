package comic.model;

public class Admin extends Member {
    private ComicManager comicManager;

    public Admin(String username, String password, Role role) {
            super(username, password, role);
    }

    public void addComic(Comic comic) {
        comicManager.addComic(comic);
    }

    public void removeComic(Comic comic) {
        comicManager.removeComic(comic);
    }

    public void updateComic(Comic comic, String newTitle, String newAuthor, String newCategory, Status newStatus) {
        comic.updateComic(newTitle, newAuthor, newCategory, newStatus);
    }

    public void updateChapter(Comic comic, int chapterNumber, String newTitle, String newContent) {
        for (Chapter chapter : comic.getChapterList()) {
            if (chapter.getChapterNumber() == chapterNumber) {
                chapter.updateChapter(newTitle, newContent);
                break;
            }
        }
    }
    
}
