import java.util.ArrayList;

public class Inventory {

  // ============================================================
  // Member Variables
  // ============================================================

  private ArrayList<Tool> toolList;

  // ============================================================
  // Constructors
  // ============================================================

  public Inventory(ArrayList<Tool> toolList) {
    this.toolList = toolList;
  }

  // ============================================================
  // Public Instance Methods
  // ============================================================

  public String searchToolByName(String providedToolName) {
    String toolInfo = "";
    Tool toolResult = null;
    providedToolName = providedToolName.toLowerCase();

    for (Tool tool : toolList) {
      if (providedToolName.equals(tool.getName().toLowerCase()))
        toolResult = tool;
    }

    toolInfo = provideToolInfo(toolResult);
    return toolInfo;
  }

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

  @Override
  public String toString() {
    String inventoryToString = "List of tools in inventory: \n\n";
    for (Tool tool : toolList)
      inventoryToString += tool;

    return inventoryToString;
  }

  // ============================================================
  // Private Instance Methods
  // ============================================================

  private Tool getToolByID(String providedToolID) {
    Tool toolResult = null;
    boolean isNumeric = isNumeric(providedToolID);

    if (isNumeric) {
      int toolID = Integer.parseInt(providedToolID);
      for (Tool tool : toolList) {
        if (toolID == tool.getId())
          toolResult = tool;
      }
    }
    return toolResult;
  }

  private String provideToolInfo(Tool toolResult) {
    String toolInfo;
    if (toolResult != null) {
      Supplier toolSupplier = toolResult.getSupplier();
      toolInfo = "\nTool Name: " + toolResult.getName() + "\nID: " + toolResult.getId() + "\nPrice: $"
          + toolResult.getPrice() + "\nSupplier: " + toolSupplier.getName() + "\n\n";
    } else {
      toolInfo = "\nError: Tool not found.\n\n";
    }
    return toolInfo;
  }

  private String provideToolQuantity(Tool toolResult) {
    String toolInfo;
    if (toolResult != null)
      toolInfo = "\n" + toolResult.getName() + "\nQuantity in stock: " + toolResult.getQuantity() + "\n\n";
    else
      toolInfo = "\nError: Tool not found.\n\n";

    return toolInfo;
  }

  // TODO Potentially place this in Tool, like Dr. M shows
  private String decreaseToolQuantity(Tool toolResult, OrderRepository orderRepository) {
    String toolInfo;

    if (toolResult == null)
      toolInfo = "\nError: Tool not found.\n\n";
    else if (toolResult.getQuantity() >= 25) {
      int updatedQuantity = toolResult.getQuantity() - 25;
      toolInfo = "\nSale! " + toolResult.getName() + " decreases from " + toolResult.getQuantity()
          + " units to " + updatedQuantity + " units.\n\n";
      toolResult.setQuantity(updatedQuantity);
    } else {
      toolInfo = "\nWe have sold out of " + toolResult.getName() + "! The quantity is now at 0.\n\n";
      toolResult.setQuantity(0);
    }

    if (toolResult.getQuantity() < 40)
      orderRepository.updateOrder(toolList);

    return toolInfo;
  }

  private static boolean isNumeric(String str) {
    return str.matches("\\d+");
  }
}