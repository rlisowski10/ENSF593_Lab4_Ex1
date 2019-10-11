import java.util.ArrayList;

public class Inventory {

  // ============================================================
  // Member Variables
  // ============================================================

  private ArrayList<Tool> toolList = new ArrayList<Tool>();

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

    if (toolResult != null) {
      Supplier toolSupplier = toolResult.getSupplier();
      toolInfo = "\nTool Name: " + toolResult.getName() + "\nID: " + toolResult.getId() + "\nPrice: $" + toolResult.getPrice()
          + "\nSupplier: " + toolSupplier.getName();
    } else {
      toolInfo = "\nError: Tool not found.";
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
}