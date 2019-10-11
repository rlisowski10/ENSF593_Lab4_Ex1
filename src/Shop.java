public class Shop {

  // ============================================================
  // Member Variables
  // ============================================================

  private SupplierList supplierList;
  private Inventory inventory;

  // ============================================================
  // Constructors
  // ============================================================

  public Shop(SupplierList supplierList, Inventory inventory) {
    this.supplierList = supplierList;
    this.inventory = inventory;
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
}