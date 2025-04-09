package weeksTasks.week03;

public class PrintedBook extends Book {
    private int pages;
    private int stock;

    public PrintedBook(int bookID, String title, String bookimage, String author, int price, long ISBN, int pages, int stock) {
        super(bookID, title, bookimage, author, price, ISBN);
        this.pages = pages;
        this.stock = stock;
    }

    public int getPages() { return pages; }
    public int getStock() { return stock; }

    @Override
    public void displayInfo() {
        System.out.println("[PrintedBook] " + title + " by " + author + ", " + pages + " pages, Stock: " + stock);
    }
}

