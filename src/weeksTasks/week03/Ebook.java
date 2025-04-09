package weeksTasks.week03;

public class Ebook extends Book {
    private int kb;

    public Ebook(int bookID, String title, String bookimage, String author, int price, long ISBN, int kb) {
        super(bookID, title, bookimage, author, price, ISBN);
        this.kb = kb;
    }

    public int getKb() { return kb; }

    @Override
    public void displayInfo() {
        System.out.println("[Ebook] " + title + " by " + author + ", Size: " + kb + " KB");
    }
}


