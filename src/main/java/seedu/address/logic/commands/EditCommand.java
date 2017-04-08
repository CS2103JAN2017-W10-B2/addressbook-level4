package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.label.UniqueLabelList;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.UniqueTaskList;

/**
 * Edits the details of an existing task in ToDoList!!.
 */
public class EditCommand extends Command {

	// @@author A0115333U
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the last ToDoList!! listing. "
            + "Existing values (if any) will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) (TITLE) (from: STARTTIME) (till: DEADLINE) "
            + "(remark: REMARKS) (label: LABELS...) (c/ COMPLETIONSTATUS)\n"
            + "OR: INDEX (must be a positive integer) (TITLE) (due by: DEADLINE) (c/ COMPLETIONSTATUS)"
            + "(remark: REMARKS) (label: LABELS...)\n"
            + "Example 1: edit 1 c/yes"
            + "Example 2: edit 2 Titlechanged";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the ToDoList!!";
    // @@author

    private final int filteredTaskListIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param filteredTaskListIndex the index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditCommand(int filteredTaskListIndex, EditTaskDescriptor editTaskDescriptor) {
        assert filteredTaskListIndex > 0;
        assert editTaskDescriptor != null;

        // converts filteredTaskListIndex from one-based to zero-based.
        this.filteredTaskListIndex = filteredTaskListIndex - 1;

        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (filteredTaskListIndex >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToEdit = lastShownList.get(filteredTaskListIndex);

        model.updateEditStack(taskToEdit);

        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        try {
            model.updateTask(filteredTaskListIndex, editedTask);
        } catch (UniqueTaskList.DuplicateTaskException dpe) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.updateFilteredListToShowOngoing();
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, taskToEdit));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    // @@author A0115333U
    private static Task createEditedTask(ReadOnlyTask taskToEdit,
            EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Title updatedTitle = editTaskDescriptor.getTitle().orElseGet(taskToEdit::getTitle);
        Deadline updatedDeadline = editTaskDescriptor.getDeadline().orElseGet(taskToEdit::getDeadline);
        Remarks updatedRemarks = editTaskDescriptor.getRemark().orElseGet(taskToEdit::getRemarks);
        StartTime updatedStartTime = editTaskDescriptor.getStartTime().orElseGet(taskToEdit::getStartTime);
        UniqueLabelList updatedLabels = editTaskDescriptor.getLabels().orElseGet(taskToEdit::getLabels);
        boolean updatedIsCompleted = editTaskDescriptor.getIsCompleted();

        return new Task(updatedTitle, updatedDeadline, updatedRemarks, updatedStartTime, updatedLabels,
                updatedIsCompleted);
    }
    // @@author

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Optional<Title> title = Optional.empty();
        private Optional<Deadline> deadline = Optional.empty();
        private Optional<Remarks> remark = Optional.empty();
        private Optional<StartTime> startTime = Optional.empty();
        private Optional<UniqueLabelList> labels = Optional.empty();
        // @@author A0115333U
        private boolean isCompleted = false;
        private boolean isCompletededited = false;

        public EditTaskDescriptor() {}

        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            this.title = toCopy.getTitle();
            this.deadline = toCopy.getDeadline();
            this.remark = toCopy.getRemark();
            this.startTime = toCopy.getStartTime();
            this.labels = toCopy.getLabels();
            this.isCompleted = toCopy.getIsCompleted();
        }


        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyPresent(this.title, this.deadline, this.remark, this.startTime,
                    this.labels) || this.isCompletededited;
        }
        // @@author

        public void setTitle(Optional<Title> title) {
            assert title != null;
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return title;
        }

        public void setDeadline(Optional<Deadline> deadline) {
            assert deadline != null;
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return deadline;
        }

        public void setRemarks(Optional<Remarks> remark) {
            assert remark != null;
            this.remark = remark;
        }

        public Optional<Remarks> getRemark() {
            return remark;
        }

        // @@author A0115333U
        public void setIsCompleted(boolean isCompleted) {
            this.isCompleted = isCompleted;
        }

        public boolean getIsCompleted() {
            return isCompleted;
        }

        public void setIsCompletededited(boolean isCompletededited) {
            this.isCompletededited = isCompletededited;
        }
        // @@author

        public void setStartTime(Optional<StartTime> startTime) {
            assert startTime != null;
            this.startTime = startTime;
        }

        public Optional<StartTime> getStartTime() {
            return startTime;
        }

        public void setTags(Optional<UniqueLabelList> tags) {
            assert tags != null;
            this.labels = tags;
        }

        public Optional<UniqueLabelList> getLabels() {
            return labels;
        }
    }
}
