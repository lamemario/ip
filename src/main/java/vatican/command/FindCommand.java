package vatican.command;

import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.ui.Ui;

/**
 * Represents a command to search for tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    /** The keyword used to filter the task list */
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The string to search for within task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching the task list for the keyword
     * and displaying the matching results to the user.
     *
     * @param tasks The list of tasks to be searched.
     * @param ui The user interface used to display the search results.
     * @param storage The storage handler (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFoundTasks(tasks.find(keyword));
    }
}
