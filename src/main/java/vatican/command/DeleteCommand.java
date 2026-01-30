package vatican.command;

import vatican.VaticanException;
import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.task.Task;
import vatican.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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