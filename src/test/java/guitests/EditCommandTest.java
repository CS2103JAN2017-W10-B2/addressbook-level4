package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.label.Label;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Title;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TestTask;

// TODO edit this
// TODO: reduce GUI tests by transferring some tests to be covered by lower level tests.
public class EditCommandTest extends ToDoListGuiTest {

    // The list of persons in the person list panel is expected to match this
    // list.
    // This list is updated with every successful call to assertEditSuccess().
    TestTask[] expectedTasksList = td.getTypicalTasks();

    @Test
    public void edit_allFieldsSpecified_success() throws Exception {
        String detailsToEdit = "Buy chocolates from:04/15/17 till:04/15/17 remark:dark chocolate the best #luvluv";
        int addressBookIndex = 1;

        TestTask editedPerson = new TaskBuilder().withTitle("Buy chocolates").withDeadline("04/15/17")
                .withRemarks("dark chocolate the best").withStartTime("04/15/17").withLabels("luvluv")
                .build();

        assertEditSuccess(addressBookIndex, addressBookIndex, detailsToEdit, editedPerson);
    }

    @Test
    public void edit_notAllFieldsSpecified_success() throws Exception {
        String detailsToEdit = "#sweetie #bestie";
        int addressBookIndex = 2;

        TestTask personToEdit = expectedTasksList[addressBookIndex - 1];
        TestTask editedPerson = new TaskBuilder(personToEdit).withLabels("sweetie", "bestie").build();

        assertEditSuccess(addressBookIndex, addressBookIndex, detailsToEdit, editedPerson);
    }

    @Test
    public void edit_clearTags_success() throws Exception {
        String detailsToEdit = "#";
        int addressBookIndex = 2;

        TestTask personToEdit = expectedTasksList[addressBookIndex - 1];
        TestTask editedPerson = new TaskBuilder(personToEdit).withLabels().build();

        assertEditSuccess(addressBookIndex, addressBookIndex, detailsToEdit, editedPerson);
    }

    @Test
    public void edit_findThenEdit_success() throws Exception {
        commandBox.runCommand("find recess");

        String detailsToEdit = "Additional Lecture during recess week";
        int filteredPersonListIndex = 1;
        int addressBookIndex = 5;

        TestTask personToEdit = expectedTasksList[addressBookIndex - 1];
        TestTask editedPerson = new TaskBuilder(personToEdit).withTitle("Additional Lecture during recess week").build();

        assertEditSuccess(filteredPersonListIndex, addressBookIndex, detailsToEdit, editedPerson);
    }

    @Test
    public void edit_missingPersonIndex_failure() {
        commandBox.runCommand("edit Bobby");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void edit_invalidPersonIndex_failure() {
        commandBox.runCommand("edit 8 Bobby");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void edit_noFieldsSpecified_failure() {
        commandBox.runCommand("edit 1");
        assertResultMessage(EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void edit_invalidValues_failure() {
        commandBox.runCommand("edit 1 *&");
        assertResultMessage(Title.MESSAGE_TITLE_CONSTRAINTS);

        commandBox.runCommand("edit 1 till:abcd");
        assertResultMessage(Deadline.MESSAGE_DEADLINE_CONSTRAINTS);

        commandBox.runCommand("edit 1 remark:yahoo#!!!");
        assertResultMessage(Remarks.MESSAGE_REMARKS_CONSTRAINTS);

        commandBox.runCommand("edit 1 from:");
        assertResultMessage(StartTime.MESSAGE_START_TIME_CONSTRAINTS);

        commandBox.runCommand("edit 1 #*&");
        assertResultMessage(Label.MESSAGE_LABEL_CONSTRAINTS);
    }

    @Test
    public void edit_duplicatePerson_failure() {
        commandBox.runCommand(
                "edit 3 assignment due from:10/12/17 till:10/12/17 remark: hand in assignment at LT27 #science #event");
        assertResultMessage(EditCommand.MESSAGE_DUPLICATE_TASK);
    }

    /**
     * Checks whether the edited person has the correct updated details.
     *
     * @param filteredPersonListIndex
     *            index of person to edit in filtered list
     * @param addressBookIndex
     *            index of person to edit in the address book. Must refer to the
     *            same person as {@code filteredPersonListIndex}
     * @param detailsToEdit
     *            details to edit the person with as input to the edit command
     * @param editedPerson
     *            the expected person after editing the person's details
     */
    private void assertEditSuccess(int filteredPersonListIndex, int addressBookIndex, String detailsToEdit,
            TestTask editedPerson) {
        commandBox.runCommand("edit " + filteredPersonListIndex + " " + detailsToEdit);

        // confirm the new card contains the right data
        TaskCardHandle editedCard = taskListPanel.navigateToPerson(editedPerson.getTitle().fullTitle);
        assertMatching(editedPerson, editedCard);

        // confirm the list now contains all previous persons plus the person
        // with updated details
        expectedTasksList[addressBookIndex - 1] = editedPerson;
        assertTrue(taskListPanel.isListMatching(expectedTasksList));
        assertResultMessage(String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedPerson));
    }
}
