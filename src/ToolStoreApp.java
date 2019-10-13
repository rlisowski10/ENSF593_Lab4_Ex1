import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

// TODO JavaDocs

/**
 * Displays the user interface to the console, and allows for user interaction
 * with the application.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-10-12
 */
public class ToolStoreApp {

  // ============================================================
  // Public Instance Methods
  // ============================================================

  /**
   * Displays the front-end menu to the user, and handles returning to the menu.
   */
  public void displayMenu() {
    Scanner scan = new Scanner(System.in);
    boolean showMenu = true;

    FileManager fileManager = new FileManager();
    Shop shop = fileManager.loadShop();

    while (showMenu) {
      clearConsole();
      System.out.println("Welcome to the Internal Supply Management System");
      System.out.println(shop.getCurrentDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
      System.out.print("\n1. List all tools" + "\n2. Search for tool by tool name" + "\n3. Search for tool by ID"
          + "\n4. Check item quantity by ID" + "\n5. [Testing] Simulate sale of item (-25 quantity)"
          + "\n6. [Testing] Advance to next day (create order)" + "\n7. Quit"
          + "\n\nPlease enter a number from the list: ");

      showMenu = scanMenuInput(scan, shop);

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

  /**
   * Clears the console for the next display of the user-interface.
   */
  private void clearConsole() {
    int numRowsToAdd = 200;

    // Prints a specified number of rows to 'clear' the console window for the user.
    // This is the most system-agnostic way to ensure a clean user interface.
    for (int i = 0; i < numRowsToAdd; i++)
      System.out.println("");
  }

  /**
   * Scans for the user-input in the menu, and directs the input to the proper
   * functionality.
   * 
   * @param scan A scanner for user-input.
   * @param shop The Shop object, which is the main object-containiner for the
   *             back-end.
   * @return boolean A boolean to direct whether to show the user-interface to the
   *         user or not.
   */
  private boolean scanMenuInput(Scanner scan, Shop shop) {
    boolean showMenu = true;
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

  /**
   * Calls a method to display all tools in inventory to the user.
   * 
   * @param shop The Shop object, which is the main object-containiner for the
   *             back-end.
   */
  private void listAllTools(Shop shop) {
    clearConsole();
    System.out.println(shop.getInventory());
  }

  /**
   * Allows the user to search for any tool by name (case-agnostic).
   * 
   * @param scan A scanner for user-input.
   * @param shop The Shop object, which is the main object-containiner for the
   *             back-end.
   */
  private void searchToolByName(Scanner scan, Shop shop) {
    clearConsole();
    System.out.print("Please enter the tool name: ");
    String userInput = scan.nextLine();

    System.out.print(shop.searchToolByName(userInput));
  }

  /**
   * Allows the user to search for any tool by ID number.
   * 
   * @param scan             A scanner for user-input.
   * @param shop             The Shop object, which is the main object-containiner
   *                         for the back-end.
   * @param inventoryProcess A string that dictates the back-end functionality in
   *                         the Inventory class. Providing this string allows for
   *                         substantial code reuse.
   */
  private void searchToolByID(Scanner scan, Shop shop, String inventoryProcess) {
    clearConsole();
    System.out.print("Please enter the tool ID: ");
    String userInput = scan.nextLine();

    System.out.print(shop.searchToolByID(userInput, inventoryProcess));
  }

  /**
   * Calls a method to advance the application to the next day, for simulation and
   * testing purposes.
   * 
   * @param shop The Shop object, which is the main object-containiner for the
   *             back-end.
   */
  private void advanceToNextDay(Shop shop) {
    clearConsole();
    shop.advanceToNextDay();
  }

  // ============================================================
  // Static Methods
  // ============================================================

  /**
   * The main method, from which the program initially starts.
   * 
   * @param args Console arguments; unused in this case.
   */
  public static void main(String[] args) {
    ToolStoreApp toolStoreApp = new ToolStoreApp();
    toolStoreApp.displayMenu();
  }
}