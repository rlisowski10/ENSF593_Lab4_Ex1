import java.util.ArrayList;
import java.io.PrintWriter;
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
        currentOrder.printOrderLines();
        currentOrder.updateToolQuantities();
        printOrders();
        System.out.println("\nNote: Above order exported to Order.txt.\n");
      } else
        System.out.println("*** No order was created for the day. ***\n");
    }

    this.currentDate = currentDate.plusDays(1);
  }

  private void printOrders() {
    String ordersText = printSeparator();
    for (Order order : orderList)
      ordersText += order.printOrderLines() + "\n" + printSeparator();
    System.out.println(ordersText);

    try {
      PrintWriter writer = new PrintWriter("order.txt", "UTF-8");
      writer.println(ordersText);
      writer.close();
    } catch (Exception e) {
      System.out.println("Error: Could not write order to text file.");
    }
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

  private String printSeparator() {
    return ("*".repeat(70));
  }
}