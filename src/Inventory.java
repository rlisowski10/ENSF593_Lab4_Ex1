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

  @Override
  public String toString() {
    String inventoryToString = "List of tools in inventory: \n\n";
    for (Tool tool : toolList)
      inventoryToString += tool;

    return inventoryToString;
  }
}