public class Tool {
  
  private int id;
  private String name;  
  private int quantity;
  private double price;
  private Supplier supplier;

  Tool(int id, String name, int quantity, double price, Supplier supplier) {
    this.id = id;
    this.name = name;    
    this.quantity = quantity;
    this.price = price;
    this.supplier = supplier;
  }
}