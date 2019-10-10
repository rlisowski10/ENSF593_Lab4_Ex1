import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileNotFoundException;

// TODO Create two methods in here (1. return SupplierList 2. return Inventory)

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

  public Inventory loadInventory() {
    Inventory inventory = new Inventory();

    String inventoryFilePath = "./resources/items.txt";
    String[] inventoryFileContents = fileReader(inventoryFilePath).split("\r\n");

    for (String inventoryTextLine : inventoryFileContents) {
      String[] inventoryInfo = inventoryTextLine.split(";");

      int id = Integer.parseInt(inventoryInfo[0]);
      String name = inventoryInfo[1];
      String address = inventoryInfo[2];
      String contact = inventoryInfo[3];

      inventory inventory = new inventory(id, name, address, contact);
      inventoryList.add(inventory);
    }

    return inventory;
  }

  public String fileReader(String filePath) {
    String fileContent = "";

    try {
      Path filepath = Paths.get(filePath);
      fileContent = Files.readString(filepath);
    } catch (Exception e) {
      System.out.println("Error: " + filePath + " could not be read.");
    }

    return fileContent;
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

  // ============================================================
  // Static Methods
  // ============================================================

  public static void main(String[] args) throws FileNotFoundException {
    FileManager fileManager = new FileManager();
    fileManager.loadSupplierList();
  }
}