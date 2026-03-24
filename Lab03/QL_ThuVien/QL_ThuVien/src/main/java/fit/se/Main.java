package fit.se;

import fit.se.library.decorator.BasicBorrowing;
import fit.se.library.decorator.Borrowing;
import fit.se.library.decorator.BrailleEditionDecorator;
import fit.se.library.decorator.ExtendedTimeDecorator;
import fit.se.library.factory.Book;
import fit.se.library.factory.BookFactory;
import fit.se.library.observer.User;
import fit.se.library.singleton.Library;
import fit.se.library.strategy.SearchByAuthor;
import fit.se.library.strategy.SearchByTitle;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== KHỞI TẠO HỆ THỐNG (SINGLETON) ===");
        Library library = Library.getInstance();
        Library anotherLibraryRef = Library.getInstance();
        System.out.println("Kiểm tra Singleton (2 biến tham chiếu có cùng 1 object?): " + (library == anotherLibraryRef));
        System.out.println();

        System.out.println("=== ĐĂNG KÝ NHẬN THÔNG BÁO (OBSERVER) ===");
        User user1 = new User("Nguyễn Văn A");
        User user2 = new User("Trần Thị B");
        library.addObserver(user1);
        library.addObserver(user2);
        System.out.println("Đã thêm 2 người dùng vào danh sách nhận thông báo.\n");

        System.out.println("=== THÊM SÁCH VÀO THƯ VIỆN (FACTORY METHOD & OBSERVER) ===");
        // Factory Method tạo sách, khi add vào Library sẽ trigger Observer
        Book book1 = BookFactory.createBook("PAPER", "Clean Code", "Robert C. Martin", "Programming");
        Book book2 = BookFactory.createBook("EBOOK", "Design Patterns", "Gang of Four", "Software Engineering");
        Book book3 = BookFactory.createBook("PAPER", "Clean Architecture", "Robert C. Martin", "Architecture");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        System.out.println();

        System.out.println("=== TÌM KIẾM SÁCH (STRATEGY) ===");
        // Tìm theo tên
        library.setSearchStrategy(new SearchByTitle());
        System.out.println("Tìm kiếm theo tên 'Clean':");
        List<Book> searchResults1 = library.searchBooks("Clean");
        searchResults1.forEach(b -> System.out.println(" - " + b.getTitle() + " (" + b.getBookType() + ")"));

        // Thay đổi thuật toán sang tìm theo tác giả
        library.setSearchStrategy(new SearchByAuthor());
        System.out.println("\nTìm kiếm theo tác giả 'Martin':");
        List<Book> searchResults2 = library.searchBooks("Martin");
        searchResults2.forEach(b -> System.out.println(" - " + b.getTitle() + " (" + b.getAuthor() + ")"));
        System.out.println();

        System.out.println("=== MƯỢN SÁCH & MỞ RỘNG TÍNH NĂNG (DECORATOR) ===");
        if (!searchResults2.isEmpty()) {
            Book bookToBorrow = searchResults2.get(0); // Lấy sách đầu tiên tìm được

            // 1. Mượn cơ bản
            Borrowing basicBorrow = new BasicBorrowing(bookToBorrow);
            System.out.println("1. Mượn cơ bản:");
            System.out.println("   Thông tin: " + basicBorrow.getBorrowInfo());
            System.out.println("   Số ngày mượn: " + basicBorrow.getBorrowDays() + " ngày");

            // 2. Mượn cơ bản + Gia hạn thời gian
            Borrowing extendedBorrow = new ExtendedTimeDecorator(basicBorrow);
            System.out.println("\n2. Mượn có gia hạn:");
            System.out.println("   Thông tin: " + extendedBorrow.getBorrowInfo());
            System.out.println("   Số ngày mượn: " + extendedBorrow.getBorrowDays() + " ngày");

            // 3. Mượn cơ bản + Gia hạn + Yêu cầu bản chữ nổi
            Borrowing ultimateBorrow = new BrailleEditionDecorator(extendedBorrow);
            System.out.println("\n3. Mượn có gia hạn VÀ yêu cầu bản chữ nổi:");
            System.out.println("   Thông tin: " + ultimateBorrow.getBorrowInfo());
            System.out.println("   Số ngày mượn: " + ultimateBorrow.getBorrowDays() + " ngày");
        }
    }
}