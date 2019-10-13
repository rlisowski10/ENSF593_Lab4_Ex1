import java.util.ArrayList;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * Holds a list of all of the daily orders that have been made through time.
 * Provides functionality for printing general order data, along with creating
 * new orders for each day.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-10-12
 */
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
    setCurrentDate(LocalDate.now());
    setOrderList(new ArrayList<Order>());
  }

  // ============================================================
  // Accessors
  // ============================================================

  public LocalDate getCurrentDate() {
    return currentDate;
  }

  public void setCurrentDate(LocalDate currentDate) {
    this.currentDate = currentDate;
  }

  public void setOrderList(ArrayList<Order> orderList) {
    this.orderList = orderList;
  }

  public int getMIN_QUANTITY() {
    return MIN_QUANTITY;
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
      if (currentOrder.getOrderDate() == getCurrentDate()) {
        currentOrder.printOrderLines();
        currentOrder.updateToolQuantities();
        printOrders();
        System.out.println("\nNote: Above order exported to Order.txt.\n");
      } else
        System.out.println("*** No order was created for the day. ***\n");
    }
    setCurrentDate(getCurrentDate().plusDays(1));
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
      if (currentOrder.getOrderDate() != getCurrentDate()) {
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
    Order newDailyOrder = new Order(getCurrentDate());
    orderList.add(newDailyOrder);
    int orderSize = REQUIRED_QUANTITY - tool.getQuantity();
    newDailyOrder.addOrderLine(tool, orderSize);
  }

  private String printSeparator() {
    return ("*".repeat(70));
  }
}