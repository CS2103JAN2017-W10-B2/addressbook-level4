//@@author A0143132X
package seedu.address.logic.commands;

import java.util.Set;

/**
 * Finds and lists all tasks in doitdoit!! whose substring of title and remarks matches any of the argument keywords.
 * OR finds and lists all tasks in doitdoit!! whose labels exactly matches any of the argument label keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose title or remarks contain any of "
            + "the specified keywords Eand displays them as a list with index numbers.\n"
            + "Parameters: [KEYWORDS...] [#LABEL_KEYWORDS...] \n"
            + "Example: " + COMMAND_WORD + " assignment math test";

    private final Set<String> keywords;
    private final boolean isLabel;

    public FindCommand(Set<String> keywords, boolean isLabel) {
        this.keywords = keywords;
        this.isLabel = isLabel;
    }

    @Override
    public CommandResult execute() {
        if (isLabel) {
            model.updateFilteredTaskListByLabel(keywords);
            return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
        } else {
            model.updateFilteredTaskList(keywords);
            return new CommandResult(getMessageForTaskListShownSummary(model.getFilteredTaskList().size()));
        }
    }

}
