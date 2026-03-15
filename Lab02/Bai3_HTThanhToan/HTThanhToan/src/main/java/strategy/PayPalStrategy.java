package strategy;

import java.text.DecimalFormat;

public class PayPalStrategy implements PaymentStrategy {
    private String email;

    public PayPalStrategy(String email) { 
        this.email = email; 
    }

    @Override
    public void pay(double amount) {
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println("[Strategy] Thanh toán " + df.format(amount) + " VNĐ qua PayPal (" + email + ")");
    }
}