import java.util.ArrayList;
import java.time.LocalDate;

public class Order {

  // ============================================================
  // Member Variables
  // ============================================================

  private ArrayList<OrderLine> orderLineList;
  private LocalDate orderDate;
  private int orderID;

  // ============================================================
  // Constructors
  // ============================================================

  public Order(LocalDate currentDate) {
    orderDate = currentDate;
    orderLineList = new ArrayList<OrderLine>();
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

  // TODO Add functionality to check for existing tool orderline
  public void addOrderLine(Tool toolToOrder, int orderQuantity) {
    OrderLine orderLine = new OrderLine(toolToOrder, orderQuantity);
    orderLineList.add(orderLine);
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================
}