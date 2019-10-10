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
}