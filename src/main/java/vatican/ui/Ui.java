package vatican.ui;

import java.util.ArrayList;

import vatican.data.TaskList;
import vatican.task.Task;

/**
 * Handles interactions with the user.
 * Responsible for reading user input and printing messages to the console.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    /**
     * Prints a varargs list of messages to the console.
     * This eliminates the need for repetitive System.out.println calls.
     *
     * @param messages The strings to be printed, one per line.
     */
    public void showToUser(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }

    /**
     * Prints a horizontal divider line to the console.
     */
    public void showLine() {
    }

    /**
     * Displays the welcome message and logo when the application starts.
     * @param logo The ASCII art logo of the application.
     */
    public void showWelcome(String logo) {
        showLine();
        showToUser(
                logo,
                " More life. It's vatican.Vatican, dun know.",
                " So, what's the deal? I'm tryna see how I can bless you."
        );
        showLine();
    }

    /**
     * Prints the entire list of tasks to the console.
     * @param tasks The TaskList object containing the tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        showLine();
        showToUser(" Check the list out, styll:");
        for (int i = 0; i < tasks.getSize(); i++) {
            showToUser(" " + (i + 1) + "." + tasks.getTask(i));
        }
        showLine();
    }

    /**
     * Prints the list of tasks found by a search keyword.
     * @param foundTasks The ArrayList of tasks matching the search.
     */
    public void showFoundTasks(ArrayList<Task> foundTasks) {
        showLine();
        showToUser(" Here are the matching tasks in your list, styll:");
        for (int i = 0; i < foundTasks.size(); i++) {
            showToUser(" " + (i + 1) + "." + foundTasks.get(i));
        }
        showLine();
    }

    /**
     * Prints a confirmation message when a task is marked as done.
     * @param task The task that was marked.
     */
    public void showMarked(Task task) {
        showLine();
        showToUser(
                " Big moves. I've marked this as done, styll:",
                "   " + task
        );
        showLine();
    }

    /**
     * Prints a confirmation message when a task is marked as not done.
     * @param task The task that was unmarked.
     */
    public void showUnmarked(Task task) {
        showLine();
        showToUser(
                " High key, I've marked this as not done yet:",
                "   " + task
        );
        showLine();
    }

    /**
     * Prints a confirmation message when a new task is added.
     * @param task The task that was added.
     * @param totalSize The new total number of tasks in the list.
     */
    public void showAdded(Task task, int totalSize) {
        showLine();
        showToUser(
                " Got it. I've added this blessing, styll:",
                "   " + task,
                " Now you have " + totalSize + " tasks in the list. Dun know."
        );
        showLine();
    }

    /**
     * Prints the exit message when the user closes the application.
     */
    public void showGoodbye() {
        showLine();
        showToUser(" Wallahi, I'm out here. Say less.");
        showLine();
    }

    /**
     * Prints an error message to the console.
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        showToUser("Two twos my word fam, " + message);
        showLine();
    }

    /**
     * Prints a confirmation message when a task is deleted.
     * @param task The task that was removed.
     * @param totalTasks The remaining number of tasks in the list.
     */
    public void showDeleted(Task task, int totalTasks) {
        showToUser(
                " Say less. I've removed this blessing:",
                "   " + task,
                " Now you have " + totalTasks + " tasks in the list."
        );
    }
}
