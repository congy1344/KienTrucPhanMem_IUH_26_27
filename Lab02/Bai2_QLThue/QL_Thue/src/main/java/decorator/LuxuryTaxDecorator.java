package decorator;

public class LuxuryTaxDecorator extends TaxDecorator {
    public LuxuryTaxDecorator(ProductComponent product) { super(product); }

    @Override
    public double getPrice() {
        return wrappedProduct.getPrice() + (wrappedProduct.getPrice() * 0.30);
    }

    @Override
    public String getDescription() {
        return wrappedProduct.getDescription() + " + Thuế Xa xỉ(30%)";
    }
}