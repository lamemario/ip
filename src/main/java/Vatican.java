import java.util.Scanner;

public class Vatican {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

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

    public static void main(String[] args) {
        new Vatican("data/vatican.txt").run();
    }
}