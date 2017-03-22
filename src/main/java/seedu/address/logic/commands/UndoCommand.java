package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.LastSuccessfulAction;
import seedu.address.model.ModelManager;

public class UndoCommand extends Command {
	
	 public static final String COMMAND_WORD = "undo";
	 
	 public static final String MESSAGE_USAGE = COMMAND_WORD + ": undo a previous command to doitdoit!!. ";
	 
	 public static final String MESSAGE_SUCCESS = "Previous command undone: %1$s";
	 
	 

	@Override
	public CommandResult execute() throws CommandException {
		
		 model.undoTask();
		
		return null;
	}

}
