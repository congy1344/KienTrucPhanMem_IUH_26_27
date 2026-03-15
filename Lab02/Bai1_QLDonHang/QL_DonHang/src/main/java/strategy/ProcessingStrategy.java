package strategy;

public class ProcessingStrategy implements OrderStrategy {
    @Override
    public void processOrder() { System.out.println("[Strategy - Đang xử lý] Đóng gói và vận chuyển..."); }
    @Override
    public void cancelOrder() { System.out.println("[Strategy - Đang xử lý] Hủy và hoàn tiền..."); }
}