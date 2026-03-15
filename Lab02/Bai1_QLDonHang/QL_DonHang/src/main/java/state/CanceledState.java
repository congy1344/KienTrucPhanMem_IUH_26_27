package state;

public class CanceledState implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("[State - Lỗi] Đơn hàng đã bị hủy, không thể xử lý.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("[State - Thông báo] Đơn hàng đã được hủy trước đó.");
    }
}