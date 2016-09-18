/* Name: Nathan Guenther
 Course: CNT 4714 – Fall 2015
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Date: Sunday September 20, 2015
*/
package program.pkg2;

public class ThreadRunner {
    public static void main(String[] args) {
        
        // Create Shared Bank Account
        Account account = new Account();

        // Prepare Base Output
        System.out.printf("Deposit Threads\t\t\tWithdrawl Threads\t\tBalence\t\t\t\n");
        System.out.printf("---------------\t\t\t-----------------\t\t---------------\t\t\t\n");  

        // Setup Type of Threads
        depositThread d = new depositThread(account);
        withdrawlThread w = new withdrawlThread(account);        

        // Initalize threads
        Thread d1 = new Thread(d, "1");
        Thread d2 = new Thread(d, "2");
        Thread d3 = new Thread(d, "3");

        Thread w1 = new Thread(w, "4");
        Thread w2 = new Thread(w, "5");
        Thread w3 = new Thread(w, "6");
        Thread w4 = new Thread(w, "7");
        Thread w5 = new Thread(w, "8");

        // Start threads
        d1.start();
        w1.start();
        d2.start();
        w2.start();
        w3.start();
        w4.start();
        w5.start();
        d3.start();
        }
}