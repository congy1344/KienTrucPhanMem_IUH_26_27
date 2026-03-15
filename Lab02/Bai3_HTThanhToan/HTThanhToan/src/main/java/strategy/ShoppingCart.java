package strategy;

public class ShoppingCart {
    private double totalAmount;
    
    public ShoppingCart(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void processPayment(PaymentStrategy strategy) {
        strategy.pay(totalAmount);
    }
}