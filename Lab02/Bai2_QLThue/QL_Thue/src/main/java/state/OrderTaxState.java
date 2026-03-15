package state;

public interface OrderTaxState {
    void calculateAndTransition(Order order);
}