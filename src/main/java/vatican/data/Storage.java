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
    private final String filePath;

    /**
     * Creates a new Storage instance.
     *
     * @param filePath The file path where the data is stored.
     */
    public Storage(String filePath) {
        // ASSERTION: Ensure file path is valid
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Saves the current task list to the file specified in the constructor.
     * Creates the parent directories if they do not exist.
     *
     * @param taskList The TaskList containing tasks to save.
     * @throws VaticanException If the file cannot be written to.
     */
    public void save(TaskList taskList) throws VaticanException {
        // ASSERTION: Never try to save a null list
        assert taskList != null : "Cannot save a null TaskList";

        try {
            Path path = Paths.get(filePath);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                writer.write(taskList.getTask(i).toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            // Throw exception so UI handles the error message, keeping Storage logic clean
            throw new VaticanException("Two twos my word fam, I couldn't save the blessings: " + e.getMessage());
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
                    try {
                        Task t = parseTask(line);
                        if (t != null) {
                            loadedTasks.add(t);
                        }
                    } catch (Exception e) {
                        // Corrupted line? Skip it instead of crashing the whole app
                        System.out.println("Warning: Skipping corrupted line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            throw new VaticanException("oh my gad fam, i cant read them wasteyute vatican.task list, tsk.");
        }
        return loadedTasks;
    }

    // This logic parses the "T | 1 | description" format back into objects
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null; // Basic validation

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        Task task;

        switch (type) {
        case "T":
            task = new Todo(parts[2]);
            break;
        case "D":
            if (parts.length < 4) return null;
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            if (parts.length < 5) return null;
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