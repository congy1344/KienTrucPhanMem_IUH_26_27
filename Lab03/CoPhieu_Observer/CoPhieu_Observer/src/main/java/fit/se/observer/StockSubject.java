package fit.se.observer;

public interface StockSubject {
    void registerObserver(StockObserver observer); // Đăng ký theo dõi
    void removeObserver(StockObserver observer);   // Hủy theo dõi
    void notifyObservers();                        // Thông báo cho tất cả
}