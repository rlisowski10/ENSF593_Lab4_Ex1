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
  // Public Instance Methods
  // ============================================================

  public void displayToolList() {
    System.out.println(inventory);
  }
}