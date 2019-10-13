import java.util.ArrayList;

/**
 * Stores the supplier list information for the store.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-10-12
 */
public class SupplierList {

  // ============================================================
  // Member Variables
  // ============================================================

  private ArrayList<Supplier> suppliers;

  // ============================================================
  // Constructors
  // ============================================================

  /**
   * Constructor for the list of suppliers.
   * 
   * @param suppliers An arraylist of suppliers.
   */
  public SupplierList(ArrayList<Supplier> suppliers) {
    this.suppliers = suppliers;
  }
}