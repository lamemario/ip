public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VaticanException {
        Task t = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.showDeleted(t, tasks.getSize());
        storage.save(tasks);
    }
}