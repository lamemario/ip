import java.util.Scanner;

public class Vatican {
    public static void main(String[] args) {
        String logo = """
                 __      __   _   _                \s
                 \\ \\    / /  | | (_)               \s
                  \\ \\  / /_ _| |_ _  ___ __ _ _ __ \s
                   \\ \\/ / _` | __| |/ __/ _` | '_ \\\s
                    \\  / (_| | |_| | (_| (_| | | | |
                     \\/ \\__,_|\\__|_|\\___\\__,_|_| |_|
            """;

        // Ui components
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        ui.showWelcome(logo);

        boolean isExit = false;

        while(scanner.hasNextLine() && !isExit) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            try {
                switch (command) {
                    case "bye":
                        isExit = true;
                        break;

                    case "list":
                        ui.showTaskList(taskList);
                        break;

                    case "mark":
                        if (parts.length < 2)
                            throw new VaticanException("Who am I marking? You forgot the number, fam.");
                        try {
                            int markIndex = Integer.parseInt(parts[1]) - 1;
                            // >>> FIX: Check if index is too high!
                            if (markIndex < 0 || markIndex >= taskList.getSize()) {
                                throw new VaticanException("That number is a wasteyute. It doesn't exist, styll.");
                            }
                            taskList.markTask(markIndex);
                            ui.showMarked(taskList.getTask(markIndex));
                        } catch (NumberFormatException e) {
                            throw new VaticanException("That number is a wasteyute. It doesn't exist, styll.");
                        }
                        break;

                    case "unmark":
                        if (parts.length < 2)
                            throw new VaticanException("Who am I unmarking? Don't cheese me, give me a number.");
                        try {
                            int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                            // >>> FIX: Check if index is too high!
                            if (unmarkIndex < 0 || unmarkIndex >= taskList.getSize()) {
                                throw new VaticanException("That number is a wasteyute. It doesn't exist, styll.");
                            }
                            taskList.unmarkTask(unmarkIndex);
                            ui.showUnmarked(taskList.getTask(unmarkIndex));
                        } catch (NumberFormatException e) {
                            throw new VaticanException("That number is a wasteyute. It doesn't exist, styll.");
                        }
                        break;

                    case "todo":
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new VaticanException("you're moving loose. The description cannot be empty, styll.");
                        }
                        Task newTodo = new Todo(parts[1]);
                        taskList.addTask(newTodo);
                        ui.showAdded(newTodo, taskList.getSize());
                        break;

                    case "deadline":
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new VaticanException("you're moving loose. The description cannot be empty, styll.");
                        }
                        String[] dParts = parts[1].split(" /by ");
                        if (dParts.length < 2) {
                            throw new VaticanException("mans need a date! You forgot '/by', are you dumb?");
                        }
                        Task newDeadline = new Deadline(dParts[0], dParts[1]);
                        taskList.addTask(newDeadline);
                        ui.showAdded(newDeadline, taskList.getSize());
                        break;

                    case "event":
                        if (parts.length < 2 || parts[1].trim().isEmpty()) {
                            throw new VaticanException("you're moving loose. The description cannot be empty, styll.");
                        }
                        String[] eParts = parts[1].split(" /from ");
                        if (eParts.length < 2 || !eParts[1].contains(" /to ")) {
                            throw new VaticanException("you need a start AND end time (/from & /to).");
                        }
                        String[] timeParts = eParts[1].split(" /to ");
                        Task newEvent = new Event(eParts[0], timeParts[0], timeParts[1]);
                        taskList.addTask(newEvent);
                        ui.showAdded(newEvent, taskList.getSize());
                        break;

                    case "delete":
                        if (parts.length < 2 || parts[1].trim().isEmpty())
                            throw new VaticanException("you tryna delete what? You forgot the number, fam.");
                        try {
                            int deleteIndex = Integer.parseInt(parts[1]) - 1;

                            // Check if the number is valid
                            if (deleteIndex < 0 || deleteIndex >= taskList.getSize()) {
                                throw new VaticanException("That number is a wasteyute. It doesn't exist, styll.");
                            }

                            // Get the task before deleting it (so we can show it)
                            Task taskToDelete = taskList.getTask(deleteIndex);
                            taskList.deleteTask(deleteIndex);

                            ui.showDeleted(taskToDelete, taskList.getSize());
                        } catch (NumberFormatException e) {
                            throw new VaticanException("That number is a wasteyute. It doesn't exist, styll.");
                        }
                        break;

                    default:
                        throw new VaticanException("I don't know what that means. Nize that.");
                }
            } catch (VaticanException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
        scanner.close();
    }
}