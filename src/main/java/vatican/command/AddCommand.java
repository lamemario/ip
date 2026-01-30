package vatican.command;

import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.task.Task;
import vatican.ui.Ui;
import vatican.VaticanException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VaticanException {
        tasks.addTask(task);
        ui.showAdded(task, tasks.getSize());
        storage.save(tasks);
    }
}