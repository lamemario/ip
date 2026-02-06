package vatican.command;

import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.ui.Ui;

/**
 * Represents a command to display all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by requesting the UI to display the current tasks.
     *
     * @param tasks The list of tasks to be displayed.
     * @param ui The user interface used to show the task list.
     * @param storage The storage handler (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    /**
     * Returns false to indicate that the application should continue running after this command.
     *
     * @return False always.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
