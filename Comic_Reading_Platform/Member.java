package Comic_Reading_Platform;

public class Member extends User {
    private MemberAction memberAction;

    public Member(String username, String password, String email) {
        super(username, password, email, User.ROLE_MEMBER);
        memberAction = new MemberAction();
    }

    public void readComic() {
        memberAction.readComic();
    }

    public void rateComic() {
        memberAction.rateComic();
    }

    public void viewLibrary() {
        memberAction.viewLibrary();
    }

    public void addComicToLibrary() {
        memberAction.addComicToLibrary();
    }

    public void removeComicFromLibrary() {
        memberAction.removeComicFromLibrary();
    }

    public void viewRecommendations() {
        memberAction.viewRecommendations();
    }

    public void viewReadingHistory() {
        memberAction.viewReadingHistory();
    }
    // Other methods if needed...
}
