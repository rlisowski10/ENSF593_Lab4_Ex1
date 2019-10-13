/**
 * Stores the individual tool order data, and handles tool quantity updates
 * after additional stock has been ordered.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-10-12
 */
public class OrderLine {

  // ============================================================
  // Member Variables
  // ============================================================

  private Tool tool;
  private int orderAmount;

  // ============================================================
  // Constructors
  // ============================================================

  /**
   * Constructor for an orderline, which sets the tool and order quantity.
   * 
   * @param tool        The tool that an orderline will be created for.
   * @param orderAmount The order amount for that tool.
   */
  public OrderLine(Tool tool, int orderAmount) {
    setTool(tool);
    setOrderAmount(orderAmount);
  }

  // ============================================================
  // Accessors
  // ============================================================

  /**
   * Gets the order quantity for an orderline.
   * 
   * @return int The order quantity for an orderline.
   */
  public int getOrderAmount() {
    return orderAmount;
  }

  /**
   * Sets the order quantity for an orderline.
   * 
   * @param orderAmount The order quantity for an orderline.
   */
  public void setOrderAmount(int orderAmount) {
    this.orderAmount = orderAmount;
  }

  /**
   * Gets the tool associated with an orderline.
   * 
   * @return Tool The tool to be ordered.
   */
  public Tool getTool() {
    return tool;
  }

  /**
   * Sets the tool associated with an orderline.
   * 
   * @param tool The tool to be ordered.
   */
  public void setTool(Tool tool) {
    this.tool = tool;
  }

  /**
   * Gets the name of the tool being ordered.
   * 
   * @return String The name of the tool.
   */
  public String getToolName() {
    return getTool().getName();
  }

  /**
   * Gets the the supplier name for the tool.
   * 
   * @return String The supplier name for the tool.
   */
  public String getToolSupplier() {
    return getTool().getSupplierName();
  }

  // ============================================================
  // Public Instance Methods
  // ============================================================

  /**
   * Updates the inventory's tool quantity based on the amount ordered.
   */
  public void updateToolQuantity() {
    int currentQuantity = getTool().getQuantity();
    // Adds the order quantity to the current supplied quantity for a tool.
    getTool().setQuantity(currentQuantity + getOrderAmount());
  }
}