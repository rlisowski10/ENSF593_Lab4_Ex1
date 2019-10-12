import java.util.ArrayList;
import java.time.LocalDate;

public class OrderRepository {

  // ============================================================
  // Member Variables
  // ============================================================

  private ArrayList<Order> orderList;
  private LocalDate currentDate;
  private final int MIN_QUANTITY = 40;
  private final int REQUIRED_QUANTITY = 50;

  // ============================================================
  // Constructors
  // ============================================================

  public OrderRepository() {
    currentDate = LocalDate.now();
    orderList = new ArrayList<Order>();
  }

  // ============================================================
  // Accessors
  // ============================================================

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public void updateOrder(ArrayList<Tool> toolList) {
    for (Tool tool : toolList) {
      if (tool.getQuantity() < MIN_QUANTITY) {
        if (orderList.size() == 0) {
          createNewDailyOrder(tool);
        } else if (orderList.get(orderList.size() - 1).getOrderDate() != currentDate) {
          createNewDailyOrder(tool);
        } else {
          // Add tool order to Order object for that day
          addToExistingOrder(tool);
        }
      }
    }
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

  private void addToExistingOrder(Tool tool) {
    Order currentOrder = orderList.get(orderList.size() - 1);
    int orderSize = REQUIRED_QUANTITY - tool.getQuantity();
    currentOrder.addOrderLine(tool, orderSize);
  }

  private void createNewDailyOrder(Tool tool) {
    Order newDailyOrder = new Order(currentDate);
    orderList.add(newDailyOrder);
    int orderSize = REQUIRED_QUANTITY - tool.getQuantity();
    newDailyOrder.addOrderLine(tool, orderSize);
  }
}