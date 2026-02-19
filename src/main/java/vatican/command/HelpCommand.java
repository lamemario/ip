package vatican.command;

import vatican.data.Storage;
import vatican.data.TaskList;
import vatican.ui.Ui;

/**
 * Provides guidance on how to use the Vatican bot.
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String helpMessage = "Here's how you use Vatican, dun know:\n\n"
                + "1. todo [desc] - Add a simple mission.\n"
                + "2. deadline [desc] /by [date] - Add a mission with a cutoff.\n"
                + "3. event [desc] /from [time] /to [time] - Add a timed link-up.\n"
                + "4. list - See all your current blessings.\n"
                + "5. mark [index] - Check off a task (Green style).\n"
                + "6. unmark [index] - Re-open a task.\n"
                + "7. delete [index] - Clear a task, styll (Orange style).\n"
                + "8. find [keyword] - Search for specific business.\n"
                + "9. help - See this menu again.\n"
                + "10. bye - Wallahi, I'll be outta there crodie.";

        ui.showToUser(helpMessage);
    }
}
