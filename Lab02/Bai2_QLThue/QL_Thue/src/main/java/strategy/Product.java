package strategy;

public class Product {
    private String name;
    private double basePrice;
    private TaxStrategy taxStrategy;

    public Product(String name, double basePrice, TaxStrategy taxStrategy) {
        this.name = name;
        this.basePrice = basePrice;
        this.taxStrategy = taxStrategy;
    }

    public void setTaxStrategy(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }

    public double getFinalPrice() {
        double tax = taxStrategy.calculateTax(basePrice);
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###");
        System.out.println("[Strategy] Sản phẩm: " + name + " | Giá gốc: " + df.format(basePrice) + " | Thuế: " + df.format(tax));
        return basePrice + tax;
    }
}