/* Name: Nathan Guenther
 Course: CNT 4714 – Fall 2015
 Assignment title: Program 1 – Event-driven Programming
 Date: Sunday September 13, 2015
*/
package assignment.pkg1;

// Classifies the Book object
public class Book {
    
    // Initialize Variables (b for book)
    private String bInfo;
    private float bPrice;
    private int bID;
    private String bName;
    
    // Book Constructor
    public Book(){
        setbInfo("That ID doesn't corespond to any known book");
    }
    
       // Setters & Getters for a Book
    public String getbInfo() {
        return bInfo;
    }

    public void setbInfo(String bInfo) {
        this.bInfo = bInfo;
    }

    public float getbPrice() {
        return bPrice;
    }

    public void setbPrice(float bPrice) {
        this.bPrice = bPrice;
    }

    public int getbID() {
        return bID;
    }

    public void setbID(int bID) {
        this.bID = bID;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }
    
}