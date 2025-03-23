package level_3_question_1;

public class Task {
    private final int id;
    private String title;
    private String description;

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return id + "," + title + "," + description;
    }

    public static Task fromString(String taskStr) {
        String[] parts = taskStr.split(",", 3);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format in file.");
        }
        return new Task(Integer.parseInt(parts[0].trim()), parts[1].trim(), parts[2].trim());
    }
}
