//@@author A0115333U
package seedu.address.logic.commands;

/**
 * Lists tasks of specified types in ToDoList to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed %1$s tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List tasks by types. "
            + "Parameters: all/ongoing/completed\n" + "Example: list all";
    private final String listType;

    public ListCommand(String listType) {
        this.listType = listType;
    }

    @Override
    public CommandResult execute() {
        assert model != null;
        switch (listType.trim()) {
        case "all":
            model.updateFilteredListToShowAll();
            break;

        case "ongoing":
            model.updateFilteredListToShowOngoing();
            break;

        case "completed":
            model.updateFilteredListToShowCompleted();
            break;
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, listType));
    }
}
