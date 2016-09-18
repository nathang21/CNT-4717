/* Name: Nathan Guenther
 Course: CNT 4714 – Fall 2015
 Assignment title: Program 1 – Event-driven Programming
 Date: Sunday September 13, 2015
*/
package assignment.pkg1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;


// Classifies the StoreLogic which lays out each function of the program. Single Object that runs until exit.
public class StoreLogic {
   
    // Initalize Variables (l for live)
    private static String text;
    private static char[] chars;
    private static Formatter output; 
    private boolean readFlag = false;
    private Invoice lInvoice;
    private static ArrayList<Book> lBookList;
    
     // Initalize Globals
    private static final String inputFile = "inventory.txt";
    private final String outputFile = "transaction.txt";
    private final float taxRate = 0.06f;
    private final String taxTotal = "6%";
    
    // Constructor to Prepare invoice and load the inventory
    public StoreLogic() {
        this.lInvoice = new Invoice();
        
        // Check to ensure file hasn't alread been read in
        if (readFlag == false) {
            
            // Parse file
            readFile();
            
            // Mark Status as Read
            readFlag = true;
        }
    }
    
    
   // Calculates the subtotal after discount
   private float calcDiscount(Book lBookList, int lQuantity){
        float lSubTotal = lBookList.getbPrice() * (float) lQuantity;
        
        if(lQuantity < 5){
            return lSubTotal;
        }else if(lQuantity < 10){
            return lSubTotal - (lSubTotal * 0.1f);
        }else if(lQuantity < 15){
            return lSubTotal - (lSubTotal * 0.15f);
        }else if(lQuantity >= 15){
            return lSubTotal - (lSubTotal * 0.2f);
        }else return 0f;
    }
    
    
   // Show the User the Discount based on # of idtems ordered.
   private int showDiscount(int lQuantity){
        if(lQuantity < 5)
            return 0;
        else if(lQuantity < 10)
            return 10;
        else if(lQuantity < 15)
            return 15;
        else if(lQuantity >= 15)
            return 20;
        else return 0;
    }
   
    
   // Cyles through Inventory to look up Book by ID
   public Book lookupBook(int lID){
       for(Book book:lBookList){
           if(book.getbID() == lID)
               return book;
       }
       return new Book();
   }
   
   
   // Retrieves and Displays Book info to User in GUI
   public void showBookInfo(Book lBook, int lQuantity){
       lBook.setbInfo(String.valueOf(lBook.getbID()) + " "+lBook.getbName()+" "+String.valueOf(lBook.getbPrice()+" "+String.valueOf(lQuantity) +" "+String.valueOf(showDiscount(lQuantity))+ "% "+String.valueOf(calcDiscount(lBook, lQuantity))));
   }
   
   
   // Calculate and Display Subtotal to User in GUI
   public float calcSubTotal(Book lBook, int lQuantity){
       return lInvoice.getfSubTotal() + calcDiscount(lBook, lQuantity);
   }
   
   
   // Shows users details on all processed orders
   public String reviewOrder(){
       String lOrderList = "";
       int i = 1;
       for(Order lOrder: lInvoice.getfOrder()){
           lOrderList = lOrderList + String.valueOf(i)+". "+lOrder.getuBook().getbInfo()+"\n";
           i++;
       }
       return lOrderList;
   }
           
   
   // Takes current order and adds it to the Invoice
   public void processOrder(Book lBookList, int lQuantity, int lUnitQuanity){
       Order lOrder = new Order();
       lOrder.setuBook(lBookList);
       lOrder.setuQuantity(lQuantity);
       lOrder.setuDiscount((float) showDiscount(lQuantity));
       lOrder.setuSubTotal(calcDiscount(lBookList, lQuantity));
       
       this.lInvoice.addOrder(lOrder);
       this.lInvoice.setfSubTotal(calcDiscount(lBookList, lQuantity));
       this.lInvoice.setfTotal(this.lInvoice.getfSubTotal() + (this.lInvoice.getfSubTotal() * taxRate));
       this.lInvoice.setfUnitQuanity(lUnitQuanity);
       
   }
    
   // Comples all orders and prepares Invoice
   public void prepInvoice(){
       
        // Initalize Output Methods
        FileWriter validOutputCheck = null;
        BufferedWriter fileOutput = null;
       
        // Check directory for file output issues (already exists, can't write, etc.).
        try {
            validOutputCheck = new FileWriter(outputFile,true);
        } catch (IOException e) {
            System.out.println("The file could not be opened for writing.");
            JOptionPane errorBox = new JOptionPane("The file could not be opened for writing.", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
       
        fileOutput = new BufferedWriter(validOutputCheck);
       
        // Loop through orders to Calculate Invoice
        for(Order lOrder: this.lInvoice.getfOrder()){
           
            // Retrieve and write Book Info
            try {
                fileOutput.write(this.lInvoice.getfTransaction()+", "+lOrder.getuBook().getbID()+", "+lOrder.getuBook().getbName()+", "+lOrder.getuBook().getbPrice()+", "+lOrder.getuQuantity()+", "+lOrder.getuDiscount()+", "+lOrder.getuSubTotal()+", "+this.lInvoice.getfTimeStamp());
                fileOutput.newLine();
            }catch (IOException e) {
                System.out.println("Could not write to file.");
                JOptionPane errorBox = new JOptionPane("Could not write to file.", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
           }
       }
        
        // Clean Up Files
        try {
            fileOutput.close();
            validOutputCheck.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
   
   // Display Invoice to user in GUI
   public String showInvoice() {
       this.lInvoice.setDate();
       String dialog = "Date: ";
       
       dialog = dialog + this.lInvoice.getfTimeStamp() + "\n\n";
       dialog = dialog + "Number of line items: " + this.lInvoice.getfUnitQuanity() + "\n\n";
       dialog = dialog + "Item# / ID / Title / Price / Quantity / Discount % / Subtotal: \n\n";
       dialog = dialog + reviewOrder() + "\n\n";
       dialog = dialog + "Order SubTotal: " + this.lInvoice.getfSubTotal() + "\n\n";
       dialog = dialog + "Tax Rate:      " + taxTotal + "\n\n";
       dialog = dialog + "Tax Ammount:      $" + (this.lInvoice.getfSubTotal() * taxRate) + "\n\n";
       dialog = dialog + "Order Total:      " + this.lInvoice.getfTotal() + "\n\n";
       dialog = dialog + "Thank you for shopping at the Book Store! \n\n";
       
       return dialog;
   }
   
    // Rred file into the Book Object ArrayList
   public static void readFile()
    {
        // Initalize Input Methods
        FileReader validInputCheck = null;
        BufferedReader scannedInput = null;
        lBookList = new ArrayList<>();
        
        // Check for file existance, and character encoding
        try {
            validInputCheck = new FileReader(inputFile);
            
        } catch (FileNotFoundException e) {
            JOptionPane errorBox = new JOptionPane("Could not open file, it may not exist or can't be accessed!", JOptionPane.ERROR_MESSAGE);
            errorBox.setVisible(true);
            e.printStackTrace();
        }
        
        
        // Streams data to characters for easy input into ArrayList
        try {
            scannedInput = new BufferedReader(validInputCheck);
            
            // Temporary variable for reading each line
            String tempLine;
            
            // Loop to parse the entire text file
            while ((tempLine = scannedInput.readLine()) != null) {
                // Temporary Variable referencing current book being processed
                String[] breakUpInput = new String[3];
                // Book Oject 
                Book currentBook = new Book();
                
                breakUpInput = tempLine.split(",", 3);
                currentBook.setbID(Integer.valueOf(breakUpInput[0]));
                currentBook.setbName(breakUpInput[1]);
                currentBook.setbPrice(Float.valueOf(breakUpInput[2]));
                
                lBookList.add(currentBook);
                }
            
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
   
   // Close leftover file IO before termination
   public static void closeFile()
    {
       // Cleanup file if needed.
       if (output != null)
           output.close();
   }
    
}

