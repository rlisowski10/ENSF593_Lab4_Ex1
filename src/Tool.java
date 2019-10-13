/**
 * Holds the data for a single tool in the shop's inventory.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-10-12
 */
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

  /**
   * Constructor to set all required data fields for a tool, including the
   * supplier information, represented by an object.
   * 
   * @param id       The tool's id.
   * @param name     The tool's name.
   * @param quantity The tool's current quantity in stock.
   * @param price    The tool's set price.
   * @param supplier A reference to the supplier object associated with that tool.
   */
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

  /**
   * Returns the supplier name for the tool.
   * 
   * @return String The tool's supplier name.
   */
  public String getSupplierName() {
    return supplier.getName();
  }

  /**
   * Sets the supplier reference object for a tool.
   * 
   * @param supplier The tool's supplier reference.
   */
  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  /**
   * Gets the price of the tool, in dollars.
   * 
   * @return double The price of the tool, in dollars.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets the prices of the tool, in dollars.
   * 
   * @param price The price of the tool, in dollars.
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Gets the quantity in stock for the tool.
   * 
   * @return int The tool's quantity in stock.
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity in stock for the tool.
   * 
   * @param quantity The tool's quantity in stock.
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Gets the name of the tool.
   * 
   * @return String The name of the tool.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the tool.
   * 
   * @param name The name of the tool.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the ID number for the tool.
   * 
   * @return int The ID number for the tool.
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the ID number for the tool.
   * 
   * @param id The ID number for the tool.
   */
  public void setId(int id) {
    this.id = id;
  }

  // ============================================================
  // Public Instance Methods
  // ============================================================

  /**
   * The toString override method that displays a tool's stored information.
   * 
   * @return String The tool's description information.
   */
  @Override
  public String toString() {
    String nameTabs;
    nameTabs = (getName().length() < 8) ? (nameTabs = "\t\t\t") : "\t\t";

    String toolToString = getName() + nameTabs + "ID: " + getId() + "\tQuantity: " + getQuantity() + "\tPrice: $"
        + String.format("%.2f", getPrice()) + "\tSupplier: " + getSupplierName() + "\n";
    return toolToString;
  }
}