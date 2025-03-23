package level_2_question_1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TaskManager {
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static int idCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = getValidIntegerInput(scanner, "Choose an option: ");

            switch (choice) {
                case 1 -> addTask(scanner);
                case 2 -> viewTasks();
                case 3 -> updateTask(scanner);
                case 4 -> deleteTask(scanner);
                case 5 -> {
                    System.out.println("Exiting Task Manager. Thank you!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== Task Manager =====");
        System.out.println("1. Add Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. Update Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter Task Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine().trim();

        if (title.isEmpty() || description.isEmpty()) {
            System.out.println("Task title and description cannot be empty.");
            return;
        }

        taskList.add(new Task(idCounter++, title, description));
        System.out.println("Task added successfully!");
    }

    private static void viewTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\nTask List:");
            for (Task task : taskList) {
                System.out.println(task);
            }
        }
    }

    private static void updateTask(Scanner scanner) {
        int id = getValidIntegerInput(scanner, "Enter Task ID to update: ");

        for (Task task : taskList) {
            if (task.getId() == id) {
                System.out.print("Enter new Task Title: ");
                String title = scanner.nextLine().trim();
                System.out.print("Enter new Task Description: ");
                String description = scanner.nextLine().trim();

                if (title.isEmpty() || description.isEmpty()) {
                    System.out.println("Task title and description cannot be empty.");
                    return;
                }

                task.setTitle(title);
                task.setDescription(description);
                System.out.println("Task updated successfully!");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    private static void deleteTask(Scanner scanner) {
        int id = getValidIntegerInput(scanner, "Enter Task ID to delete: ");

        Iterator<Task> iterator = taskList.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == id) {
                iterator.remove();
                System.out.println("Task deleted successfully!");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    private static int getValidIntegerInput(Scanner scanner, String prompt) {
        int input;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                return input;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
