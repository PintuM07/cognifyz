package level_1;

import java.util.Scanner;

public class NumberPattern {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Pyramid Maker!");
        System.out.println("This program will create a number pyramid based on how many rows you choose.");
        
        System.out.print("Enter the number of rows you want: ");
        int rows = scanner.nextInt();
        
        System.out.println("Here is your pyramid:");
        
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < rows - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        
        System.out.println("Hope you liked your pyramid! Thanks for trying this out!");
        scanner.close();
    }
	
}
