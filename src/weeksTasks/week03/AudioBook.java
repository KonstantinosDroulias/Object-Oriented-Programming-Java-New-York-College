package weeksTasks.week03;

public class AudioBook extends Book {
    private String duration;

    public AudioBook(int bookID, String title, String bookimage, String author, int price, long ISBN, String duration) {
        super(bookID, title, bookimage, author, price, ISBN);
        this.duration = duration;
    }

    public String getDuration() { return duration; }

    @Override
    public void displayInfo() {
        System.out.println("[AudioBook] " + title + " by " + author + ", Duration: " + duration);
    }
}
