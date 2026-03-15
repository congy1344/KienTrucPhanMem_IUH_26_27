package fit.se;

import strategy.*;
import decorator.*;
import state.*;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#,###");

        System.out.println("====== 1. TEST STRATEGY (Chọn 1 loại thuế) ======");
        strategy.Product laptop = new strategy.Product("Laptop", 20000000, new VatTaxStrategy());
        System.out.println("Thanh toán: " + df.format(laptop.getFinalPrice()) + " VNĐ");

        laptop.setTaxStrategy(new LuxuryTaxStrategy());
        System.out.println("Thanh toán (đã đổi luật): " + df.format(laptop.getFinalPrice()) + " VNĐ");

        System.out.println("\n====== 2. TEST DECORATOR (Cộng dồn thuế) ======");
        ProductComponent car = new BaseProduct("Oto BMW", 1000000000);

        car = new ConsumptionTaxDecorator(car);
        car = new VatTaxDecorator(car);
        car = new LuxuryTaxDecorator(car);

        System.out.println(car.getDescription() + " \n=> Tổng giá trị thanh toán: " + df.format(car.getPrice()) + " VNĐ");

        System.out.println("\n====== 3. TEST STATE (Tự đổi luật tính) ======");
        state.Order order = new state.Order(30000000);
        order.processTax();

        System.out.println("\n(Khách hàng mua thêm hàng giá 30 triệu nữa...)");
        order.addAmount(30000000);
        order.processTax();
    }
}