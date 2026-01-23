public class TaskList {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void addTask(Task task) {
        tasks[taskCount++] = task;
    }

    public void markTask(int index) {
        tasks[index].markAsDone();
    }

    public void unmarkTask(int index) {
        tasks[index].unmarkAsDone();
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    public int getSize() {
        return taskCount;
    }
}