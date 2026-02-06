package vatican;

/**
 * Represents the various types of commands supported by the application.
 */
public enum CommandType {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UNKNOWN;

    /**
     * Returns the CommandType corresponding to the given string.
     * If the string does not match any valid command type, UNKNOWN is returned.
     *
     * @param str The input string representing a command.
     * @return The matching CommandType, or UNKNOWN if no match is found.
     */
    public static CommandType fromString(String str) {
        try {
            return CommandType.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
