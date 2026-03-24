package fit.se.observer;

import java.util.ArrayList;
import java.util.List;

public class Stock implements StockSubject {
    private String symbol; // Mã cổ phiếu (VD: VCB, FPT)
    private double price;  // Giá cổ phiếu
    
    // Danh sách những người đang theo dõi cổ phiếu này
    private List<StockObserver> observers; 

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
        this.observers = new ArrayList<>();
    }

    // Khi giá thay đổi, tự động gọi notifyObservers()
    public void setPrice(double newPrice) {
        if (this.price != newPrice) {
            this.price = newPrice;
            notifyObservers(); 
        }
    }

    @Override
    public void registerObserver(StockObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        // Duyệt qua tất cả nhà đầu tư và gửi thông báo
        for (StockObserver observer : observers) {
            observer.update(symbol, price);
        }
    }
}