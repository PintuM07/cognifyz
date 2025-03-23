package Level_2.Question_2;
import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTemperature Converter:");
            System.out.println("1. Convert Celsius to Fahrenheit");
            System.out.println("2. Convert Fahrenheit to Celsius");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 3) {
                System.out.println("Exiting Temperature Converter.");
                break;
            }

            System.out.print("Enter the temperature: ");
            double temp = scanner.nextDouble();
            double convertedTemp;

            if (choice == 1) {
                convertedTemp = (temp * 9 / 5) + 32;
                System.out.println(temp + "°C = " + convertedTemp + "°F");
            } else if (choice == 2) {
                convertedTemp = (temp - 32) * 5 / 9;
                System.out.println(temp + "°F = " + convertedTemp + "°C");
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
