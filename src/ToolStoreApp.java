import java.util.Scanner;

// TODO JavaDocs
// TODO Ensure all member variables calls are through getter/setter
// TODO Consider security throughout
// TODO Check for SRP throughout, and move methods if needed

public class ToolStoreApp {

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public void displayMenu() {
    Scanner scan = new Scanner(System.in);
    boolean showMenu = true;

    FileManager fileManager = new FileManager();
    Shop shop = fileManager.loadShop();

    while (showMenu) {
      clearConsole();
      System.out.println("Welcome to the ROBCO Industries Supply Management System");
      System.out.println("\"We're in the business of happiness.\"");
      System.out.print("\n1. List all tools" + "\n2. Search for tool by tool name" + "\n3. Search for tool by ID"
          + "\n4. Check item quantity by ID" + "\n5. [Testing] Simulate sale of item (-25 quantity)"
          + "\n6. [Testing] Advance to next day / Create order" + "\n7. Quit"
          + "\n\nPlease enter a number from the list: ");

      showMenu = scanMenuInput(scan, showMenu, shop);

      if (showMenu) {
        System.out.println("*** Press enter to return to the menu. ***");
        scan.nextLine();
      } else {
        scan.close();
        break;
      }
    }
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

  private void clearConsole() {
    int numRowsToAdd = 200;

    // Prints a specified number of rows to 'clear' the console window for the user.
    // This is the most system-agnostic way to ensure a clean user interface.
    for (int i = 0; i < numRowsToAdd; i++)
      System.out.println("");
  }

  private boolean scanMenuInput(Scanner scan, boolean showMenu, Shop shop) {
    String userInput = scan.nextLine();
    String inventoryProcess;

    switch (userInput) {
    case "1":
      listAllTools(shop);
      break;
    case "2":
      searchToolByName(scan, shop);
      break;
    case "3":
      inventoryProcess = "ShowAll";
      searchToolByID(scan, shop, inventoryProcess);
      break;
    case "4":
      inventoryProcess = "ShowQuantity";
      searchToolByID(scan, shop, inventoryProcess);
      break;
    case "5":
      inventoryProcess = "DecreaseQuantity";
      searchToolByID(scan, shop, inventoryProcess);
      break;
    case "6":
      advanceToNextDay(shop);
      break;
    case "7":
      System.out.println("\n*** Shutting Down ***\n");
      showMenu = false;
      break;
    default:
      System.out.println("\nError: Please enter a valid menu number.\n");
    }
    return showMenu;
  }

  private void listAllTools(Shop shop) {
    clearConsole();
    shop.displayInventory();
  }

  private void searchToolByName(Scanner scan, Shop shop) {
    clearConsole();
    System.out.print("Please enter the tool name: ");
    String userInput = scan.nextLine();

    System.out.print(shop.searchToolByName(userInput));
  }

  private void searchToolByID(Scanner scan, Shop shop, String inventoryProcess) {
    clearConsole();
    System.out.print("Please enter the tool ID: ");
    String userInput = scan.nextLine();

    System.out.print(shop.searchToolByID(userInput, inventoryProcess));
  }
  
  private void advanceToNextDay(Shop shop) {
    shop.advanceToNextDay();
  }

  // ============================================================
  // Static Methods
  // ============================================================

  public static void main(String[] args) {
    ToolStoreApp toolStoreApp = new ToolStoreApp();
    toolStoreApp.displayMenu();
  }
}