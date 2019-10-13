import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Loads the initial tool and supplier datasets into the application, handing
 * over an instantiated Shop class to the front-end.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-10-12
 */
public class FileManager {

  // ============================================================
  // Public Instance Methods
  // ============================================================

  /**
   * The only public method for the File Manager class, which calls methods to
   * load the existing tool and supplier data into objects. The only return from
   * this class is the Shop object, which ensures that the front-end only depends
   * on File Manager and Shop.
   * 
   * @return Shop The Shop object, which is the main object-containiner for the
   *         back-end.
   */
  public Shop loadShop() {
    ArrayList<Supplier> suppliers = loadSupplierList();
    SupplierList supplierList = new SupplierList(suppliers);
    OrderRepository orderRepository = new OrderRepository();
    Inventory inventory = loadInventory(suppliers, orderRepository);

    Shop shop = new Shop(supplierList, inventory, orderRepository);
    return shop;
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

  /**
   * Opens up the suppliers text file, extracts the data in the correct format,
   * and stores it in supplier objects. The supplier objects are then added to a
   * list of suppliers.
   * 
   * @return ArrayList<Supplier> A list containing all of the individuals supplier
   *         objects.
   */
  private ArrayList<Supplier> loadSupplierList() {
    ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
    String supplierFilePath = "./resources/suppliers.txt";

    // Splits the text into individual lines by carriage return and newline
    // characters.
    String[] supplierFileContents = fileReader(supplierFilePath).split("\r\n");

    for (String supplierTextLine : supplierFileContents) {
      String[] supplierInfo = supplierTextLine.split(";");

      int id = Integer.parseInt(supplierInfo[0]);
      String name = supplierInfo[1];
      String address = supplierInfo[2];
      String contact = supplierInfo[3];

      Supplier supplier = new Supplier(id, name, address, contact);
      suppliers.add(supplier);
    }

    return suppliers;
  }

  /**
   * Opens up the tool text file, extracts the data in the correct format, and
   * stores it in tool objects. The tool objects are then added to a list of
   * tools.
   * 
   * @param suppliers       A list of supplier objects, containing all supplier
   *                        data.
   * @param orderRepository An object that holds all order-related data.
   * @return Inventory An object that holds a list of all tool data.
   */
  private Inventory loadInventory(ArrayList<Supplier> suppliers, OrderRepository orderRepository) {
    ArrayList<Tool> toolList = new ArrayList<Tool>();

    // Splits the text into individual lines by carriage return and newline
    // characters.
    String inventoryFilePath = "./resources/items.txt";
    String[] inventoryFileContents = fileReader(inventoryFilePath).split("\r\n");

    for (String inventoryTextLine : inventoryFileContents) {
      String[] inventoryInfo = inventoryTextLine.split(";");

      int id = Integer.parseInt(inventoryInfo[0]);
      String name = inventoryInfo[1];
      int quantity = Integer.parseInt(inventoryInfo[2]);
      double price = Double.parseDouble(inventoryInfo[3]);
      Supplier supplier = findSupplier(inventoryInfo[4], suppliers);

      Tool tool = new Tool(id, name, quantity, price, supplier);
      toolList.add(tool);
    }

    // Populates the initial order list with a new order of tools, based on the
    // initial quantities in the input tools list.
    orderRepository.updateOrder(toolList);
    Inventory inventory = new Inventory(toolList);
    return inventory;
  }

  /**
   * Opens an input text file and reads the contents into a string for processing.
   * 
   * @param filePath The location of the input text file.
   * @return String A string containing the complete contents of the text file.
   */
  private String fileReader(String filePath) {
    String fileContent = "";

    try {
      Path filepath = Paths.get(filePath);
      fileContent = Files.readString(filepath);
    } catch (Exception e) {
      System.out.println("Error: " + filePath + " could not be read.");
    }

    return fileContent;
  }

  /**
   * A method to iterate through the supplier list in search of a matching
   * supplier, using the supplier ID instance variable.
   * 
   * @param supplierID   The user-provided ID for a supplier.
   * @param supplierList A list containing all supplier object data.
   * @return Supplier The supplier
   */
  private Supplier findSupplier(String supplierID, ArrayList<Supplier> supplierList) {
    int id = Integer.parseInt(supplierID);
    Supplier matchingSupplier = null;

    for (Supplier supplier : supplierList) {
      if (id == supplier.getId()) {
        matchingSupplier = supplier;
        break;
      }
    }

    return matchingSupplier;
  }
}