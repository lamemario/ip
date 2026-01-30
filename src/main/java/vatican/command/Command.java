package vatican.command;

import vatican.VaticanException;
import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws VaticanException;

    public boolean isExit() {
        return false;
    }
}