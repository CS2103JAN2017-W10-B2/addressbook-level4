package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.KEYWORDS_ARGS_FORMAT;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

import seedu.address.logic.commands.Command;

import seedu.address.logic.commands.IncorrectCommand;
import seedu.address.logic.commands.UndoCommand;

public class UndoCommandParser {
	
	public Command parse(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
        }

        // keywords delimited by whitespace
        //final String[] keywords = matcher.group("keywords").split("\\s+");
        //final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new UndoCommand();
    }

}
