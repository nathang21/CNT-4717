/* Name: Nathan Guenther
 Course: CNT 4714 – Fall 2015
 Assignment title: Program 1 – Event-driven Programming
 Date: Sunday September 13, 2015
*/
package assignment.pkg1;

// Clasifies the Order class which holds information about a cusotmers individual order
public class Order {
    
    // Initlaize Variables (u for User)
    private Book  uBook;
    private int uQuantity;
    private float uDiscount;
    private float uSubTotal;

    
    // Setters and Getters for an Order
    public Book getuBook() {
        return this.uBook;
    }

    public void setuBook(Book uBook) {
        this.uBook = uBook;
    }

    public int getuQuantity() {
        return this.uQuantity;
    }

    public void setuQuantity(int uQuantity) {
        this.uQuantity = uQuantity;
    }

    public float getuDiscount() {
        return this.uDiscount;
    }

    public void setuDiscount(float uDiscount) {
        this.uDiscount = uDiscount;
    }

    public float getuSubTotal() {
        return this.uSubTotal;
    }

    public void setuSubTotal(float uSubTotal) {
        this.uSubTotal = uSubTotal;
    }
    
}

