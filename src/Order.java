import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;

public class Order {

  // ============================================================
  // Member Variables
  // ============================================================

  private ArrayList<OrderLine> orderLineList;
  private LocalDate orderDate;
  private int orderID;
  private final int MAX_RANDOM_ID = 99999;

  // ============================================================
  // Constructors
  // ============================================================

  public Order(LocalDate currentDate) {
    orderDate = currentDate;
    orderLineList = new ArrayList<OrderLine>();
    orderID = generateOrderID();
  }

  // ============================================================
  // Accessors
  // ============================================================

  public LocalDate getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDate orderDate) {
    this.orderDate = orderDate;
  }

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public void addOrderLine(Tool toolToOrder, int orderQuantity) {
    OrderLine orderLine = new OrderLine(toolToOrder, orderQuantity);
    orderLineList.add(orderLine);
  }

  public String printOrderLines() {
    String orderText = "";

    orderText += "\nORDER ID:\t\t" + orderID + "\nDate Ordered:\t\t"
        + orderDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

    for (OrderLine orderLine : orderLineList) {
      orderText += "\n\nItem description:\t" + orderLine.getToolName() + "\nAmount ordered:\t\t"
      + orderLine.getOrderAmount() + "\nSupplier:\t\t" + orderLine.getToolSupplier();
    }

    return orderText;
  }

  public void updateToolQuantities() {
    for (OrderLine orderLine : orderLineList)
      orderLine.updateToolQuantity();
  }

  public void removeOrderLineForSameTool(Tool tool) {
    int elementToRemove = 0;
    boolean removeElement = false;

    for (int i = 0; i < orderLineList.size(); i++) {
      if (tool.getName().equals(orderLineList.get(i).getToolName())) {
        elementToRemove = i;
        removeElement = true;
      }
    }

    if (removeElement)
      orderLineList.remove(elementToRemove);
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

  private int generateOrderID() {
    Random r = new Random();
    int randomID = (int) (MAX_RANDOM_ID * r.nextDouble());

    return randomID;
  }
}