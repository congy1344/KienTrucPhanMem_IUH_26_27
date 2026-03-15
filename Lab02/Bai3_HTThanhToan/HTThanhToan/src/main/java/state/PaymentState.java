package state;

public interface PaymentState {
    void process(PaymentContext context);
}