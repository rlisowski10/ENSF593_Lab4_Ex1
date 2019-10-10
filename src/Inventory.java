import java.util.ArrayList;

public class Inventory {

  private ArrayList<Tool> toolList = new ArrayList<Tool>();

  public Inventory(ArrayList<Tool> toolList) {
    this.setToolList(toolList);
  }

  // REMOVE THESE GETTER/SETTER*****************
  public ArrayList<Tool> getToolList() {
    return toolList;
  }

  public void setToolList(ArrayList<Tool> toolList) {
    this.toolList = toolList;
  }
  // ***************************
}