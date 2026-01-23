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
        Scanner scanner = new Scanner(System.in);

        System.out.println(horizontalLine);
        System.out.println(logo);
        System.out.println(" More life. It's Vatican, dun know.");
        System.out.println(" So, what's the deal? I'm tryna see how I can bless you.");
        System.out.println(horizontalLine);

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            System.out.println(horizontalLine);
            System.out.println(" " + input);
            System.out.println(horizontalLine);
        }
    }

}