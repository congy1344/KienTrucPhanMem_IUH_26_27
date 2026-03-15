package fit.se;

import strategy.*;
import decorator.*;
import state.*;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#,###");

        System.out.println("====== 1. TEST STRATEGY (Chọn phương thức thanh toán) ======");
        ShoppingCart cart = new ShoppingCart(15000000);
        cart.processPayment(new CreditCardStrategy("1234-5678-9012"));
        cart.processPayment(new PayPalStrategy("user@example.com"));

        System.out.println("\n====== 2. TEST DECORATOR (Thêm Phí & Giảm giá) ======");
        PaymentComponent myPayment = new BasePayment(2000000); // 2 triệu

        // Đắp thêm các lớp (Decorators)
        myPayment = new ProcessingFeeDecorator(myPayment);
        myPayment = new DiscountDecorator(myPayment);

        System.out.println(myPayment.getDescription());
        System.out.println("=> Tổng tiền thực tế cần thanh toán: " + df.format(myPayment.getAmount()) + " VNĐ");

        System.out.println("\n====== 3. TEST STATE (Vòng đời thanh toán) ======");
        PaymentContext paymentFlow = new PaymentContext(5000000); // 5 triệu

        paymentFlow.advance(); // Pending -> Processing
        paymentFlow.advance(); // Processing -> Completed
        paymentFlow.advance(); // Cố chạy tiếp khi đã Completed
    }
}