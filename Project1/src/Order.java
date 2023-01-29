/*  Name:  Asma Ahmed
     Course: CNT 4714 – Spring 2023 
     Assignment title: Project 1 – Event-driven Enterprise Simulation 
     Date: Sunday January 29, 2023 
*/ 

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Order {

	private int currentNumItems = 0;
    private double orderSubtotal = 0;
    private double orderTotal = 0;
    private int totalItems = 0;
    private int maxNumItems = -1;
    private String fileName = "transactions.txt";
    
    private ArrayList<String> items = new ArrayList<>(); // Final ArrayList for confirmed items
    private StringBuilder viewOrder = new StringBuilder();
    private StringBuilder finishOrder = new StringBuilder();

    File file = new File(fileName);
    String[] itemDetails = new String[6];
    
    BigDecimal num = new BigDecimal("58.987");
    
    //getFinishOrder
    public String getFinishOrder() {
        return this.finishOrder.toString();
    }//close getFinishOrder
    
    //setFinishOrder
    public void setFinishOrder(String date, String time) {
        this.setOrderTotal();
        this.finishOrder.append("NileDotCom");
        this.finishOrder.append(System.getProperty("line.separator"));        
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Date: " + date + " | " + "Time: " + time);
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Number of items: " + this.getTotalItems());
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Item # / ID / Price / Quantity / Discount % / Subtotal");
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(this.getViewOrder());
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Order subtotal:   $" + new DecimalFormat("#0.00").format(this.getOrderSubtotal()));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Tax Rate: 6%");
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Tax: " + new DecimalFormat("#0.00").format(this.getOrderTotal() * 0.06));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Order total:      $" + new DecimalFormat("#0.00").format(this.getOrderTotal()));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append(System.getProperty("line.separator"));
        this.finishOrder.append("Thank You!");   
    }//close setFinishOrder
    
    //getViewOrder
    public String getViewOrder() {
        return this.viewOrder.toString();
    }//close getViewOrder
    
    //addToViewOrder
    public void addToViewOrder(String order) {
        viewOrder.append(this.getTotalItems() + ". " + order);
        viewOrder.append(System.getProperty("line.separator"));
    }//close addToViewOrder
    
    //getItemInfo
    public String[] getItemInfo() {
        return itemDetails;
    }//close getItemInfo
    
    //setItemInfo array of strings
    public void setItemInfo(String itemID, String name, String price, String itemQuantity, String discountPercent, String totalDiscount){
        itemDetails[0] = itemID;
        itemDetails[1] = name;
        itemDetails[2] = price;
        itemDetails[3] = itemQuantity;
        itemDetails[4] = discountPercent;
        itemDetails[5] = totalDiscount;
    }//close setItemInfo
    
   //DISCOUNTS
    /* Discount is based on the quantity of an item. 
	1-4 = 0% 
	5-9 = 10% 
	10-14 = 15% 
	>=15 = 20% 
     */
    
    //getTotalDiscount | calculates discount
    public double getTotalDiscount(int quantity, double itemPrice){
        if (quantity >= 1 && quantity <= 4){
            return (quantity * itemPrice);
        }//no discount
        if (quantity >= 5 && quantity <= 9){
            return .10 * (quantity * itemPrice);
        }// 10% discount
        if (quantity >= 10 && quantity <= 14){
            return .15 * (quantity * itemPrice);
        }//15% discount
        if (quantity >= 15){
            return .20 * (quantity * itemPrice);
        }//20% discount
        
        return 0.0;
    }//close getTotalDiscount
    
    //getDiscountPercentage | displays discount 
    public int getDiscountPercentage(int quantity){
        if (quantity >= 1 && quantity <= 4){
            return 0;
        }
        if (quantity >= 5 && quantity <= 9){
            return 10;
        }
        if (quantity >= 10 && quantity <= 14){
            return 15;
        }
        if (quantity >= 15){
            return 20;
        }
        return 0;
    }//close getDiscountPercentage
    
    //viewOrder
    public String viewOrder() {
        return fileName;
    } //close viewOrder

    //prepareInvoice
    public void prepareInvoice() {
        String lineItem = new String();
        for(int i = 0; i < this.itemDetails.length; i++){
            lineItem += this.itemDetails[i] + ", ";
        }//close loop
        
        items.add(lineItem);
    }//close prepareInvoice
    
    //printInvoice
    public void printInvoice() throws IOException {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat permutation = new SimpleDateFormat("yyMMddyyHHmm");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a z");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

        // Sets finishOrder stringBuilder for Dialog Box
        this.setFinishOrder(dateFormat.format(date), time.format(date));

        // Checks if file does not exist and creates new one if false
        if(file.exists() == false){
            file.createNewFile();
        }//close conditional

        //otherwise append file
        PrintWriter outputStream = new PrintWriter(new FileWriter(fileName, true));

        //write file
        for(int i = 0; i < this.items.size(); i++){
            outputStream.append(permutation.format(date) + ", ");
            String lineItem = this.items.get(i);
            outputStream.append(lineItem);
            outputStream.append(dateFormat.format(date) + ", ");
            outputStream.append(time.format(date));
            outputStream.println();
        }//close for loop
        outputStream.flush();
        outputStream.close();
    }//close printInvoice
    
    public int getCurrentNumItems() {
        return currentNumItems;
    }
    public void setCurrentNumItems(int currentNumItems) {
        this.currentNumItems = this.currentNumItems + currentNumItems;
    }
    public double getOrderSubtotal() {
        return orderSubtotal;
    }
    public void setOrderSubtotal(int quantity, double itemPrice) {
        this.orderSubtotal = this.orderSubtotal + this.getTotalDiscount(quantity, itemPrice);
    }
    public int getTotalItems() {
        return totalItems;
    }
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
    public int getMaxNumItems() {
        return maxNumItems;
    }
    public void setMaxNumItems(int maxNumItems) {
        this.maxNumItems = maxNumItems;
    }
    public double getOrderTotal() {
        return orderTotal;
    }
    public void setOrderTotal() {
        this.orderTotal = this.orderSubtotal + (0.06 * this.orderSubtotal);
    }
    
}//close Order
