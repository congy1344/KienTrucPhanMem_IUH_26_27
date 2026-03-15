package state;

public class LuxuryTaxState implements OrderTaxState {
    @Override
    public void calculateAndTransition(Order order) {
        double tax = order.getTotalAmount() * 0.30;
        System.out.println("[State - Xa xỉ] Thuế Xa xỉ (30%): " + tax);
    }
}