
public class Item {

	private String itemID;
	private String name;
	private String stockStatus;
	private double price;
	
//price
	public double getPrice() {
        return price;
    }//close get
    public void setPrice(double price) {
        this.price = price;
    }//close set

//itemID
    public String getItemID() {
        return itemID;
    }//close get
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }//close set
    
//stockStatus
    public String inStockStatus() {
        return stockStatus;
    }//close get
    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }//close set
    
//name
    public String getName() {
    	return name;
    }//close get
    public void setName(String name) {
    	this.name = name;
    }//close set
} //close item
