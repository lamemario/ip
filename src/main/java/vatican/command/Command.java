package vatican.command;

import vatican.VaticanException;
import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.ui.Ui;

/**
 * Represents an executable command within the application.
 * This is an abstract class that serves as a base for all specific command types.
 */
public abstract class Command {
    /**
     * Executes the specific command logic using the provided task list, UI, and storage.
     *
     * @param tasks The list of tasks to be operated on.
     * @param ui The user interface for displaying feedback.
     * @param storage The storage handler for reading or writing data.
     * @throws VaticanException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws VaticanException;

    /**
     * Returns true if this command is an exit command.
     * By default, commands return false unless overridden.
     *
     * @return True if the application should terminate, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
