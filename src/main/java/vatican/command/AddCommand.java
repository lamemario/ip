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