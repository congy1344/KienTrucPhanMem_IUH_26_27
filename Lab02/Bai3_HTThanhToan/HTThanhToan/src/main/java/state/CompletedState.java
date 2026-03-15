package state;

public class CompletedState implements PaymentState {
    @Override
    public void process(PaymentContext context) {
        System.out.println("[State - Completed] Giao dịch đã hoàn tất. Không thể xử lý lại.");
    }
}