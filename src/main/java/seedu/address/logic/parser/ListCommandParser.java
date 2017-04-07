//@@author A011333U
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.IncorrectCommand;
import seedu.address.logic.commands.ListCommand;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * ListCommand and returns an ListCommand object for execution.
     */
    public Command parse(String args) {
        switch (args.trim()) {
        case "all":
            return new ListCommand("all");

        case "ongoing":
            return new ListCommand("ongoing");

        case "completed":
            return new ListCommand("completed");

        default:
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }
}
