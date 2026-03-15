package state;

public class ProcessingState implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("[State - Đang xử lý] Đang đóng gói và vận chuyển...");
        order.setState(new DeliveredState());
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("[State - Đang xử lý] Hủy và hoàn tiền...");
        order.setState(new CanceledState());
    }
}