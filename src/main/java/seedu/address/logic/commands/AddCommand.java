package seedu.address.logic.commands;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.TimeUtil;
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
 * Adds a task to ToDoList.
 */
public class AddCommand extends Command {
    // @@author A0135795R
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to toDoList!!. "
            + "Parameters: TITLE (from: START TIME) (till:  DEADLINE) (remark: REMAKRS) (label: LABELS...) "
            + "(c/ COMPLETIONSTATUS)\n"
            + "OR: TITLE (from: START TIME) (due by:  DEADLINE) (remark: REMAKRS) (label: LABELS...) "
            + "(c/ COMPLETIONSTATUS)\n" + "Example: " + COMMAND_WORD
            + " Complete Assignment 1 from now till next friday remark: 20% of final grade label: Assignment c/yes";
    // @@author
    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_TIME_CONFLICT = "Start time should be before deadline. Please try again.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in doitdoit!!";

    private final Task toAdd;

    /**
     * Creates an AddCommand using raw values.
     *
     * @throws IllegalValueException
     *             if any of the raw values are invalid
     */
    // @@author A0143132X
    public AddCommand(String title, String deadline, String remarks, String startTime, String isCompleted,
            Set<String> labels) throws IllegalValueException {
        final Set<Label> labelSet = new HashSet<>();
        for (String labelName : labels) {
            labelSet.add(new Label(labelName));
        }
        this.toAdd = new Task(new Title(title), deadline == null ? null : new Deadline(deadline),
                remarks == null ? null : new Remarks(remarks), startTime == null ? null : new StartTime(startTime),
                new UniqueLabelList(labelSet), (isCompleted.trim().equals("yes")));
    }
    // @@author

    @Override
    public CommandResult execute() throws CommandException {
        assert model != null;
        try {
            if (toAdd.hasDeadline() && toAdd.hasStartTime()) {
                LocalDateTime deadline = TimeUtil.getDateTime(toAdd.getDeadline().value);
                LocalDateTime startTime = TimeUtil.getDateTime(toAdd.getStartTime().value);
                if (deadline.compareTo(startTime) < 0) {
                    throw new CommandException(MESSAGE_TIME_CONFLICT);
                }
            }
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

    }

}
