package decorator;

import java.text.DecimalFormat;

public class BasePayment implements PaymentComponent {
    private double amount;

    public BasePayment(double amount) { 
        this.amount = amount; 
    }

    @Override
    public double getAmount() { 
        return amount; 
    }

    @Override
    public String getDescription() {
        DecimalFormat df = new DecimalFormat("#,###");
        return "Số tiền gốc: " + df.format(amount) + " VNĐ";
    }
}