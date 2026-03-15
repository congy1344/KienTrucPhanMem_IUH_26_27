package strategy;

public class DeliveredStrategy implements OrderStrategy {
    @Override
    public void processOrder() { System.out.println("[Strategy - Đã giao] Đơn hàng đã giao."); }
    @Override
    public void cancelOrder() { System.out.println("[Strategy - Lỗi] Không thể hủy vì đã giao."); }
}