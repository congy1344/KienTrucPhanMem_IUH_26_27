package decorator;

public abstract class TaxDecorator implements ProductComponent {
    protected ProductComponent wrappedProduct;

    public TaxDecorator(ProductComponent product) {
        this.wrappedProduct = product;
    }
}