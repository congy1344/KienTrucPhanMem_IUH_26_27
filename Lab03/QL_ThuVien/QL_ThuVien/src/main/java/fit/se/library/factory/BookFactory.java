package fit.se.library.factory;

public class BookFactory {
    public static Book createBook(String type, String title, String author, String category) {
        if (type.equalsIgnoreCase("PAPER")) {
            return new PaperBook(title, author, category);
        } else if (type.equalsIgnoreCase("EBOOK")) {
            return new EBook(title, author, category);
        }
        throw new IllegalArgumentException("Loại sách không hợp lệ");
    }
}