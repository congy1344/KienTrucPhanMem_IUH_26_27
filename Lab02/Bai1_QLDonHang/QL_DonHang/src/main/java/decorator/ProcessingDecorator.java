package decorator;

public class ProcessingDecorator extends OrderDecorator {
    public ProcessingDecorator(OrderComponent orderWrapper) { super(orderWrapper); }

    @Override
    public void process() {
        super.process();
        System.out.println("[Decorator - Thêm] Đã thêm công đoạn: Đóng gói và vận chuyển.");
    }
}