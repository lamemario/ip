package vatican.command;

import vatican.data.Storage;
import vatican.ui.Ui;
import vatican.data.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFoundTasks(tasks.find(keyword));
    }
}