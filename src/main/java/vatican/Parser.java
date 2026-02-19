package vatican;

import vatican.command.AddCommand;
import vatican.command.Command;
import vatican.command.DeleteCommand;
import vatican.command.ExitCommand;
import vatican.command.FindCommand;
import vatican.command.HelpCommand;
import vatican.command.ListCommand;
import vatican.command.MarkCommand;
import vatican.task.Deadline;
import vatican.task.Event;
import vatican.task.Todo;

/**
 * Parses user input into executable commands.
 * Identifies the command type and extracts necessary arguments.
 */
public class Parser {

    /**
     * Parses the full command string and returns the corresponding Command object.
     * @param fullCommand The full line of input entered by the user.
     * @return A specific Command object (e.g., AddCommand, DeleteCommand) ready for execution.
     * @throws VaticanException If the command is invalid or contains missing/incorrect parameters.
     */
    public static Command parse(String fullCommand) throws VaticanException {
        // ASSERTION: The UI should never pass a null string to the parser
        assert fullCommand != null : "Command string cannot be null";

        String[] parts = fullCommand.split(" ", 2);
        CommandType type = CommandType.fromString(parts[0]);

        switch (type) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
        case UNMARK:
            return new MarkCommand(parseIndex(parts), type == CommandType.MARK);
        case DELETE:
            return new DeleteCommand(parseIndex(parts));
        case FIND:
            return prepareFind(parts);
        case TODO:
            return prepareTodo(parts);
        case DEADLINE:
            return prepareDeadline(parts);
        case EVENT:
            return prepareEvent(parts);
        case HELP:
            return new HelpCommand();
        default:
            throw new VaticanException("I don't know what that means. Nize that.");
        }
    }

    private static Command prepareFind(String[] parts) throws VaticanException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new VaticanException("the description cannot be empty, styll.");
        }
        return new FindCommand(parts[1].trim());
    }

    private static Command prepareTodo(String[] parts) throws VaticanException {
        validateDescription(parts, "you're moving loose. The description cannot be empty, styll.");
        return new AddCommand(new Todo(parts[1]));
    }

    private static Command prepareDeadline(String[] parts) throws VaticanException {
        validateDescription(parts, "you're moving loose. The description cannot be empty, styll.");
        String[] dParts = parts[1].split(" /by ");
        if (dParts.length < 2) {
            throw new VaticanException("mans need a date! You forgot '/by', are you dumb?");
        }
        return new AddCommand(new Deadline(dParts[0], dParts[1]));
    }

    private static Command prepareEvent(String[] parts) throws VaticanException {
        validateDescription(parts, "you're moving loose. The description cannot be empty, styll.");
        String[] eParts = parts[1].split(" /from ");
        if (eParts.length < 2 || !eParts[1].contains(" /to ")) {
            throw new VaticanException("you need a start AND end time (/from & /to).");
        }
        String[] timeParts = eParts[1].split(" /to ");
        return new AddCommand(new Event(eParts[0], timeParts[0], timeParts[1]));
    }

    private static void validateDescription(String[] parts, String message) throws VaticanException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new VaticanException(message);
        }
    }

    private static int parseIndex(String[] parts) throws VaticanException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new VaticanException("you forgot the number.");
        }
        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new VaticanException("that number is a wasteyute. Give me a digit, styll.");
        }
    }
}
