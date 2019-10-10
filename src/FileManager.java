import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileNotFoundException;

public class FileManager {

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public SupplierList loadSupplierList() {
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

    SupplierList supplierList = new SupplierList(suppliers);
    return supplierList;
  }

  public Inventory loadInventory(SupplierList supplierList) {
    ArrayList<Tool> toolList = new ArrayList<Tool>();
    ArrayList<Supplier> suppliers = supplierList.getSupplierList();

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

    Inventory inventory = new Inventory(toolList);
    return inventory;
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

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

  // ============================================================
  // Static Methods
  // ============================================================

  // Unit test to ensure FileManager outputs.
  public static void main(String[] args) throws FileNotFoundException {
    FileManager fileManager = new FileManager();

    SupplierList supplierList = fileManager.loadSupplierList();
    fileManager.loadInventory(supplierList);
  }
}