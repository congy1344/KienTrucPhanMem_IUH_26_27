package state;

public class PaymentContext {
    private double amount;
    private PaymentState currentState;

    public PaymentContext(double amount) {
        this.amount = amount;
        this.currentState = new PendingState(); // Vừa tạo là Chờ thanh toán
    }

    public double getAmount() { 
        return amount; 
    }
    
    public void setState(PaymentState state) { 
        this.currentState = state; 
    }

    public void advance() {
        currentState.process(this);
    }
}