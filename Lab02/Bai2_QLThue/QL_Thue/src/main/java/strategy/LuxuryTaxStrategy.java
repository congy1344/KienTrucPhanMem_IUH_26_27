package strategy;

public class LuxuryTaxStrategy implements TaxStrategy {
    @Override
    public double calculateTax(double basePrice) {
        return basePrice * 0.30; // Thuế xa xỉ 30%
    }
}