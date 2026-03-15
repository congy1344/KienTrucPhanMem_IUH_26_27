package decorator;

public class GiftWrapDecorator extends OrderDecorator {
    public GiftWrapDecorator(OrderComponent orderWrapper) { super(orderWrapper); }

    @Override
    public void process() {
        super.process();
        System.out.println("[Decorator - Dịch vụ] Bọc giấy quà tặng cao cấp.");
    }
}