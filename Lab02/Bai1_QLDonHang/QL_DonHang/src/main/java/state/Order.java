package state;

public class Order {
    private OrderState currentState;

    public Order() {
        this.currentState = new NewOrderState();
    }

    public void setState(OrderState state) {
        this.currentState = state;
    }

    public void process() {
        currentState.processOrder(this);
    }

    public void cancel() {
        currentState.cancelOrder(this);
    }
}