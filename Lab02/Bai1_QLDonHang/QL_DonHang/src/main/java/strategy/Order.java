package strategy;

public class Order {
    private OrderStrategy strategy;

    public Order(OrderStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(OrderStrategy strategy) {
        this.strategy = strategy;
    }

    public void process() {
        if (strategy != null) strategy.processOrder();
    }

    public void cancel() {
        if (strategy != null) strategy.cancelOrder();
    }
}