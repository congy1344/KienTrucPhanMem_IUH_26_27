package strategy;

public class VatTaxStrategy implements TaxStrategy {
    @Override
    public double calculateTax(double basePrice) {
        return basePrice * 0.10; // VAT 10%
    }
}