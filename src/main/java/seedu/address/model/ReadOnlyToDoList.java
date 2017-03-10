package seedu.address.model;


import javafx.collections.ObservableList;
import seedu.address.model.label.Label;
import seedu.address.model.task.ReadOnlyTask;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyToDoList {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<ReadOnlyTask> getTaskList();

    /**
     * Returns an unmodifiable view of the labels list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Label> getLabelList();

}
