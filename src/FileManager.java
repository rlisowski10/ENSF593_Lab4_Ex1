import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class FileManager {

  // ============================================================
  // Public Instance Methods
  // ============================================================

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

  private ArrayList<Supplier> loadSupplierList() {
    ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
    String supplierFilePath = "./resources/suppliers.txt";
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

  private Inventory loadInventory(ArrayList<Supplier> suppliers, OrderRepository orderRepository) {
    ArrayList<Tool> toolList = new ArrayList<Tool>();

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

    orderRepository.updateOrder(toolList);
    Inventory inventory = new Inventory(toolList);
    return inventory;
  }

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