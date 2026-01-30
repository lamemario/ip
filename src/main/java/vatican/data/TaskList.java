package vatican.data;

import java.util.ArrayList;

import vatican.task.Task;

/**
 * Represents the list of tasks.
 * Provides operations to add, delete, mark, unmark, and retrieve tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList initialized with a given list of tasks.
     *
     * @param loadedTasks The ArrayList of tasks loaded from storage.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index The zero-based index of the task to remove.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The zero-based index of the task to mark.
     */
    public void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The zero-based index of the task to unmark.
     */
    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The zero-based index of the task.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return The number of tasks currently in the list.
     */
    public int getSize() {
        return tasks.size();
    }
}