package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.KEYWORD_ONLY_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISCOMPLETED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABELS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ONLY_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.logic.commands.IncorrectCommand;
import seedu.address.model.label.UniqueLabelList;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     */
    public Command parse(String args) {
        assert args != null;
        //@@author A0135795R
        ArgumentTokenizer argsTokenizer;
        if (args.contains(KEYWORD_ONLY_DEADLINE)) {
            argsTokenizer = new ArgumentTokenizer(PREFIX_START_TIME, PREFIX_ONLY_DEADLINE, PREFIX_REMARKS,
                    PREFIX_LABELS, PREFIX_ISCOMPLETED);
        } else {
            argsTokenizer = new ArgumentTokenizer(PREFIX_START_TIME, PREFIX_DEADLINE, PREFIX_REMARKS,
                    PREFIX_LABELS, PREFIX_ISCOMPLETED);
        }
        argsTokenizer.tokenize(args);
        List<Optional<String>> preambleFields = ParserUtil.splitPreamble(argsTokenizer.getPreamble().orElse(""), 2);

        Optional<Integer> index = preambleFields.get(0).flatMap(ParserUtil::parseIndex);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        try {
            Optional<String> deadline;
            if (args.contains(KEYWORD_ONLY_DEADLINE)) {
                deadline = argsTokenizer.getValue(PREFIX_ONLY_DEADLINE);
            } else {
                deadline = argsTokenizer.getValue(PREFIX_DEADLINE);
            }
            editTaskDescriptor.setTitle(ParserUtil.parseTitle(preambleFields.get(1)));
            editTaskDescriptor.setDeadline(ParserUtil.parseDeadline(deadline));
            editTaskDescriptor.setRemarks(ParserUtil.parseRemarks(argsTokenizer.getValue(PREFIX_REMARKS)));
            editTaskDescriptor.setStartTime(ParserUtil.parseStartTime(argsTokenizer.getValue(PREFIX_START_TIME)));
            editTaskDescriptor.setIsCompleted(ParserUtil.parseIsCompleted(argsTokenizer
                    .getValue(PREFIX_ISCOMPLETED)).toString().trim().equals("Optional[yes]"));
            editTaskDescriptor.setIsCompletededited(argsTokenizer.getValue(PREFIX_ISCOMPLETED).isPresent());
            editTaskDescriptor.setTags(parseTagsForEdit(ParserUtil.toSet(argsTokenizer.getAllValues(PREFIX_LABELS))));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
        //@@author

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            return new IncorrectCommand(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index.get(), editTaskDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into an {@code Optional<UniqueTagList>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Optional<UniqueTagList>} containing zero tags.
     */
    private Optional<UniqueLabelList> parseTagsForEdit(Collection<String> tags) throws IllegalValueException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseLabels(tagSet));
    }

}
