import java.util.ArrayList;

public class SupplierList {

  // ============================================================
  // Member Variables
  // ============================================================

  private ArrayList<Supplier> supplierList = new ArrayList<Supplier>();

  // ============================================================
  // Constructors
  // ============================================================

  public SupplierList(ArrayList<Supplier> supplierList) {
    this.setSupplierList(supplierList);
  }

  // ============================================================
  // Accessors
  // ============================================================

  public void setSupplierList(ArrayList<Supplier> supplierList) {
    this.supplierList = supplierList;
  }
}