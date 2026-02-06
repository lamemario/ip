package vatican.command;

import vatican.VaticanException;
import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.task.Task;
import vatican.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    /** The task to be added to the list */
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the list, displaying a message,
     * and saving the updated list to storage.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws VaticanException If an error occurs during execution or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VaticanException {
        tasks.addTask(task);
        ui.showAdded(task, tasks.getSize());
        storage.save(tasks);
    }
}
