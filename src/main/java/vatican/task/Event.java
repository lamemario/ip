package vatican.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import vatican.VaticanException;

/**
 * Represents an Event task.
 * An Event is a task that occurs within a specific date range.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates a new Event task.
     * Parses the start and end date strings into LocalDate objects.
     * @param description The description of the event.
     * @param from The start date in "yyyy-mm-dd" format.
     * @param to The end date in "yyyy-mm-dd" format.
     * @throws VaticanException If the date format is invalid.
     */
    public Event(String description, String from, String to) throws VaticanException {
        super(description);
        try {
            this.from = LocalDate.parse(from.trim());
            this.to = LocalDate.parse(to.trim());
        } catch (DateTimeParseException e) {
            throw new VaticanException("Use yyyy-mm-dd for the timing, dun know.");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(fmt) +
                " to: " + to.format(fmt) + ")";
    }

    /**
     * Formats the Event task for file storage.
     * @return A string formatted as "E | status | description | from | to".
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | "
                + from + " | " + to;
    }
}