import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileNotFoundException;

public class FileManager {

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public void populateLists() throws FileNotFoundException {
    ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
    ArrayList<Tool> toolList = new ArrayList<Tool>();
    String supplierFilePath = "./resources/suppliers.txt";
    String toolsFilePath = "./resources/items.txt";

    String supplierTextContent = fileReader(supplierFilePath);
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
    fileManager.populateLists();
  }
}