public class Tool {

  private int id;
  private String name;
  private int quantity;
  private double price;
  private Supplier supplier;

  Tool(int id, String name, int quantity, double price, Supplier supplier) {
    this.setId(id);
    this.setName(name);
    this.setQuantity(quantity);
    this.setPrice(price);
    this.setSupplier(supplier);
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}