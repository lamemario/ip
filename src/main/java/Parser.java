public class Parser {
    public static Command parse(String fullCommand) throws VaticanException {
        String[] parts = fullCommand.split(" ", 2);
        CommandType type = CommandType.fromString(parts[0]);

        switch (type) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        default:
            throw new VaticanException("I haven't learned that move in the new system yet, styll.");
        }
    }
}