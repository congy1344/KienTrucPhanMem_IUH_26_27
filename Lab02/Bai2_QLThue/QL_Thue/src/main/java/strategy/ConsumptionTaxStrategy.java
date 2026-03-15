package strategy;

public class ConsumptionTaxStrategy implements TaxStrategy {
    @Override
    public double calculateTax(double basePrice) {
        return basePrice * 0.05; // Thuế tiêu thụ 5%
    }
}