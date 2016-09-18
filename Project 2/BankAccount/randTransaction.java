/* Name: Nathan Guenther
 Course: CNT 4714 – Fall 2015
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Date: Sunday September 20, 2015
*/
package program.pkg2;

import java.util.Random;

public class randTransaction {
    
    // Takes Boolean operator (true = deposit, false = withdrawl) and outputs random number
    public static int newNumber (boolean operator){
    
        //Random randomGenerator = new Random();
        int randomAmmount = 0;

        // If depositing money should be
        if (operator == true){
            randomAmmount = integerRange(1, 200); 
            return randomAmmount;

        // If withdrawling money
        } else {
            randomAmmount = integerRange(1, 50);
            return randomAmmount;
        }
    }
    
    // Makes sure int is within range.
    private static int integerRange(int min, int max) {
    
        Random randomGenerator = new Random();
        int i = 0;

        // Loops until generates Random Number within range
        while (min >= i || max <= i) {
            i = randomGenerator.nextInt();
        }

        return i;
    }
    
    // Generate random time for thread to sleep
    // Later decided to not use
    public static int newSleepTime(boolean operator) {
    
        Random randomGenerator = new Random();
        int sleepTime;
        
        // If depositing money should be
        if (operator == true){
            sleepTime = randomGenerator.nextInt(100);
            return sleepTime;

        // If withdrawling money
        } else {
            sleepTime = randomGenerator.nextInt(5);
            return sleepTime;
        }
    }
}
