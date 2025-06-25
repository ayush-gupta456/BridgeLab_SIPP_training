public class EBook extends Book {
    public EBook(String ISBN, String title, String author) {
        super(ISBN, title, author);
    }

    public void displayInfo() {
        System.out.println("EBook ISBN: " + ISBN + ", Title: " + title);
    }
}