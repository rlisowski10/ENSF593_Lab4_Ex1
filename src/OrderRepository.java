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

  public LocalDate getCurrentDate() {
    return currentDate;
  }

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public void updateOrder(ArrayList<Tool> toolList) {
    for (Tool tool : toolList) {
      if (tool.getQuantity() < MIN_QUANTITY) {
        makeOrder(tool);
      }
    }
  }

  public void advanceToNextDay() {
    if (isOrderPopulated()) {
      Order currentOrder = orderList.get(orderList.size() - 1);
      if (currentOrder.getOrderDate() == currentDate) {
        currentOrder.printOrderLinesToConsole();
        // TODO Print current order to text file.
        currentOrder.updateToolQuantities();
      } else
        System.out.println("*** No order was created for the previous day. ***\n");
    }

    this.currentDate = currentDate.plusDays(1);
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

  private boolean isOrderPopulated() {
    boolean isOrderPopulated = true;
    if (orderList.size() == 0) {
      isOrderPopulated = false;
    }

    return isOrderPopulated;
  }

  private void makeOrder(Tool tool) {
    if (isOrderPopulated()) {
      Order currentOrder = orderList.get(orderList.size() - 1);
      if (currentOrder.getOrderDate() != currentDate) {
        createNewDailyOrder(tool);
      } else {
        addToCurrentOrder(tool, currentOrder);
      }
    } else {
      createNewDailyOrder(tool);
    }
  }

  private void addToCurrentOrder(Tool tool, Order currentOrder) {
    int orderSize = REQUIRED_QUANTITY - tool.getQuantity();
    currentOrder.removeOrderLineForSameTool(tool);
    currentOrder.addOrderLine(tool, orderSize);
  }

  private void createNewDailyOrder(Tool tool) {
    Order newDailyOrder = new Order(currentDate);
    orderList.add(newDailyOrder);
    int orderSize = REQUIRED_QUANTITY - tool.getQuantity();
    newDailyOrder.addOrderLine(tool, orderSize);
  }
}