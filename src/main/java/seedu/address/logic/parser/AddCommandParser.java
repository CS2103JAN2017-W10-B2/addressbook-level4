package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISCOMPLETED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABELS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.NoSuchElementException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.IncorrectCommand;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     */
    public Command parse(String args) {
        ArgumentTokenizer argsTokenizer =
                new ArgumentTokenizer(PREFIX_DEADLINE, PREFIX_REMARKS, PREFIX_START_TIME,
                        PREFIX_LABELS, PREFIX_ISCOMPLETED);
        argsTokenizer.tokenize(args);
        try {
            return new AddCommand(
                    argsTokenizer.getPreamble().get(),
                    argsTokenizer.getValue(PREFIX_DEADLINE).orElse(null),
                    argsTokenizer.getValue(PREFIX_REMARKS).orElse(null),
                    argsTokenizer.getValue(PREFIX_START_TIME).orElse(null),
                    argsTokenizer.getValue(PREFIX_ISCOMPLETED).orElse("no"),
                    ParserUtil.toSet(argsTokenizer.getAllValues(PREFIX_LABELS))
                    );
        } catch (NoSuchElementException nsee) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

}
