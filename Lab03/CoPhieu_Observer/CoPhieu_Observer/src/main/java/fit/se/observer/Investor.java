package fit.se.observer;

import java.text.DecimalFormat;

public class Investor implements StockObserver {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double newPrice) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        System.out.println("[Tin nhắn tới " + name + "] Cổ phiếu " + stockSymbol + " vừa chốt giá mới: " + df.format(newPrice) + " VNĐ");
    }
}