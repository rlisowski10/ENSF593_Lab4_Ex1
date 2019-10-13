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

  public OrderLine(Tool tool, int orderAmount) {
    setTool(tool);
    setOrderAmount(orderAmount);
  }

  // ============================================================
  // Accessors
  // ============================================================

  public int getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(int orderAmount) {
    this.orderAmount = orderAmount;
  }

  public Tool getTool() {
    return tool;
  }

  public void setTool(Tool tool) {
    this.tool = tool;
  }

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public void updateToolQuantity() {
    int currentQuantity = getTool().getQuantity();
    getTool().setQuantity(currentQuantity + getOrderAmount());
  }

  public String getToolName() {
    return getTool().getName();
  }

  public String getToolSupplier() {
    return getTool().getSupplierName();
  }
}