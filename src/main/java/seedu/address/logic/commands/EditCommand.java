package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.LABELS;
import seedu.address.model.task.DEADLINE;
import seedu.address.model.task.REMARKS;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.TITLE;
import seedu.address.model.task.Task;
import seedu.address.model.task.This_attribute_is_not_in_use;
import seedu.address.model.task.UniquePersonList;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) [NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS ] [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 p/91234567 e/johndoe@yahoo.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final int filteredPersonListIndex;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param filteredPersonListIndex the index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(int filteredPersonListIndex, EditPersonDescriptor editPersonDescriptor) {
        assert filteredPersonListIndex > 0;
        assert editPersonDescriptor != null;

        // converts filteredPersonListIndex from one-based to zero-based.
        this.filteredPersonListIndex = filteredPersonListIndex - 1;

        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<ReadOnlyTask> lastShownList = model.getFilteredPersonList();

        if (filteredPersonListIndex >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask personToEdit = lastShownList.get(filteredPersonListIndex);
        Task editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        try {
            model.updatePerson(filteredPersonListIndex, editedPerson);
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
                                             EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        TITLE updatedName = editPersonDescriptor.getName().orElseGet(personToEdit::getTitle);
        DEADLINE updatedPhone = editPersonDescriptor.getPhone().orElseGet(personToEdit::getDeadline);
        REMARKS updatedEmail = editPersonDescriptor.getEmail().orElseGet(personToEdit::getRemarks);
        This_attribute_is_not_in_use updatedAddress = editPersonDescriptor.getAddress().orElseGet(personToEdit::getNot_in_use);
        LABELS updatedTags = editPersonDescriptor.getTags().orElseGet(personToEdit::getLabels);

        return new Task(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Optional<TITLE> name = Optional.empty();
        private Optional<DEADLINE> phone = Optional.empty();
        private Optional<REMARKS> email = Optional.empty();
        private Optional<This_attribute_is_not_in_use> address = Optional.empty();
        private Optional<LABELS> tags = Optional.empty();

        public EditPersonDescriptor() {}

        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
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

        public void setName(Optional<TITLE> name) {
            assert name != null;
            this.name = name;
        }

        public Optional<TITLE> getName() {
            return name;
        }

        public void setPhone(Optional<DEADLINE> phone) {
            assert phone != null;
            this.phone = phone;
        }

        public Optional<DEADLINE> getPhone() {
            return phone;
        }

        public void setEmail(Optional<REMARKS> email) {
            assert email != null;
            this.email = email;
        }

        public Optional<REMARKS> getEmail() {
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
