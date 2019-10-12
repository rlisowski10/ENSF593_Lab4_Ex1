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
    this.tool = tool;
    this.orderAmount = orderAmount;
  }

  // ============================================================
  // Accessors
  // ============================================================

  public int getOrderAmount() {
    return orderAmount;
  }

  public String getToolName() {
    return tool.getName();
  }

  public String getToolSupplier() {
    return tool.getSupplierName();
  }

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public void updateToolQuantity() {
    int currentQuantity = tool.getQuantity();
    tool.setQuantity(currentQuantity + orderAmount);
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================
}