package comic.model;

import java.util.ArrayList;
import java.util.List;

public class ComicManager {
    private List<Comic> comicList;

    public ComicManager() {
        this.comicList = new ArrayList<>();
    }

    public void addComic(Comic comic) {
        comicList.add(comic);
    }

    public void removeComic(Comic comic) {
        comicList.remove(comic);
    }

    public int getComicCount() {
        return comicList.size(); 
    }
}
