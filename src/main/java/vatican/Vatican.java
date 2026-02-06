package vatican;

import java.util.Scanner;

import vatican.command.Command;
import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.ui.Ui;

/**
 * Represents the main entry point for the Vatican chatbot application.
 * Coordinates the UI, storage, and task list to handle user interactions.
 */
public class Vatican {
    /** The storage handler for reading and writing task data */
    private final Storage storage;

    /** The user interface for interacting with the user */
    private final Ui ui;

    /** The list of tasks currently managed by the application */
    private TaskList taskList;

    /**
     * Constructs a Vatican instance with the specified file path for storage.
     *
     * @param filePath The path to the file where tasks are saved.
     */
    public Vatican(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (VaticanException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Starts the application and runs the main Vatican chatbot.
     *
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new Vatican("data/vatican.txt").run();
    }

    /**
     * Runs the main program loop, processing user commands until an exit command is received.
     */
    public void run() {
        String logo = """
                     __      __   _   _                \s
                     \\ \\    / /  | | (_)               \s
                      \\ \\  / /_ _| |_ _  ___ __ _ _ __ \s
                       \\ \\/ / _` | __| |/ __/ _` | '_ \\\s
                        \\  / (_| | |_| | (_| (_| | | | |
                         \\/ \\__,_|\\__|_|\\___\\__,_|_| |_|
                """;

        Scanner scanner = new Scanner(System.in);
        ui.showWelcome(logo);
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = scanner.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (VaticanException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }
}
