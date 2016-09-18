/* Name: Nathan Guenther
 Course: CNT 4714 – Fall 2015
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Date: Sunday September 20, 2015
*/
package program.pkg2;

// Running loop for Deposit Thread
public class depositThread implements Runnable {
    private Account account;

    // Constructor to link thread to main shared account
    public depositThread (Account mainAccount) {
        account = mainAccount;
    }

    public void run() {
        try {
            // Infinate Loop
            while (true) {
                account.deposit();
                Thread.sleep(7500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}