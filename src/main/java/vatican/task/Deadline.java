package vatican.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import vatican.VaticanException;

/**
 * Represents a Deadline task.
 * A Deadline is a task that needs to be completed before a specific date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a new Deadline task.
     * Parses the date string into a LocalDate object.
     *
     * @param description The description of the task.
     * @param by          The deadline date in "yyyy-mm-dd" format.
     * @throws VaticanException If the date format is invalid.
     */
    public Deadline(String description, String by) throws VaticanException {
        super(description);
        try {
            this.by = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new VaticanException("Format your dates as yyyy-mm-dd (e.g., 2026-10-15), styll.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Formats the Deadline task for file storage.
     *
     * @return A string formatted as "D | status | description | by".
     */
    @Override
    public String toFileFormat() {
        // Ensure 'by' is treated as a String
        return "D | " + super.toFileFormat() + " | " + by.toString();
    }
}
