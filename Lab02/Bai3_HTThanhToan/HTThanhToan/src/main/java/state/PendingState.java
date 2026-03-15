package state;

import java.text.DecimalFormat;

public class PendingState implements PaymentState {
    @Override
    public void process(PaymentContext context) {
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println("[State - Pending] Đang khởi tạo giao dịch " + df.format(context.getAmount()) + " VNĐ. Chuyển sang xử lý...");
        context.setState(new ProcessingState());
    }
}