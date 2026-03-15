package state;

public class Order {
    private double totalAmount;
    private OrderTaxState currentState;

    public Order(double amount) {
        this.totalAmount = amount;
        this.currentState = new NormalTaxState(); // Mặc định là bình thường
    }

    public double getTotalAmount() { return totalAmount; }
    
    public void addAmount(double amount) { this.totalAmount += amount; }
    
    public void setState(OrderTaxState state) { this.currentState = state; }

    public void processTax() {
        currentState.calculateAndTransition(this);
    }
}