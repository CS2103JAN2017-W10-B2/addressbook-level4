//@@author A0115333U
package seedu.address.logic.commands;

import java.io.IOException;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.core.Config;



/**
 * Set storage path for doitdoit!!.
 */
public class SetPathCommand extends Command {

	private String StoragePath;
	
	private Config initializedConfig;
	
    public static final String COMMAND_WORD = "set_path";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": set a storage path for ToDoList. "
    		+ "Parameters: Storage_Path\n"
    		+ "Note that '.xml' is necessary. App needs to restart after this setting.\n"
            + "Example: " + COMMAND_WORD 
            + " f:/ToDoList.xml"+ "\n"
            + "Example: " + COMMAND_WORD 
            + " default";

    public static final String MESSAGE_SUCCESS = "New storage path set: %1$s. Please restart the App.";

    /**
     * Set the storage path for ToDoList;
     */
    public SetPathCommand(String StoragePath){
        if (StoragePath.trim().equals("default")||StoragePath.trim()==null){
        		this.StoragePath = "data/ToDoList.xml";
        }
        else {
        	this.StoragePath = StoragePath.trim();
        }
        	
        	
    }

    @Override
    public CommandResult execute() {
        try {
            Optional<Config> configOptional = ConfigUtil.readConfig("config.tim");
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            initializedConfig = new Config();
        }
        
        initializedConfig.setAddressBookFilePath(StoragePath);
        
        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, "config.tim");
        } catch (IOException e) {
        }
    	 return new CommandResult(String.format(MESSAGE_SUCCESS, StoragePath));
    }

}
