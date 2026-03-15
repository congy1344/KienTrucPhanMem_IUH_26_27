package decorator;

public abstract class OrderDecorator implements OrderComponent {
    protected OrderComponent orderWrapper;

    public OrderDecorator(OrderComponent orderWrapper) {
        this.orderWrapper = orderWrapper;
    }

    @Override
    public void process() {
        if (orderWrapper != null) {
            orderWrapper.process();
        }
    }
}