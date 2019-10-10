import java.util.Scanner;

// TODO JavaDocs
// TODO Ensure all member variables calls are through getter/setter
// TODO Consider security throughout

public class ToolStoreApp {

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public void displayMenu() {
    Scanner scan = new Scanner(System.in);
    boolean showMenu = true;

    // Create a new Shop object, providing SupplierList and Inventory objects from
    // the FileManager. These arguments are necessary to ensure that ToolStoreApp
    // has dependency relationships only with Shop and FileManager.
    FileManager fileManager = new FileManager();

    // Oct 10 9:16am: The front-end is dependent on SupplierList, which is not
    // permitted. So I will instead have to return a Shop object from FileManager,
    // which is great anyways, since that will allow FileManager to take care of
    // back-end dependencies instead of the front-end class.
    SupplierList supplierList = fileManager.loadSupplierList();
    Shop shop = new Shop(supplierList, fileManager.loadInventory(supplierList));

    clearConsole();
    while (showMenu) {
      System.out.println("Welcome to the RobCo Industries Supply Management System:");
      System.out.print("\n1. [In Progress] List all tools" + "\n2. [Pending] Search for tool by tool name"
          + "\n3. [Pending] Search for tool by ID" + "\n4. [Pending] Check item quantity"
          + "\n5. [Pending] Add new tool" + "\n6. [Pending] Remove existing tool"
          + "\n7. [Pending] Decrease item quantity" + "\n8. Quit" + "\n\nPlease enter a number from the list: ");

      showMenu = scanMenuInput(scan, showMenu);

      if (showMenu) {
        System.out.println("\n*** Press enter to return to the menu. ***");
        scan.nextLine();
        scan.close();
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

  private boolean scanMenuInput(Scanner scan, boolean showMenu) {
    String userInput;
    userInput = scan.nextLine();
    userInput = userInput.replaceAll("\\W", "");

    switch (userInput) {
    case "1":
      // addPassengerUI(scan);
      break;
    case "8":
      System.out.println("\n*** Application Terminated ***\n");
      showMenu = false;
      break;
    }
    return showMenu;
  }

  // ============================================================
  // Static Methods
  // ============================================================

  public static void main(String[] args) {
    ToolStoreApp toolStoreApp = new ToolStoreApp();
    toolStoreApp.displayMenu();
  }
}