/**
 * Stores the data for each individual supplier.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-10-12
 */
public class Supplier {

  // ============================================================
  // Member Variables
  // ============================================================

  private int id;
  private String name;
  private String address;
  private String contact;

  // ============================================================
  // Constructors
  // ============================================================

  /**
   * Constructs the supplier with all of the relevant supplier information.
   * 
   * @param id      The supplier ID.
   * @param name    The name of the supplier.
   * @param address The address for the supplier.
   * @param contact The contact information for the supplier.
   */
  public Supplier(int id, String name, String address, String contact) {
    this.setId(id);
    this.setName(name);
    this.setAddress(address);
    this.setContact(contact);
  }

  // ============================================================
  // Accessors
  // ============================================================

  /**
   * Sets the contact information for the supplier.
   * 
   * @param contact The contact information for the supplier.
   */
  public void setContact(String contact) {
    this.contact = contact;
  }

  /**
   * Sets the address information for the supplier.
   * 
   * @param address The address information for the supplier.
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Gets the name of the supplier.
   * 
   * @return String The name of the supplier.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the supplier.
   * 
   * @param name The name of the supplier.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the ID for the supplier.
   * 
   * @return int The ID for the supplier.
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the ID for the supplier.
   * 
   * @param id The ID for the supplier.
   */
  public void setId(int id) {
    this.id = id;
  }
}