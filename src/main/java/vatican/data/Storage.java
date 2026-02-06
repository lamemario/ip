package vatican.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import vatican.VaticanException;
import vatican.task.Deadline;
import vatican.task.Event;
import vatican.task.Task;
import vatican.task.Todo;

/**
 * Handles the loading and saving of task data to the hard disk.
 * Ensures data persistence across application restarts by writing to a specific file path.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a new Storage instance.
     *
     * @param filePath The file path where the data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current task list to the file specified in the constructor.
     * Creates the parent directories if they do not exist.
     *
     * @param taskList The TaskList containing tasks to save.
     */
    public void save(TaskList taskList) {
        try {
            // OS-independent way to handle paths and directories
            Path path = Paths.get(filePath); //
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent()); // Create ./vatican.data/ if missing
            }

            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                writer.write(taskList.getTask(i).toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(" Two twos my word fam, I couldn't save the blessings: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file. Returns an empty list if the file does not exist.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws VaticanException If the file exists but cannot be read or parsed due to IO errors.
     */
    public ArrayList<Task> load() throws VaticanException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return loadedTasks; // Return empty list for first-time run
        }

        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                if (!line.trim().isEmpty()) {
                    loadedTasks.add(parseTask(line));
                }
            }
        } catch (IOException e) {
            throw new VaticanException("oh my gad fam, i cant read them wasteyute vatican.task list, tsk.");
        }
        return loadedTasks;
    }

    // This logic parses the "T | 1 | description" format back into objects
    private Task parseTask(String line) throws VaticanException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        Task task;

        switch (type) {
        case "T":
            task = new Todo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            task = new Event(parts[2], parts[3], parts[4]);
            break;
        default:
            return null;
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
