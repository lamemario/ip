package vatican;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import vatican.command.Command;

public class ParserTest {
    @Test
    public void parse_todo_correctCommandType() throws VaticanException {
        Command c = Parser.parse("todo read scripture");
        assertTrue(c instanceof vatican.command.AddCommand);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        assertThrows(VaticanException.class, () -> {
            Parser.parse("waste command");
        });
    }
}