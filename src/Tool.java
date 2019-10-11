public class Tool {

  // ============================================================
  // Member Variables
  // ============================================================

  private int id;
  private String name;
  private int quantity;
  private double price;
  private Supplier supplier;

  // ============================================================
  // Constructors
  // ============================================================

  Tool(int id, String name, int quantity, double price, Supplier supplier) {
    this.setId(id);
    this.setName(name);
    this.setQuantity(quantity);
    this.setPrice(price);
    this.setSupplier(supplier);
  }

  // ============================================================
  // Accessors
  // ============================================================

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

  // ============================================================
  // Public Instance Methods
  // ============================================================

  @Override
  public String toString() {
    String nameTabs;
    if (getName().length() < 8)
      nameTabs = "\t\t\t";
    else
      nameTabs = "\t\t";

    String toolToString = getName() + nameTabs + "ID: " + getId() + "\tQuantity: " + getQuantity() + "\tPrice: $"
        + String.format("%.2f", getPrice()) + "\tSupplier: " + getSupplier().getName() + "\n";
    return toolToString;
  }
}