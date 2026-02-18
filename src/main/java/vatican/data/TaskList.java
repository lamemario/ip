package vatican.data;

import java.util.ArrayList;

import vatican.task.Task;

/**
 * Represents the list of tasks.
 * Provides operations to add, delete, mark, unmark, find, and retrieve tasks.
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
     * @param loadedTasks The ArrayList of tasks loaded from storage.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        // ASSERTION: Cannot initialize with a null list
        assert loadedTasks != null : "Cannot initialize TaskList with null tasks";
        this.tasks = loadedTasks;
    }

    /**
     * Adds a task to the list.
     * @param task The task object to be added.
     */
    public void addTask(Task task) {
        // ASSERTION: Ensure we don't add null tasks
        assert task != null : "Cannot add a null task to the list";
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     * @param index The zero-based index of the task to remove.
     */
    public void deleteTask(int index) {
        // ASSERTION: Index must be within valid bounds
        assert index >= 0 && index < tasks.size() : "Delete index out of bounds";
        tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as done.
     * @param index The zero-based index of the task to mark.
     */
    public void markTask(int index) {
        // ASSERTION: Index must be within valid bounds
        assert index >= 0 && index < tasks.size() : "Mark index out of bounds";
        Task task = tasks.get(index);
        task.markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     * @param index The zero-based index of the task to unmark.
     */
    public void unmarkTask(int index) {
        // ASSERTION: Index must be within valid bounds
        assert index >= 0 && index < tasks.size() : "Unmark index out of bounds";
        Task task = tasks.get(index);
        task.markAsNotDone();
    }

    /**
     * Retrieves the task at the specified index.
     * @param index The zero-based index of the task.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        // ASSERTION: Index must be within valid bounds
        assert index >= 0 && index < tasks.size() : "Get task index out of bounds";
        return tasks.get(index);
    }

    /**
     * Finds tasks that contain the keyword in their description.
     * @param keyword The string to search for.
     * @return An ArrayList of matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        assert keyword != null : "Search keyword cannot be null";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getDescription().contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the total number of tasks in the list.
     * @return The number of tasks currently in the list.
     */
    public int getSize() {
        return tasks.size();
    }
}