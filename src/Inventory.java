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

    toolInfo = provideToolInfo(toolResult);
    return toolInfo;
  }

  public String searchToolByID(String providedToolID) {
    String toolInfo = "";
    Tool toolResult = null;
    boolean isNumeric = isNumeric(providedToolID);

    if (isNumeric) {
      int toolID = Integer.parseInt(providedToolID);
      for (Tool tool : toolList) {
        if (toolID == tool.getId())
          toolResult = tool;
      }
    }

    toolInfo = provideToolInfo(toolResult);
    return toolInfo;
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

  private static boolean isNumeric(String str) {
    return str.matches("\\d+");
  }

  @Override
  public String toString() {
    String inventoryToString = "List of tools in inventory: \n\n";
    for (Tool tool : toolList)
      inventoryToString += tool;

    return inventoryToString;
  }
}