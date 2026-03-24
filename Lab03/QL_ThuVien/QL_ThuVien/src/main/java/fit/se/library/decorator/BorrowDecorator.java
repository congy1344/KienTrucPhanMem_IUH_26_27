package fit.se.library.decorator;

public abstract class BorrowDecorator implements Borrowing {
    protected Borrowing borrowing;
    public BorrowDecorator(Borrowing borrowing) { this.borrowing = borrowing; }
    public String getBorrowInfo() { return borrowing.getBorrowInfo(); }
    public int getBorrowDays() { return borrowing.getBorrowDays(); }
}