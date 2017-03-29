//@@author A0143132X
package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.label.Label;
import seedu.address.model.label.UniqueLabelList;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.UniqueTaskList;

/**
 * Adds a task to doitdoit!!.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to toDoList!!. "
            + "Parameters: TITLE (from: START TIME) (till:  DEADLINE) (remarks: REMAKRS) (label: LABELS...) "
            + "(c/ COMPLETIONSTATUS)\n"
            + "OR: TITLE (from: START TIME) (due by:  DEADLINE) (remarks: REMAKRS) (label: LABELS...) "
            + "(c/ COMPLETIONSTATUS)\n"
            + "Example: " + COMMAND_WORD
            + " Complete Assignment 1 from now till next friday remark: 20% of final grade label: Assignment c/yes";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in doitdoit!!";

    private final Task toAdd;

    /**
     * Creates an AddCommand using raw values.
     * @param isCompleted TODO
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String title, String deadline, String remarks, String startTime, String isCompleted,
            Set<String> labels)
                    throws IllegalValueException {
        final Set<Label> labelSet = new HashSet<>();
        for (String labelName : labels) {
            labelSet.add(new Label(labelName));
        }
        this.toAdd = new Task(
                new Title(title),
                deadline == null ? null : new Deadline(deadline),
                        remarks == null ? null : new Remarks(remarks),
                                startTime == null ? null : new StartTime(startTime),
                                        new UniqueLabelList(labelSet),
                                        (isCompleted.trim().equals("yes"))
                );
    }

    @Override
    public CommandResult execute() throws CommandException {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

    }

}
