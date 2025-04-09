package weeksTasks.week03;

public abstract class Book {
    protected int bookID;
    protected String title;
    protected String bookimage;
    protected String author;
    protected int price;
    protected long ISBN;

    public Book(int bookID, String title, String bookimage, String author, int price, long ISBN) {
        this.bookID = bookID;
        this.title = title;
        this.bookimage = bookimage;
        this.author = author;
        this.price = price;
        this.ISBN = ISBN;
    }

    public int getBookID() { return bookID; }
    public String getTitle() { return title; }
    public String getBookImage() { return bookimage; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN+""; }
    public int getPrice() { return price; }

    public abstract void displayInfo();
}


