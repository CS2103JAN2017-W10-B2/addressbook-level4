package seedu.address.logic.commands;

import java.util.Set;

/**
 * Finds and lists all tasks in doitdoit!! whose title contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose title or remarks contain any of "
            + "the specified keywords Eand displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " assignment math test";

    //@@author A0143132X
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
