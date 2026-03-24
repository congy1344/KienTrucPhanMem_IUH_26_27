package fit.se.library.decorator;

public class ExtendedTimeDecorator extends BorrowDecorator {
    public ExtendedTimeDecorator(Borrowing borrowing) { super(borrowing); }
    public String getBorrowInfo() { return borrowing.getBorrowInfo() + " (Đã gia hạn)"; }
    public int getBorrowDays() { return borrowing.getBorrowDays() + 7; } // Thêm 7 ngày
}