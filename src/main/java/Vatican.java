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

        String horizontalLine = "____________________________________________________________";

        // Ui components
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        ui.showWelcome(logo);

        boolean isExit = false;

        while(!isExit) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            switch (command) {
                case "bye":
                    isExit = true;
                    break;

                case "list":
                    ui.showTaskList(taskList);
                    break;

                case "mark":
                    int markIndex = Integer.parseInt(parts[1]) - 1;
                    taskList.markTask(markIndex);
                    ui.showMarked(taskList.getTask(markIndex));
                    break;

                case "unmark":
                    int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                    taskList.unmarkTask(unmarkIndex);
                    ui.showUnmarked(taskList.getTask(unmarkIndex));
                    break;

                case "todo":
                    Task newTodo = new Todo(parts[1]);
                    taskList.addTask(newTodo);
                    ui.showAdded(newTodo, taskList.getSize());
                    break;

                case "deadline":
                    String[] dParts = parts[1].split(" /by ");
                    Task newDeadline = new Deadline(dParts[0], dParts[1]);
                    taskList.addTask(newDeadline);
                    ui.showAdded(newDeadline, taskList.getSize());
                    break;

                case "event":
                    String[] eParts = parts[1].split(" /from ");
                    String[] timeParts = eParts[1].split(" /to ");
                    Task newEvent = new Event(eParts[0], timeParts[0], timeParts[1]);
                    taskList.addTask(newEvent);
                    ui.showAdded(newEvent, taskList.getSize());
                    break;

                default:
                    Task genericTask = new Todo(input);
                    taskList.addTask(genericTask);
                    ui.showAdded(genericTask, taskList.getSize());
                    break;
            }
        }
        ui.showGoodbye();
        scanner.close();
    }
}