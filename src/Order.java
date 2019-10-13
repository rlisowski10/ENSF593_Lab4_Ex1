import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;

/**
 * Holds the data for each daily order, including an arraylist of orderlines for
 * each tool that has been ordered. Allows new tools to be ordered, and displays
 * the relevant individual tool order information to the user.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-10-12
 */
public class Order {

  // ============================================================
  // Member Variables
  // ============================================================

  private ArrayList<OrderLine> orderLineList;
  private LocalDate orderDate;
  private int orderID;
  private static final int MAX_RANDOM_ID = 99999;

  // ============================================================
  // Constructors
  // ============================================================

  /**
   * Constructor that sets the order date to the current (simulation) date for the
   * program, generates a random order ID, and creates a new list of orderlines
   * for the order.
   * 
   * @param currentDate The current (simulation) date for the application.
   */
  public Order(LocalDate currentDate) {
    setOrderDate(currentDate);
    orderLineList = new ArrayList<OrderLine>();
    setOrderID(generateOrderID());
  }

  // ============================================================
  // Accessors
  // ============================================================

  /**
   * Gets the order date for the order.
   * 
   * @return LocalDate The order date for the order.
   */
  public LocalDate getOrderDate() {
    return orderDate;
  }

  /**
   * Sets the order date for the order.
   * 
   * @param orderDate The order date for the order.
   */
  public void setOrderDate(LocalDate orderDate) {
    this.orderDate = orderDate;
  }

  /**
   * Gets the 5-digit randomly-generated ID for the order.
   * 
   * @return int The 5-digit randomly-generated ID for the order.
   */
  public int getOrderID() {
    return orderID;
  }

  /**
   * Sets the order ID for the order.
   * 
   * @param orderID The order ID for the order.
   */
  public void setOrderID(int orderID) {
    this.orderID = orderID;
  }

  // ============================================================
  // Public Instance Methods
  // ============================================================

  /**
   * Adds a tool orderline to the current order.
   * 
   * @param toolToOrder   The tool that is to be ordered.
   * @param orderQuantity The quantity of the tool to be ordered.
   */
  public void addOrderLine(Tool toolToOrder, int orderQuantity) {
    OrderLine orderLine = new OrderLine(toolToOrder, orderQuantity);
    orderLineList.add(orderLine);
  }

  /**
   * Prints the data for the tool orderline to a string variable.
   * 
   * @return String Holds the text for the orderline.
   */
  public String printOrderLines() {
    String orderText = "";

    orderText += "\nORDER ID:\t\t" + getOrderID() + "\nDate Ordered:\t\t"
        + getOrderDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

    for (OrderLine orderLine : orderLineList) {
      orderText += "\n\nItem description:\t" + orderLine.getToolName() + "\nAmount ordered:\t\t"
          + orderLine.getOrderAmount() + "\nSupplier:\t\t" + orderLine.getToolSupplier();
    }

    return orderText;
  }

  /**
   * Iterates through each orderline, updating the associated tool with the new
   * tool quantity.
   */
  public void updateToolQuantities() {
    for (OrderLine orderLine : orderLineList)
      orderLine.updateToolQuantity();
  }

  /**
   * Checks the list that contains the daily orderlines to see if an orderline
   * already exists for that tool. If so, the old orderline is replaced by a a new
   * orderline for the tool.
   * 
   * @param tool The tool that an orderline will be created for.
   */
  public void removeOrderLineForSameTool(Tool tool) {
    int elementToRemove = 0;
    boolean removeElement = false;

    // Iterates through the orderline list to check for an existing orderline for
    // the tool.
    for (int i = 0; i < orderLineList.size(); i++) {
      if (tool.getName().equals(orderLineList.get(i).getToolName())) {
        elementToRemove = i;
        removeElement = true;
      }
    }

    // If an existing orderline is found for the tool, the existing orderline for
    // that tool is replaced by a new tool orderline.
    if (removeElement)
      orderLineList.remove(elementToRemove);
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

  /**
   * Randomly generates a 5-digit order ID.
   * 
   * @return int A 5-digit randomly assigned order ID.
   */
  private int generateOrderID() {
    Random r = new Random();
    int randomID = (int) (MAX_RANDOM_ID * r.nextDouble());

    return randomID;
  }
}