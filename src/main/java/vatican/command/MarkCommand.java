package vatican.command;

import vatican.VaticanException;
import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.ui.Ui;

public class MarkCommand extends Command {
    private final int index;
    private final boolean isMark;

    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

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