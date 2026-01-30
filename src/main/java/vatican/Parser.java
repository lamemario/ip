package vatican;

import vatican.command.Command;
import vatican.command.AddCommand;
import vatican.command.DeleteCommand;
import vatican.command.ExitCommand;
import vatican.command.ListCommand;
import vatican.command.MarkCommand;

import vatican.task.Todo;
import vatican.task.Deadline;
import vatican.task.Event;


public class Parser {
    public static Command parse(String fullCommand) throws VaticanException {
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
        case TODO:
            validateDescription(parts, "you're moving loose. The description cannot be empty, styll.");
            return new AddCommand(new Todo(parts[1]));
        case DEADLINE:
            validateDescription(parts, "you're moving loose. The description cannot be empty, styll.");
            String[] dParts = parts[1].split(" /by ");
            if (dParts.length < 2) throw new VaticanException("mans need a date! You forgot '/by', are you dumb?");
            return new AddCommand(new Deadline(dParts[0], dParts[1]));
        case EVENT:
            validateDescription(parts, "you're moving loose. The description cannot be empty, styll.");
            String[] eParts = parts[1].split(" /from ");
            if (eParts.length < 2 || !eParts[1].contains(" /to ")) {
                throw new VaticanException("you need a start AND end time (/from & /to).");
            }
            String[] timeParts = eParts[1].split(" /to ");
            return new AddCommand(new Event(eParts[0], timeParts[0], timeParts[1]));
        default:
            throw new VaticanException("I don't know what that means. Nize that.");
        }
    }

    private static void validateDescription(String[] parts, String message) throws VaticanException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new VaticanException(message);
        }
    }

    private static int parseIndex(String[] parts) throws VaticanException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new VaticanException("You forgot the number, fam.");
        }
        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new VaticanException("That number is a wasteyute. Give me a digit, styll.");
        }
    }
}