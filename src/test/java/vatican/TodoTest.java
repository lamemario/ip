package vatican;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import vatican.task.Todo;

public class TodoTest {
    @Test
    public void createTodo_validDescription_success() {
        Todo todo = new Todo("read scripture");
        assertEquals("[T][ ] read scripture", todo.toString());
    }
}