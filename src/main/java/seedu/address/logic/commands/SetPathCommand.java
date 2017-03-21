package seedu.address.logic.commands;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.core.Config;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.application.Application.Parameters;
import seedu.address.MainApp;


/**
 * Adds a person to the address book.
 */
public class SetPathCommand extends Command {

	private String StoragePath;
	
    public static final String COMMAND_WORD = "set_path";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": set a storage path for ToDoList. "
    		+ "Parameters: Storage Path"
            + "Example: " + COMMAND_WORD
            + " f:/ToDoList";

    public static final String MESSAGE_SUCCESS = "New storage path set: %1$s";

    /**
     * Set the storage path for ToDoList;
     */
    public SetPathCommand(String StoragePath){
    	this.StoragePath = StoragePath;
    	//SetPath(StoragePath);
    }

    @Override
    public CommandResult execute() {
    	 return new CommandResult(String.format(MESSAGE_SUCCESS, StoragePath));
    }

}
