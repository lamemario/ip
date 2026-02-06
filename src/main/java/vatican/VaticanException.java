package vatican;

/**
 * Represents a custom exception specific to the Vatican chatbot application.
 * Used to handle errors related to command parsing, task management, and storage.
 */
public class VaticanException extends Exception {
    /**
     * Constructs a VaticanException with the specified detail message.
     *
     * @param message The error message describing the cause of the exception.
     */
    public VaticanException(String message) {
        super(message);
    }
}
