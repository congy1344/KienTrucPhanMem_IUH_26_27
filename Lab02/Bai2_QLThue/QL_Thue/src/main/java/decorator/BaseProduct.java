package decorator;

public class BaseProduct implements ProductComponent {
    private String name;
    private double price;

    public BaseProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() { 
        return price; 
    }

    @Override
    public String getDescription() {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###");
        return name + " (Giá gốc: " + df.format(price) + ")";
    }
}