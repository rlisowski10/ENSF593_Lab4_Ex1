import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.Files;

public class FileManager {

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public void populateLists() throws FileNotFoundException {
    ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
    ArrayList<Tool> toolList = new ArrayList<Tool>();    
    String supplierFilePath = "./resources/suppliers.txt";
    String toolsFilePath = "./resources/items.txt";
    int var = 1;
  }

  public void fileReader(String filePath) {
    Scanner textScanner;

    try {
      String[] itemInfoSplit;
      File file = new File(filePath);
      textScanner = new Scanner(file);

      while (textScanner.hasNextLine()) {
        itemInfoSplit = textScanner.nextLine().split(";");
        System.out.println(itemInfoSplit[1]);
      }

      textScanner.close();
    } catch (Exception e) {
      System.out.println("Error: " + filePath + " could not be read.");
    }
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

  // ============================================================
  // Static Methods
  // ============================================================

  public static void main(String[] args) throws FileNotFoundException {
    FileManager fileManager = new FileManager();
    fileManager.populateToolList();
  }
}