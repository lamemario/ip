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

    public void showMarked(Task task) {
        showLine();
        System.out.println(" Big moves. I've marked this as done, styll:");
        System.out.println("   " + task);
        showLine();
    }

    public void showUnmarked(Task task) {
        showLine();
        System.out.println(" High key, I've marked this as not done yet:");
        System.out.println("   " + task);
        showLine();
    }

    public void showAdded(Task task, int totalSize) {
        showLine();
        System.out.println(" Got it. I've added this blessing, styll:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalSize + " tasks in the list.");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println(" Wallahi, I'm out here. Say less.");
        showLine();
    }
}