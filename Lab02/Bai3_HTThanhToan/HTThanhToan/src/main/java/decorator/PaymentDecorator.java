package decorator;

public abstract class PaymentDecorator implements PaymentComponent {
    protected PaymentComponent wrappedPayment;

    public PaymentDecorator(PaymentComponent payment) { 
        this.wrappedPayment = payment; 
    }
}