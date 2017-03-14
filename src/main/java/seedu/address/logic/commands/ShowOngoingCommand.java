package seedu.address.logic.commands;


/**
 * Lists all persons in the address book to the user.
 */
public class ShowOngoingCommand extends Command {

    public static final String COMMAND_WORD = "showOngoing";

    public static final String MESSAGE_SUCCESS = "Listed all on-going tasks";


    @Override
    public CommandResult execute() {
        model.updateFilteredListToShowOngoing();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
