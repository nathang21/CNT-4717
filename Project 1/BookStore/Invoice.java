/* Name: Nathan Guenther
 Course: CNT 4714 – Fall 2015
 Assignment title: Program 1 – Event-driven Programming
 Date: Sunday September 13, 2015
*/
package assignment.pkg1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


// Classifies the Invoice Object which the total orders & Books a customer wants to buy
public class Invoice {
    
    // Initialize Variables (f for Final)
    private ArrayList<Order> fOrder;
    private int fUnitQuanity = 0;
    private float fSubTotal = 0;
    private float fTotal = 0;
    private String fTimeStamp;
    private String fTransaction;
    
    // Constuctor to Initalize ArrayList of Orders
    public Invoice(){
        this.fOrder = new ArrayList<Order>();
    }

    
    // Getters and Setters for an Invoice
    public ArrayList<Order> getfOrder() {
        return this.fOrder;
    }

    public int getfUnitQuanity() {
        return this.fUnitQuanity;
    }

    public void setfUnitQuanity(int fUnitQuanity) {
        this.fUnitQuanity = fUnitQuanity;
    }

    public float getfSubTotal() {
        return this.fSubTotal;
    }

    public void setfSubTotal(float fSubTotal) {
        this.fSubTotal = fSubTotal;
    }

    public float getfTotal() {
        return this.fTotal;
    }

    public void setfTotal(float fTotal) {
        this.fTotal = fTotal;
    }

    public String getfTimeStamp() {
        return this.fTimeStamp;
    }

    public String getfTransaction() {
        return this.fTransaction;
    }
    
    // Adds current order to Invoice
    public void addOrder(Order fOrder){
    	this.fOrder.add(fOrder);
    }
    
    // Generate Date on Request
    public void setDate(){
        // Create Date Variable
        Date fDate = new Date();
        
        // Specify Date Format
        DateFormat fDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a z");
        
        // Save TimeStamp
        this.fTimeStamp = fDateFormat.format(fDate);
        
        // Adjust Format for Transaction
        fDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        
        //Save Transaction
        this.fTransaction = fDateFormat.format(fDate);
    }
}
