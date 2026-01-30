public class MarkCommand extends Command {
    private int index;
    private boolean isMark;

    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VaticanException {
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