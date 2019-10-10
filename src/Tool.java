public class Tool {
  String name;
  int id;
  int quantity;
  double price;
  Supplier supplier;

  Tool(String name, int id, int quantity, double price, Supplier supplier) {
    this.name = name;
    this.id = id;
    this.quantity = quantity;
    this.price = price;
    this.supplier = supplier;
  }
}