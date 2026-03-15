package decorator;

public class VatTaxDecorator extends TaxDecorator {
    public VatTaxDecorator(ProductComponent product) { super(product); }

    @Override
    public double getPrice() {
        return wrappedProduct.getPrice() + (wrappedProduct.getPrice() * 0.10);
    }

    @Override
    public String getDescription() {
        return wrappedProduct.getDescription() + " + VAT(10%)";
    }
}