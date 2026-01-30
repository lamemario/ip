package vatican.ui;

import vatican.data.TaskList;
import vatican.task.Task;

/**
 * Handles interactions with the user.
 * Responsible for reading user input and printing messages to the console.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    /**
     * Prints a horizontal divider line to the console.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message and logo when the application starts.
     *
     * @param logo The ASCII art logo of the application.
     */
    public void showWelcome(String logo) {
        showLine();
        System.out.println(logo);
        System.out.println(" More life. It's vatican.Vatican, dun know.");
        System.out.println(" So, what's the deal? I'm tryna see how I can bless you.");
        showLine();
    }

    /**
     * Prints the entire list of tasks to the console.
     *
     * @param tasks The TaskList object containing the tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        showLine();
        System.out.println(" Check the list out, styll:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.getTask(i));
        }
        showLine();
    }

    /**
     * Prints a confirmation message when a task is marked as done.
     *
     * @param task The task that was marked.
     */
    public void showMarked(Task task) {
        showLine();
        System.out.println(" Big moves. I've marked this as done, styll:");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Prints a confirmation message when a task is marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarked(Task task) {
        showLine();
        System.out.println(" High key, I've marked this as not done yet:");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Prints a confirmation message when a new task is added.
     *
     * @param task      The task that was added.
     * @param totalSize The new total number of tasks in the list.
     */
    public void showAdded(Task task, int totalSize) {
        showLine();
        System.out.println(" Got it. I've added this blessing, styll:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalSize + " tasks in the list. Dun know.");
        showLine();
    }

    /**
     * Prints the exit message when the user closes the application.
     */
    public void showGoodbye() {
        showLine();
        System.out.println(" Wallahi, I'm out here. Say less.");
        showLine();
    }

    /**
     * Prints an error message to the console.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println(" Two twos my word fam, " + message);
        showLine();
    }

    /**
     * Prints a confirmation message when a task is deleted.
     *
     * @param task       The task that was removed.
     * @param totalTasks The remaining number of tasks in the list.
     */
    public void showDeleted(Task task, int totalTasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Say less. I've removed this blessing:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}