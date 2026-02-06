package vatican.command;

import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying a goodbye message to the user.
     *
     * @param tasks The list of tasks (unused in this command).
     * @param ui The user interface used to show the goodbye message.
     * @param storage The storage handler (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns true to indicate that this command will terminate the application.
     *
     * @return True always.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
