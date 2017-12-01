package assetc.model;

public class Product {

  private int ProductID;
  private String ProductName;
  private assetc.model.Supplier Supplier;
  private assetc.model.Category Category;
  private float UnitPrice;
  private int UnitsInStock;
  private Boolean Discontinued;
  
  public int getProductID() {
    return ProductID;
  }
  public void setProductID(int productID) {
    ProductID = productID;
  }
  public String getProductName() {
    return ProductName;
  }
  public void setProductName(String productName) {
    ProductName = productName;
  }
  public float getUnitPrice() {
    return UnitPrice;
  }
  public void setUnitPrice(float unitPrice) {
    UnitPrice = unitPrice;
  }
  public int getUnitsInStock() {
    return UnitsInStock;
  }
  public void setUnitsInStock(int unitsInStock) {
    UnitsInStock = unitsInStock;
  }
  public Boolean getDiscontinued() {
    return Discontinued;
  }
  public void setDiscontinued(Boolean discontinued) {
    Discontinued = discontinued;
  }
  public assetc.model.Supplier getSupplier() {
    return Supplier;
  }
  public void setSupplier(assetc.model.Supplier supplier) {
    Supplier = supplier;
  }
  public assetc.model.Category getCategory() {
    return Category;
  }
  public void setCategory(assetc.model.Category category) {
    Category = category;
  }
  
}