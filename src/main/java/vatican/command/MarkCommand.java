package vatican.command;

import vatican.VaticanException;
import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.ui.Ui;

/**
 * Represents a command to mark or unmark a task in the task list.
 */
public class MarkCommand extends Command {
    /** The index of the task to be operated on */
    private final int index;

    /** Whether the task should be marked as done (true) or not done (false) */
    private final boolean isMark;

    /**
     * Constructs a MarkCommand with the specified task index and status.
     *
     * @param index The zero-based index of the task in the task list.
     * @param isMark True to mark the task as done, false to unmark it.
     */
    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    /**
     * Executes the command by updating the task's completion status,
     * displaying a confirmation message, and saving changes to storage.
     *
     * @param tasks The list of tasks containing the target task.
     * @param ui The user interface used to display the updated task status.
     * @param storage The storage handler for saving the updated task list.
     * @throws VaticanException If the provided index is out of bounds or saving fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VaticanException {
        // Guard against invalid indices
        if (index < 0 || index >= tasks.getSize()) {
            throw new VaticanException("That blessing doesn't exist, fam. Check the numbers, styll.");
        }

        if (isMark) {
            tasks.markTask(index);
            ui.showMarked(tasks.getTask(index));
        } else {
            tasks.unmarkTask(index);
            ui.showUnmarked(tasks.getTask(index));
        }
        storage.save(tasks);
    }
}
