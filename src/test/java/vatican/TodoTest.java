package vatican;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import vatican.task.Todo;

public class TodoTest {
    @Test
    public void createTodo_validDescription_success() {
        Todo todo = new Todo("read scripture");
        assertEquals("[T][ ] read scripture", todo.toString());
    }
}