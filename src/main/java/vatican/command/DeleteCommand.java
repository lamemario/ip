package vatican.command;

import vatican.VaticanException;
import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.task.Task;
import vatican.ui.Ui;

/**
 * Represents a command to delete a specific task from the task list.
 */
public class DeleteCommand extends Command {
    /** The index of the task to be deleted */
    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The zero-based index of the task to be removed.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by removing the task at the specified index,
     * displaying a confirmation message, and updating the storage.
     *
     * @param tasks The list of tasks to be modified.
     * @param ui The user interface for displaying the result of the deletion.
     * @param storage The storage handler for saving the updated task list.
     * @throws VaticanException If the index is invalid or an error occurs during saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VaticanException {
        // Guard against invalid indices just like MarkCommand
        if (index < 0 || index >= tasks.getSize()) {
            throw new VaticanException("Can't delete what isn't there, styll. Check the numbers.");
        }

        Task removedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.showDeleted(removedTask, tasks.getSize());
        storage.save(tasks);
    }
}
