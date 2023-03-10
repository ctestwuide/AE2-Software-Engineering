package AE2;

import java.util.Scanner;

/*
Since a scanner is needed to accept user input in multiple classes, we created a Singleton class.
This ensures that there is only one instance of the Scanner object, and that 
it can be easily accessed from any other class in the program.
 */



public class ScannerSingleton {
    private static ScannerSingleton instance;
    private Scanner scanner;
    
    private ScannerSingleton() {
        scanner = new Scanner(System.in);
    }
    
    public static ScannerSingleton getInstance() {
        if (instance == null) {
            instance = new ScannerSingleton();
        }
        return instance;
    }
    
    public Scanner getScanner() {
        return scanner;
    }
}

