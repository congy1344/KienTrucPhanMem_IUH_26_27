package decorator;

public class DeliveredDecorator extends OrderDecorator {
    public DeliveredDecorator(OrderComponent orderWrapper) { super(orderWrapper); }

    @Override
    public void process() {
        super.process();
        System.out.println("[Decorator - Thêm] Đã thêm công đoạn: Giao hàng thành công.");
    }
}