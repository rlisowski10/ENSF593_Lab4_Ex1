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

  public Shop(SupplierList supplierList, Inventory inventory, OrderRepository orderRepository) {
    this.supplierList = supplierList;
    this.inventory = inventory;
    this.orderRepository = orderRepository;
  }

  // ============================================================
  // Accessors
  // ============================================================

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public void displayInventory() {
    System.out.println(inventory);
  }

  public String searchToolByName(String providedToolName) {
    return inventory.searchToolByName(providedToolName);
  }

  public String searchToolByID(String providedToolID, String inventoryProcess) {
    return inventory.searchToolByID(providedToolID, inventoryProcess, orderRepository);
  }
}