import java.util.ArrayList;

public class Shop {

  private SupplierList supplierList;
  private Inventory inventory;

  public Shop(SupplierList supplierList, Inventory inventory) {
    this.supplierList = supplierList;
    this.inventory = inventory;

    // To delete below.... ***************************
    ArrayList<Supplier> temp = supplierList.getSupplierList();
    Supplier temp2 = temp.get(14);
    temp2.setContact("Ryan's Test");

    ArrayList<Tool> temp3 = inventory.getToolList();
    Tool temp4 = temp3.get(0);
    Supplier temp5 = temp4.getSupplier();

    System.out.println(temp5.getContact());
    System.out.println("Test");
  }
}