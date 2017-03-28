//@@author A0115333U
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.IncorrectCommand;
import seedu.address.logic.commands.SetPathCommand;

/**
 * Parses input arguments and creates a new SetPathCommand object
 */
public class SetPathCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the SetPathCommand
     * and returns an SetPathCommand object for execution.
     */
    public Command parse(String storagePath) {
        if (storagePath.trim().isEmpty()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetPathCommand.MESSAGE_USAGE));
        } else {
            return new SetPathCommand(storagePath);
        }
    }

}
