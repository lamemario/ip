public class Ui {
    private static final String LINE = "____________________________________________________________";

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome(String logo) {
        showLine();
        System.out.println(logo);
        System.out.println(" More life. It's Vatican, dun know.");
        System.out.println(" So, what's the deal? I'm tryna see how I can bless you.");
        showLine();
    }

    public void showTaskList(TaskList tasks) {
        showLine();
        System.out.println(" Check the list out, styll:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.getTask(i));
        }
        showLine();
    }
}