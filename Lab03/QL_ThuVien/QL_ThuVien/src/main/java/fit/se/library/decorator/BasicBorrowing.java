package fit.se.library.decorator;

import fit.se.library.factory.Book;

public class BasicBorrowing implements Borrowing {
    private Book book;
    public BasicBorrowing(Book book) { this.book = book; }
    public String getBorrowInfo() { return "Mượn sách: " + book.getTitle(); }
    public int getBorrowDays() { return 14; } // Mặc định 14 ngày
}