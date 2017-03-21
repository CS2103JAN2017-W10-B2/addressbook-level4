package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.label.UniqueLabelList;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Title;
import seedu.address.model.task.Task;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.UniqueTaskList;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the last doitdoit!! listing. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) [TITLE] [from STARTTIME] [to DEADLINE] "
            + "[remark: REMARKS] [label: LABELS]...\n";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in the address book.";

    private final int filteredTaskListIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param filteredTaskListIndex the index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditCommand(int filteredTaskListIndex, EditTaskDescriptor editTaskDescriptor) {
        assert filteredTaskListIndex > 0;
        assert editTaskDescriptor != null;

        // converts filteredPersonListIndex from one-based to zero-based.
        this.filteredTaskListIndex = filteredTaskListIndex - 1;

        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (filteredTaskListIndex >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask personToEdit = lastShownList.get(filteredTaskListIndex);
        Task editedPerson = createEditedPerson(personToEdit, editTaskDescriptor);

        try {
            model.updateTask(filteredTaskListIndex, editedPerson);
        } catch (UniqueTaskList.DuplicateTaskException dpe) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        model.updateFilteredListToShowAll();
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, personToEdit));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Task createEditedPerson(ReadOnlyTask personToEdit,
                                             EditTaskDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Title updatedName = editPersonDescriptor.getTitle().orElseGet(personToEdit::getTitle);
        Deadline updatedPhone = editPersonDescriptor.getDeadline().orElseGet(personToEdit::getDeadline);
        Remarks updatedEmail = editPersonDescriptor.getRemark().orElseGet(personToEdit::getRemarks);
        StartTime updatedAddress = editPersonDescriptor.getAddress().orElseGet(personToEdit::getNot_in_use);
        UniqueLabelList updatedTags = editPersonDescriptor.getLabels().orElseGet(personToEdit::getLabels);
        boolean updatedIsCompleted = editPersonDescriptor.getIsCompleted();
        
        return new Task(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedIsCompleted);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditTaskDescriptor {
        private Optional<Title> title = Optional.empty();
        private Optional<Deadline> deadline = Optional.empty();
        private Optional<Remarks> remark = Optional.empty();
        private Optional<StartTime> address = Optional.empty();
        private Optional<UniqueLabelList> labels = Optional.empty();
        private boolean isCompleted = false;
        private boolean isCompletededited = false;

        public EditTaskDescriptor() {}

        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            this.title = toCopy.getTitle();
            this.deadline = toCopy.getDeadline();
            this.remark = toCopy.getRemark();
            this.address = toCopy.getAddress();
            this.labels = toCopy.getLabels();
            this.isCompleted = toCopy.getIsCompleted();
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyPresent(this.title, this.deadline, this.remark, this.address, this.labels)||this.isCompletededited;
        }

        public void setName(Optional<Title> name) {
            assert name != null;
            this.title = name;
        }

        public Optional<Title> getTitle() {
            return title;
        }

        public void setPhone(Optional<Deadline> phone) {
            assert phone != null;
            this.deadline = phone;
        }

        public Optional<Deadline> getDeadline() {
            return deadline;
        }

        public void setEmail(Optional<Remarks> email) {
            assert email != null;
            this.remark = email;
        }

        public Optional<Remarks> getRemark() {
            return remark;
        }
        
        public void setIsCompleted(boolean isCompleted) {
            this.isCompleted = isCompleted;
        }
        
        public boolean getIsCompleted() {
            return isCompleted;
        }
        
        public void setIsCompletededited(boolean isCompletededited) {
            this.isCompletededited = isCompletededited;
        }

        public void setAddress(Optional<StartTime> address) {
            assert address != null;
            this.address = address;
        }

        public Optional<StartTime> getAddress() {
            return address;
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
