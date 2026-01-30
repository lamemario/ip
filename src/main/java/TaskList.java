import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }
}