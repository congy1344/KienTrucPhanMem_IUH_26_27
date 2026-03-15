package state;

public class NewOrderState implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("[State - Mới tạo] Đang kiểm tra thông tin...");
        order.setState(new ProcessingState());
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("[State - Mới tạo] Hủy đơn hàng thành công.");
        order.setState(new CanceledState());
    }
}