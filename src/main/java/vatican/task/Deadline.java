package vatican.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import vatican.VaticanException;

public class Deadline extends Task {
    protected LocalDate by;

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
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileFormat() {
        // Ensure 'by' is treated as a String
        return "D | " + super.toFileFormat() + " | " + by.toString();
    }
}
