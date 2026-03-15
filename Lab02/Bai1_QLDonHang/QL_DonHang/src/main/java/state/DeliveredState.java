package state;

public class DeliveredState implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("[State - Đã giao] Đơn hàng đã giao thành công.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("[State - Lỗi] Không thể hủy vì đã giao.");
    }
}