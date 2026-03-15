package decorator;

public class DiscountDecorator extends PaymentDecorator {
    public DiscountDecorator(PaymentComponent payment) { 
        super(payment); 
    }

    @Override
    public double getAmount() {
        return wrappedPayment.getAmount() - 50000; // Giảm giá 50k
    }

    @Override
    public String getDescription() {
        return wrappedPayment.getDescription() + " - Mã giảm giá (50,000 VNĐ)";
    }
}