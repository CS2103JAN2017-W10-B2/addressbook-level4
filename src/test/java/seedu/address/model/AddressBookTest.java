package seedu.address.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.label.Label;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Task;
import seedu.address.testutil.TypicalTestPersons;

public class AddressBookTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final ToDoList addressBook = new ToDoList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTaskList());
        assertEquals(Collections.emptyList(), addressBook.getLabelList());
    }

    @Test
    public void resetData_null_throwsAssertionError() {
        thrown.expect(AssertionError.class);
        addressBook.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ToDoList newData = new TypicalTestPersons().getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsAssertionError() {
        TypicalTestPersons td = new TypicalTestPersons();
        // Repeat td.alice twice
        List<Task> newPersons = Arrays.asList(new Task(td.testExample1), new Task(td.testExample1));
        List<Label> newTags = td.testExample1.getLabels().asObservableList();
        AddressBookStub newData = new AddressBookStub(newPersons, newTags);

        thrown.expect(AssertionError.class);
        addressBook.resetData(newData);
    }

    @Test
    public void resetData_withDuplicateTags_throwsAssertionError() {
        ToDoList typicalAddressBook = new TypicalTestPersons().getTypicalAddressBook();
        List<ReadOnlyTask> newPersons = typicalAddressBook.getTaskList();
        List<Label> newTags = new ArrayList<>(typicalAddressBook.getLabelList());
        // Repeat the first tag twice
        newTags.add(newTags.get(0));
        AddressBookStub newData = new AddressBookStub(newPersons, newTags);

        thrown.expect(AssertionError.class);
        addressBook.resetData(newData);
    }

    /**
     * A stub ReadOnlyAddressBook whose persons and tags lists can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyToDoList {
        private final ObservableList<ReadOnlyTask> persons = FXCollections.observableArrayList();
        private final ObservableList<Label> tags = FXCollections.observableArrayList();

        AddressBookStub(Collection<? extends ReadOnlyTask> persons, Collection<? extends Label> tags) {
            this.persons.setAll(persons);
            this.tags.setAll(tags);
        }

        @Override
        public ObservableList<ReadOnlyTask> getTaskList() {
            return persons;
        }

        @Override
        public ObservableList<Label> getLabelList() {
            return tags;
        }
    }

}
