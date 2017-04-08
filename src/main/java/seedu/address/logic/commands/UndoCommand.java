//@@author A0138831A
package seedu.address.logic.commands;

import java.util.EmptyStackException;

import seedu.address.logic.commands.exceptions.CommandException;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": undo a previous command to doitdoit!!. ";
    public static final String MESSAGE_SUCCESS = "Previous command undone: %1$s";
    public static final String MESSAGE_EMPTY_STACK = "There is no more task to undo in doitdoit!!";

    @Override
    public CommandResult execute() throws CommandException {
        assert model != null;
        try {
            model.undoTask();
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (EmptyStackException e) {
            throw new CommandException(MESSAGE_EMPTY_STACK);
        }
    }
}
