import java.time.LocalDate;

/**
 * Stores objects for the supplier list, inventory, and order repository.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-10-12
 */
public class Shop {

  // ============================================================
  // Member Variables
  // ============================================================

  private SupplierList supplierList;
  private Inventory inventory;
  private OrderRepository orderRepository;

  // ============================================================
  // Constructors
  // ============================================================

  /**
   * A constructor for the Shop class, which acts as a container for other major
   * portions of the application's functionality and in-memory data storage.
   * 
   * @param supplierList    Holds a list of all of the tool suppliers.
   * @param inventory       Holds a list of all tools in stock.
   * @param orderRepository Holds a list of all daily orders for tools.
   */
  public Shop(SupplierList supplierList, Inventory inventory, OrderRepository orderRepository) {
    setSupplierList(supplierList);
    setInventory(inventory);
    setOrderRepository(orderRepository);
  }

  // ============================================================
  // Accessors
  // ============================================================

  /**
   * Gets an array list of tool suppliers.
   * 
   * @return SupplierList An array list of tool suppliers.
   */
  public SupplierList getSupplierList() {
    return supplierList;
  }

  /**
   * Sets an object holding the array list of tool suppliers.
   * 
   * @param supplierList The object holding an array list of tool suppliers.
   */
  public void setSupplierList(SupplierList supplierList) {
    this.supplierList = supplierList;
  }

  /**
   * Gets an object holding the array list of tools.
   * 
   * @return Inventory The object holding an array list of tools.
   */
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * Sets the object holding the array list of tools.
   * 
   * @param inventory The object holding the array list of tools.
   */
  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Gets the object holding the array list of daily orders.
   * 
   * @return OrderRepository The object holding an array list of daily orders.
   */
  public OrderRepository getOrderRepository() {
    return orderRepository;
  }

  /**
   * Sets the object holding an array list of daily orders.
   * 
   * @param orderRepository The object holding an array list of daily orders.
   */
  public void setOrderRepository(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  // ============================================================
  // Public Instance Methods
  // ============================================================

  /**
   * Calls the search tool by name method in inventory.
   * 
   * @param providedToolName The user-inputted tool search parameter.
   * @return String The tool's information.
   */
  public String searchToolByName(String providedToolName) {
    return getInventory().searchToolByName(providedToolName);
  }

  /**
   * Calls the search tool by ID method in inventory.
   * 
   * @param providedToolID   The user-provided tool ID to search for.
   * @param inventoryProcess A string that dictates the back-end functionality in
   *                         the Inventory class. Providing this string allows for
   *                         substantial code reuse.
   * @return String The relevant tool information returned from the called method.
   */
  public String searchToolByID(String providedToolID, String inventoryProcess) {
    return getInventory().searchToolByID(providedToolID, inventoryProcess, getOrderRepository());
  }

  /**
   * Calls the method to advance to the next (simulation) day in the order
   * repository.
   */
  public void advanceToNextDay() {
    getOrderRepository().advanceToNextDay();
  }

  /**
   * Gets the current (simulation) day from the order repository.
   * 
   * @return LocalDate
   */
  public LocalDate getCurrentDate() {
    return getOrderRepository().getCurrentDate();
  }
}