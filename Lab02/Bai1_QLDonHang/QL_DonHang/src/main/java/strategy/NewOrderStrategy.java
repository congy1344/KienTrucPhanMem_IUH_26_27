package strategy;

public class NewOrderStrategy implements OrderStrategy {
    @Override
    public void processOrder() { System.out.println("[Strategy - Mới tạo] Kiểm tra thông tin..."); }
    @Override
    public void cancelOrder() { System.out.println("[Strategy - Mới tạo] Hủy thành công."); }
}