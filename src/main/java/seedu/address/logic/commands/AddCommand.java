package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.TITLE;
import seedu.address.model.task.Task;
import seedu.address.model.task.This_attribute_is_not_in_use;
import seedu.address.model.task.UniquePersonList;
import seedu.address.model.tag.LABELS;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to doitdoit!!. "
    		+ "Parameters: TITLE [d/DEADLINE] [r/REMARKS] [l/LABELS]...\n"
            + "Example: " + COMMAND_WORD
            + " Complete Assignment 1 d/080217 r/20% of final grade n/this is a bug l/Uni Assignment";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in doitdoit!!";

    private final Task toAdd;

    /**
     * Creates an AddCommand using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String title, String deadline, String remarks, String not_in_use, Set<String> labels)
            throws IllegalValueException {
        final Set<Tag> labelSet = new HashSet<>();
        for (String labelName : labels) {
            labelSet.add(new Tag(labelName));
        }
        this.toAdd = new Task(
                new TITLE(title),
                new Deadline(deadline),
                new Remarks(remarks),
                new This_attribute_is_not_in_use(not_in_use),
                new LABELS(labelSet)
        );
    }

    @Override
    public CommandResult execute() throws CommandException {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniquePersonList.DuplicatePersonException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

    }

}
