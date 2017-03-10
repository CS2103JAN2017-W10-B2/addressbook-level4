package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.LABELS;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Title;
import seedu.address.model.task.Task;
import seedu.address.model.task.This_attribute_is_not_in_use;
import seedu.address.model.task.UniquePersonList;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the last doitdoit!! listing. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) [TITLE] [d/DEADLINE] [r/REMARKS] [n/NOT_IN_USE ] [l/LABEL]...\n"
            + "Example: " + COMMAND_WORD + " 1 CS1010 assignment1 d/05052017 r/demo purpose";

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
            model.updatePerson(filteredTaskListIndex, editedPerson);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
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

        Title updatedName = editPersonDescriptor.getName().orElseGet(personToEdit::getTitle);
        Deadline updatedPhone = editPersonDescriptor.getPhone().orElseGet(personToEdit::getDeadline);
        Remarks updatedEmail = editPersonDescriptor.getEmail().orElseGet(personToEdit::getRemarks);
        This_attribute_is_not_in_use updatedAddress = editPersonDescriptor.getAddress().orElseGet(personToEdit::getNot_in_use);
        LABELS updatedTags = editPersonDescriptor.getTags().orElseGet(personToEdit::getLabels);

        return new Task(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditTaskDescriptor {
        private Optional<Title> name = Optional.empty();
        private Optional<Deadline> phone = Optional.empty();
        private Optional<Remarks> email = Optional.empty();
        private Optional<This_attribute_is_not_in_use> address = Optional.empty();
        private Optional<LABELS> tags = Optional.empty();

        public EditTaskDescriptor() {}

        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            this.name = toCopy.getName();
            this.phone = toCopy.getPhone();
            this.email = toCopy.getEmail();
            this.address = toCopy.getAddress();
            this.tags = toCopy.getTags();
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyPresent(this.name, this.phone, this.email, this.address, this.tags);
        }

        public void setName(Optional<Title> name) {
            assert name != null;
            this.name = name;
        }

        public Optional<Title> getName() {
            return name;
        }

        public void setPhone(Optional<Deadline> phone) {
            assert phone != null;
            this.phone = phone;
        }

        public Optional<Deadline> getPhone() {
            return phone;
        }

        public void setEmail(Optional<Remarks> email) {
            assert email != null;
            this.email = email;
        }

        public Optional<Remarks> getEmail() {
            return email;
        }

        public void setAddress(Optional<This_attribute_is_not_in_use> address) {
            assert address != null;
            this.address = address;
        }

        public Optional<This_attribute_is_not_in_use> getAddress() {
            return address;
        }

        public void setTags(Optional<LABELS> tags) {
            assert tags != null;
            this.tags = tags;
        }

        public Optional<LABELS> getTags() {
            return tags;
        }
    }
}
