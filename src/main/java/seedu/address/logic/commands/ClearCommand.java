package seedu.address.logic.commands;

import seedu.address.model.ToDoList;

/**
 * Clears doitdoit!!.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "doitdoit!! has been cleared!";


    @Override
    public CommandResult execute() {
        assert model != null;
        model.resetData(new ToDoList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
