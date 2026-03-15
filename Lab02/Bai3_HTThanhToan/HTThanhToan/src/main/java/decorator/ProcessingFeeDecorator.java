package decorator;

public class ProcessingFeeDecorator extends PaymentDecorator {
    public ProcessingFeeDecorator(PaymentComponent payment) { 
        super(payment); 
    }

    @Override
    public double getAmount() {
        return wrappedPayment.getAmount() + 15000; // Phí xử lý cố định 15k
    }

    @Override
    public String getDescription() {
        return wrappedPayment.getDescription() + " + Phí xử lý (15,000 VNĐ)";
    }
}