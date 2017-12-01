package assetc.model;

public class Category {

  private int CategoryID;
  private String CategoryName;
  
  public int getCategoryID() {
    return CategoryID;
  }
  public void setCategoryID(int categoryID) {
    CategoryID = categoryID;
  }
  public String getCategoryName() {
    return CategoryName;
  }
  public void setCategoryName(String categoryName) {
    CategoryName = categoryName;
  }
  
  public Category() { }
  
  public Category(int categoryID, String categoryName) {
    setCategoryID(categoryID);
    setCategoryName(categoryName);
  }
  
}