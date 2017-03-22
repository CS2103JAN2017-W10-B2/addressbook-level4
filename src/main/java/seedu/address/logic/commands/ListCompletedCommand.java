//@@author A0115333U
package seedu.address.logic.commands;


/**
 * Lists completed tasks in doitdoit!! to the user.
 */
public class ListCompletedCommand extends Command {

    public static final String COMMAND_WORD = "list_completed";

    public static final String MESSAGE_SUCCESS = "Listed all completed tasks";


    @Override
    public CommandResult execute() {
        model.updateFilteredListToShowCompleted();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
