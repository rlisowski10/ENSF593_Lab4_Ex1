import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileNotFoundException;

public class FileManager {

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public ArrayList<Supplier> loadSupplierList() {
    ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
    String supplierFilePath = "./resources/suppliers.txt";
    String[] supplierFileContents = fileReader(supplierFilePath).split("\r\n");

    for (String supplierTextLine : supplierFileContents) {
      String[] supplierInfo = supplierTextLine.split(";");

      int id = Integer.parseInt(supplierInfo[0]);
      String name = supplierInfo[1];
      String address = supplierInfo[2];
      String contact = supplierInfo[3];

      Supplier supplier = new Supplier(id, name, address, contact);
      supplierList.add(supplier);
    }

    return supplierList;
  }

  public Inventory loadInventory(ArrayList<Supplier> supplierList) {

    ArrayList<Tool> toolList = new ArrayList<Tool>();

    String inventoryFilePath = "./resources/items.txt";
    String[] inventoryFileContents = fileReader(inventoryFilePath).split("\r\n");

    for (String inventoryTextLine : inventoryFileContents) {
      String[] inventoryInfo = inventoryTextLine.split(";");

      int id = Integer.parseInt(inventoryInfo[0]);
      String name = inventoryInfo[1];
      int quantity = Integer.parseInt(inventoryInfo[2]);
      double price = Double.parseDouble(inventoryInfo[3]);
      Supplier supplier = findSupplier(inventoryInfo[4], supplierList);

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

  public static void main(String[] args) throws FileNotFoundException {
    FileManager fileManager = new FileManager();
    ArrayList<Supplier> supplierList = new ArrayList<Supplier>();

    supplierList = fileManager.loadSupplierList();
    fileManager.loadInventory(supplierList);
  }
}