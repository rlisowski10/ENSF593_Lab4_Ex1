import java.util.ArrayList;

public class SupplierList {

  // ============================================================
  // Member Variables
  // ============================================================

  private ArrayList<Supplier> suppliers;

  // ============================================================
  // Constructors
  // ============================================================

  public SupplierList(ArrayList<Supplier> suppliers) {
    this.setSupplierList(suppliers);
  }

  // ============================================================
  // Accessors
  // ============================================================

  public void setSupplierList(ArrayList<Supplier> suppliers) {
    this.suppliers = suppliers;
  }
}