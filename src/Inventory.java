import java.util.ArrayList;

/**
 * Holds an array list of the tools that the tool shop currently holds in the
 * inventory. Provides functionality for searching tools, along with extracting
 * information and decreasing their quantities through sales.
 * <p>
 *
 * @author Ryan Lisowski (ID: 00257796)
 * @version 1.0
 * @since 2019-10-12
 */
public class Inventory {

  // ============================================================
  // Member Variables
  // ============================================================

  private ArrayList<Tool> toolList;
  private final int SALE_QUANTITY = 25;

  // ============================================================
  // Constructors
  // ============================================================

  /**
   * Constructor to set the tool list data into the inventory.
   * 
   * @param toolList A list of tool data for the store.
   */
  public Inventory(ArrayList<Tool> toolList) {
    setToolList(toolList);
  }

  // ============================================================
  // Accessors
  // ============================================================

  /**
   * Sets the tool list data for the store.
   * 
   * @param toolList A list of tool data for the store.
   */
  public void setToolList(ArrayList<Tool> toolList) {
    this.toolList = toolList;
  }

  // ============================================================
  // Public Instance Methods
  // ============================================================

  /**
   * Allows for the search of a tool by name.
   * 
   * @param providedToolName The search name of the tool.
   * @return String The tool information in string format.
   */
  public String searchToolByName(String providedToolName) {
    String toolInfo = "";
    Tool toolResult = null;

    // Iterates through the tool list until a tool with the searched name is found.
    providedToolName = providedToolName.toLowerCase();
    for (Tool tool : toolList) {
      if (providedToolName.equals(tool.getName().toLowerCase()))
        toolResult = tool;
    }

    toolInfo = provideToolInfo(toolResult);
    return toolInfo;
  }

  /**
   * Allows for the search of a tool by ID.
   * 
   * @param providedToolID   The search ID for the tool.
   * @param inventoryProcess A string passed from the front-end that allows for a
   *                         switch to take place to determine the next processing
   *                         steps to take.
   * @param orderRepository  An object that holds all order-related data.
   * @return String A string containing information about the processing that took
   *         place for a tool.
   */
  public String searchToolByID(String providedToolID, String inventoryProcess, OrderRepository orderRepository) {
    Tool toolResult = getToolByID(providedToolID);

    String toolInfo = "";

    switch (inventoryProcess) {
    case "ShowAll":
      toolInfo = provideToolInfo(toolResult);
      break;
    case "ShowQuantity":
      toolInfo = provideToolQuantity(toolResult);
      break;
    case "DecreaseQuantity":
      toolInfo = decreaseToolQuantity(toolResult, orderRepository);
      break;
    default:
      System.out.println("\nError: Could not locate back-end inventory process.\n");
    }

    return toolInfo;
  }

  /**
   * The toString override method that displays a list of the inventory.
   * 
   * @return String A list of the inventory.
   */
  @Override
  public String toString() {
    String inventoryToString = "List of tools in inventory: \n\n";

    // Calls each tool's toString method to append onto the existing inventory
    // string.
    for (Tool tool : toolList)
      inventoryToString += tool;
    return inventoryToString;
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

  /**
   * Takes in a tool ID, sends the ID off to another method to ensure that the
   * values are numeric, and then iterates through the tool list to determine if a
   * tool exists with a matching ID.
   * 
   * @param providedToolID The search ID for the tool.
   * @return Tool Either a null value, or the tool matching the search ID.
   */
  private Tool getToolByID(String providedToolID) {
    Tool toolResult = null;
    boolean isNumeric = isNumeric(providedToolID);

    // Iterates through each tool to see if there is a match for the provided ID.
    if (isNumeric) {
      int toolID = Integer.parseInt(providedToolID);
      for (Tool tool : toolList) {
        if (toolID == tool.getId())
          toolResult = tool;
      }
    }
    return toolResult;
  }

  /**
   * Provides all relevant data for a tool in string format back to the calling
   * method.
   * 
   * @param tool The tool to get all associated information for.
   * @return String Contains all of the associated tool information in string
   *         format.
   */
  private String provideToolInfo(Tool tool) {
    String toolInfo;

    if (tool != null) {
      toolInfo = "\nTool Name: " + tool.getName() + "\nID: " + tool.getId() + "\nPrice: $" + tool.getPrice()
          + "\nSupplier: " + tool.getSupplierName() + "\n\n";
    } else {
      toolInfo = "\nError: Tool not found.\n\n";
    }
    return toolInfo;
  }

  /**
   * Provides back the quantity information for a tool in string format.
   * 
   * @param tool The tool to get the quantity information for.
   * @return String Contains the quantity information for a tool.
   */
  private String provideToolQuantity(Tool tool) {
    String toolInfo;
    if (tool != null)
      toolInfo = "\n" + tool.getName() + "\nQuantity in stock: " + tool.getQuantity() + "\n\n";
    else
      toolInfo = "\nError: Tool not found.\n\n";

    return toolInfo;
  }

  /**
   * Decreases a tool's stock (quantity) by a certain amount, which is declared as
   * a constant number in this class.
   * 
   * @param tool            The tool to decrease the held stock amount for.
   * @param orderRepository An object that will update the daily order list if a
   *                        tool falls below a minimum quantity value.
   * @return String An update message to display to the user regarding the tool's
   *         quantity.
   */
  private String decreaseToolQuantity(Tool tool, OrderRepository orderRepository) {
    String toolInfo;

    // Updates the tool's quantity if a sale of the tool has been made, checking to
    // ensure that the quantity does not fall below 0 for a tool.
    if (tool == null)
      toolInfo = "\nError: Tool not found.\n\n";
    else if (tool.getQuantity() >= SALE_QUANTITY) {
      int updatedQuantity = tool.getQuantity() - SALE_QUANTITY;
      toolInfo = "\nSale! " + tool.getName() + " decreases from " + tool.getQuantity() + " units to " + updatedQuantity
          + " units.\n\n";
      tool.setQuantity(updatedQuantity);
    } else {
      toolInfo = "\nWe have sold out of " + tool.getName() + "! The quantity is now at 0.\n\n";
      tool.setQuantity(0);
    }

    // Has the order repository process a tool order if the tool's quantity falls
    // below a threshold.
    if (tool.getQuantity() < orderRepository.getMIN_QUANTITY())
      orderRepository.updateOrder(toolList);

    return toolInfo;
  }

  /**
   * Checks whether a string input from the user is numeric, using a simple
   * regular expression.
   * 
   * @param str The user-input string.
   * @return boolean Returns true if the input string is numeric.
   */
  private static boolean isNumeric(String str) {
    return str.matches("\\d+");
  }
}