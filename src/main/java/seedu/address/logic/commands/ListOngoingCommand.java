//@@author A0115333U
package seedu.address.logic.commands;


/**
 * Lists on-going tasks in doitdoit!! to the user.
 */
public class ListOngoingCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all on-going tasks";


    @Override
    public CommandResult execute() {
        model.updateFilteredListToShowOngoing();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
