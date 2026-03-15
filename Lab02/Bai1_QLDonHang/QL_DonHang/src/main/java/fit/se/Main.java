package fit.se;

import strategy.*; // Import package strategy
import decorator.*; // Import package decorator
// Không import state.* bằng wildcard để tránh xung đột class Order với package strategy

public class Main {
    public static void main(String[] args) {
        System.out.println("====== 1. TEST STATE PATTERN ======");
        // Gọi rõ đường dẫn state.Order
        state.Order stateOrder = new state.Order();
        stateOrder.process(); // Thành Processing
        stateOrder.process(); // Thành Delivered
        stateOrder.cancel();  // Cố hủy khi đã giao

        System.out.println("\n====== 2. TEST STRATEGY PATTERN ======");
        // Gọi rõ đường dẫn strategy.Order
        strategy.Order strategyOrder = new strategy.Order(new NewOrderStrategy());
        strategyOrder.process();

        strategyOrder.setStrategy(new ProcessingStrategy());
        strategyOrder.process();

        strategyOrder.setStrategy(new DeliveredStrategy());
        strategyOrder.cancel();

        System.out.println("\n====== 3. TEST DECORATOR PATTERN ======");
        OrderComponent myOrder = new BaseOrder();

        // Đắp từng lớp tính năng lên đơn hàng
        myOrder = new ProcessingDecorator(myOrder);
        myOrder = new DeliveredDecorator(myOrder);
        myOrder = new GiftWrapDecorator(myOrder);

        myOrder.process();
        // Sẽ in ra lần lượt: Base -> Xử lý -> Giao hàng -> Gói quà
    }
}