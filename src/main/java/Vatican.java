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

                default:
                    // Treat everything else as adding a new task
                    taskList.addTask(input);
                    ui.showAdded(input);
                    break;
            }
        }
        ui.showGoodbye();
        scanner.close();
    }
}