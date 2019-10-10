public class Tool {
  private String name;
  private int id;
  private int quantity;
  private double price;
  private Supplier supplier;

  Tool(String name, int id, int quantity, double price, Supplier supplier) {
    this.name = name;
    this.id = id;
    this.quantity = quantity;
    this.price = price;
    this.supplier = supplier;
  }
}