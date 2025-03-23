package level_3_question_1;

import java.io.*;
import java.util.*;

public class TaskManagerFileIO {
    private static final List<Task> taskList = new ArrayList<>();
    private static final String FILE_NAME = "tasks.txt";
    private static int idCounter = 1;

    public static void main(String[] args) {
        loadTasksFromFile();
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
                    saveTasksToFile();
                    System.out.println("Exiting Task Manager. Thank you!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== Task Manager Menu =====");
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

        Task task = new Task(idCounter++, title, description);
        taskList.add(task);
        saveTasksToFile();
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
                saveTasksToFile();
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
                saveTasksToFile();
                System.out.println("Task deleted successfully!");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    private static void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : taskList) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static void loadTasksFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = Task.fromString(line);
                    taskList.add(task);
                    idCounter = Math.max(idCounter, task.getId() + 1);
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid task entry in file.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
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
