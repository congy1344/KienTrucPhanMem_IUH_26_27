package fit.se.library.decorator;

public class BrailleEditionDecorator extends BorrowDecorator {
    public BrailleEditionDecorator(Borrowing borrowing) { super(borrowing); }
    public String getBorrowInfo() { return borrowing.getBorrowInfo() + " [Phiên bản chữ nổi]"; }
}