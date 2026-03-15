package strategy;

public class CanceledStrategy implements OrderStrategy {
    @Override
    public void processOrder() { System.out.println("[Strategy - Lỗi] Không thể xử lý đơn đã hủy."); }
    @Override
    public void cancelOrder() { System.out.println("[Strategy - Thông báo] Đã hủy trước đó."); }
}