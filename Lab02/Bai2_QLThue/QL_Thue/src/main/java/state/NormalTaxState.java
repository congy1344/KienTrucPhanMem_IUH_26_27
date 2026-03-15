package state;

public class NormalTaxState implements OrderTaxState {
    @Override
    public void calculateAndTransition(Order order) {
        if (order.getTotalAmount() > 50000000) {
            System.out.println("[State - Chuyển đổi] Tổng giá trị vượt 50tr -> Tự động chuyển sang Thuế Xa Xỉ.");
            order.setState(new LuxuryTaxState());
            order.processTax();
        } else {
            double tax = order.getTotalAmount() * 0.10;
            java.text.DecimalFormat df = new java.text.DecimalFormat("#,###");
            System.out.println("[State - Thường] Thuế GTGT (10%): " + df.format(tax));
        }
    }
}