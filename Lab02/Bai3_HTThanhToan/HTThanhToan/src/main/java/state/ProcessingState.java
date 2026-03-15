package state;

public class ProcessingState implements PaymentState {
    @Override
    public void process(PaymentContext context) {
        System.out.println("[State - Processing] Đang liên hệ với ngân hàng để trừ tiền... Giao dịch thành công!");
        context.setState(new CompletedState());
    }
}