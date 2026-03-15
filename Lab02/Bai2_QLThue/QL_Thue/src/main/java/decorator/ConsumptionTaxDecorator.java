package decorator;

public class ConsumptionTaxDecorator extends TaxDecorator {
    public ConsumptionTaxDecorator(ProductComponent product) { super(product); }

    @Override
    public double getPrice() {
        return wrappedProduct.getPrice() + (wrappedProduct.getPrice() * 0.05);
    }

    @Override
    public String getDescription() {
        return wrappedProduct.getDescription() + " + Thuế Tiêu thụ(5%)";
    }
}