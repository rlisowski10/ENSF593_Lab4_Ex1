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
  private static final int MIN_QUANTITY = 40;
  private static final int REQUIRED_QUANTITY = 50;
  private static final String ORDER_FILE_NAME = "order.txt";

  // ============================================================
  // Constructors
  // ============================================================

  /**
   * Constructor that sets the current date to the actual date that the program is
   * run, and creates a new arraylist for daily orders to be stored in.
   */
  public OrderRepository() {
    setCurrentDate(LocalDate.now());
    setOrderList(new ArrayList<Order>());
  }

  // ============================================================
  // Accessors
  // ============================================================

  /**
   * Gets the current date, which is initially set to the actual date that the
   * program is executed on. The program does allow this date to be incremented
   * for testing/simulation purposes.
   * 
   * @return LocalDate The current simulation date for the application.
   */
  public LocalDate getCurrentDate() {
    return currentDate;
  }

  /**
   * Sets the current date to the latest testing/simulation date.
   * 
   * @param currentDate The current simulation date.
   */
  public void setCurrentDate(LocalDate currentDate) {
    this.currentDate = currentDate;
  }

  /**
   * Sets the list containing the daily order data.
   * 
   * @param orderList The arraylist containing daily order data for the store.
   */
  public void setOrderList(ArrayList<Order> orderList) {
    this.orderList = orderList;
  }

  /**
   * Gets the minimum quantity of stock that the store should carry.
   * 
   * @return int The minimum quantity of stock that the store should carry.
   */
  public int getMIN_QUANTITY() {
    return MIN_QUANTITY;
  }

  // ============================================================
  // Public Instance Methods
  // ============================================================

  /**
   * Checks all tools in the inventory to ensure that their quantity is above a
   * minimum threshold. An order is made, if not.
   * 
   * @param toolList A list containing all tools in the inventory.
   */
  public void updateOrder(ArrayList<Tool> toolList) {
    for (Tool tool : toolList) {
      if (tool.getQuantity() < MIN_QUANTITY) {
        makeOrder(tool);
      }
    }
  }

  /**
   * Checks, through a helper method, if at least one daily order exists in the
   * arraylist of daily orders. If so, another check determines whether the latest
   * daily order is for the current date. If so, methods are called to update the
   * inventory quantities with the order information, and then to generate the
   * order text. The current (simulation) date is then incremented by one.
   */
  public void advanceToNextDay() {
    if (isOrderListPopulated()) {
      // Gets the latest daily order and checks to see whether the order is for the
      // current day. If so, the tool quantities are updated with the order
      // information, and the order text is generated through another method.
      Order currentOrder = orderList.get(orderList.size() - 1);
      if (currentOrder.getOrderDate() == getCurrentDate()) {
        currentOrder.updateToolQuantities();
        generateOrderText();
        System.out.println("\nNote: Above order exported to Order.txt.\n");
      } else
        System.out.println("*** No order was created for the day. ***\n");
    }

    // Increments the simulation date by one day.
    setCurrentDate(getCurrentDate().plusDays(1));
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

  /**
   * Generates the text for a daily order.
   */
  private void generateOrderText() {
    String ordersText = printSeparator();
    for (Order order : orderList)
      ordersText += order.printOrderLines() + "\n" + printSeparator();

    printOrderToConsole(ordersText);
    printOrderToFile(ordersText);
  }

  /**
   * Takes the daily order text and prints it to a text file.
   * 
   * @param ordersText The daily order text information.
   */
  private void printOrderToFile(String ordersText) {
    try {
      PrintWriter writer = new PrintWriter(ORDER_FILE_NAME, "UTF-8");
      writer.println(ordersText);
      writer.close();
    } catch (Exception e) {
      System.out.println("Error: Could not write order to text file.");
    }
  }

  /**
   * Takes the daily order text and prints it to the console.
   * 
   * @param ordersText The daily order text information.
   */
  private void printOrderToConsole(String ordersText) {
    System.out.println(ordersText);
  }

  /**
   * Checks whether the daily order arraylist points to any objects (is
   * populated).
   * 
   * @return boolean Returns true if at least one daily order exists in the
   *         arraylist.
   */
  private boolean isOrderListPopulated() {
    boolean isOrderPopulated = true;
    if (orderList.size() == 0) {
      isOrderPopulated = false;
    }

    return isOrderPopulated;
  }

  /**
   * Checks whether an order for a tool should be added to a new daily order (if
   * one doesn't already exist), or added to an existing daily order (if one
   * exists for the current day).
   * 
   * @param tool The tool for which an order will be made.
   */
  private void makeOrder(Tool tool) {
    // Checks whether an order exists within the order arraylist, creating a new
    // order if no daily orders exist.
    if (isOrderListPopulated()) {
      // Checks whether the latest daily order that was created is for the current
      // date. If not, a new daily order is created. If so, the current tool orderline
      // is added to the existing daily order.
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

  /**
   * Adds the current tool orderline to an existing order for that day.
   * 
   * @param tool         The tool to be added to the order.
   * @param currentOrder The current daily order of tools.
   */
  private void addToCurrentOrder(Tool tool, Order currentOrder) {
    int orderSize = REQUIRED_QUANTITY - tool.getQuantity();
    // Calls a method to handle the case where an orderline for a tool already
    // exists in the daily order. Either way, the tool orderline is then added to
    // the daily order.
    currentOrder.removeOrderLineForSameTool(tool);
    currentOrder.addOrderLine(tool, orderSize);
  }

  /**
   * Creates a new daily order if one does not already exist, and calls a method
   * to add the tool orderline to the order.
   * 
   * @param tool The tool to be ordered.
   */
  private void createNewDailyOrder(Tool tool) {
    Order newDailyOrder = new Order(getCurrentDate());
    orderList.add(newDailyOrder);
    int orderSize = REQUIRED_QUANTITY - tool.getQuantity();
    newDailyOrder.addOrderLine(tool, orderSize);
  }

  /**
   * Returns a 70-character separator for the order printout.
   * 
   * @return String A string containing a 70-character separator.
   */
  private String printSeparator() {
    return ("*".repeat(70));
  }
}