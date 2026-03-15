package strategy;

import java.text.DecimalFormat;

public class CreditCardStrategy implements PaymentStrategy {
    private String cardNumber;

    public CreditCardStrategy(String cardNumber) { 
        this.cardNumber = cardNumber; 
    }

    @Override
    public void pay(double amount) {
        DecimalFormat df = new DecimalFormat("#,###");
        System.out.println("[Strategy] Thanh toán " + df.format(amount) + " VNĐ bằng Thẻ Tín Dụng (" + cardNumber + ")");
    }
}