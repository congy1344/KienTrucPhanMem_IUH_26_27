package fit.se.observer;

public interface StockObserver {
    // Phương thức này sẽ được Cổ phiếu gọi khi có biến động giá
    void update(String stockSymbol, double newPrice);
}