package vatican.task;

/**
 * Represents a Todo task.
 * A Todo is a basic task without any date or time constraints.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the specified description.
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the Todo task for file storage.
     * @return A string formatted as "T | status | description".
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }
}