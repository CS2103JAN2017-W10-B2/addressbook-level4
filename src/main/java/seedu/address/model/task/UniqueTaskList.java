package seedu.address.model.task;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.commons.exceptions.DuplicateDataException;
import seedu.address.commons.util.TimeUtil;

/**
 * A list of tasks that enforces uniqueness between its elements and does not
 * allow nulls in task titles.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 */
public class UniqueTaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final FixedSizeStack<ObservableList<Task>> undoStack = new FixedSizeStack();

    /**
     * Returns true if the list contains an equivalent task as the given
     * argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    //public UniqueTaskList(){
    	//backupIntoUndoStack();
   // }

    /**
     * Adds a task to the list.
     *
     * @throws DuplicateTaskException
     *             if the task to add is a duplicate of an existing task in the
     *             list.
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        backupIntoUndoStack();
        internalList.add(toAdd);
    }

    /**
     * Updates the task in the list at position {@code index} with
     * {@code editedTask}.
     *
     * @throws DuplicateTaskException
     *             if updating the task's details causes the task to be
     *             equivalent to another existing task in the list.
     * @throws IndexOutOfBoundsException
     *             if {@code index} < 0 or >= the size of the list.
     */
    public void updateTask(int index, ReadOnlyTask editedTask) throws DuplicateTaskException {
        assert editedTask != null;

        ObservableList<Task> currList = FXCollections.observableArrayList();
        Task temp;
        for (Task t : internalList) {
            temp = new Task(t);
            currList.add(temp);
        }
        undoStack.push(currList);

        Task taskToUpdate = internalList.get(index);
        if (!taskToUpdate.equals(editedTask) && internalList.contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        taskToUpdate.resetData(editedTask);
        // TODO: The code below is just a workaround to notify observers of the
        // updated task.
        // The right way is to implement observable properties in the Task
        // class.
        // Then, TaskCard should then bind its text labels to those observable
        // properties.
        internalList.set(index, taskToUpdate);
    }

    /**
     * Removes the equivalent task from the list.
     *
     * @throws TaskNotFoundException
     *             if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyTask toRemove) throws TaskNotFoundException {
        assert toRemove != null;

        backupIntoUndoStack();

        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }

    public void backupIntoUndoStack() {
    	ObservableList<Task> currList = FXCollections.observableArrayList();
        for (Task t : internalList) {
            currList.add(t);
        }
        undoStack.push(currList);
	}

    // @@author A0115333U
    /**
     * Sort the list.
     *
     */
    public void sort() {
        internalList.sort(new Comparator<Task>() {
            @Override
            public int compare(Task x, Task y) {
                return compare(x.getDeadline(), y.getDeadline());
            }

            private int compare(Deadline a, Deadline b) {
                if (a != null && b != null) {
                    // CHECKSTYLE.OFF: LineLength
                    return TimeUtil.getDateTime(a.toString()).isBefore(TimeUtil.getDateTime(b.toString())) ? -1
                            : TimeUtil.getDateTime(a.toString()).isAfter(TimeUtil.getDateTime(b.toString())) ? 1 : 0;
                    // CHECKSTYLE.ON: LineLength
                } else {
                    return 0;
                }
            }
        });

    }
    // @@author

    public void setTasks(UniqueTaskList replacement) {
        this.internalList.setAll(replacement.internalList);
    }

    public void setTasks(List<? extends ReadOnlyTask> tasks) throws DuplicateTaskException {
        final UniqueTaskList replacement = new UniqueTaskList();
        for (final ReadOnlyTask task : tasks) {
            replacement.add(new Task(task));
        }
        setTasks(replacement);
    }

    public void undoTask() throws EmptyStackException {

    	if(undoStack.size() == 1){
    		throw new EmptyStackException();

    	} else{

    		ObservableList<Task> prevList = FXCollections.observableArrayList();
    		prevList = undoStack.pop();
    		this.internalList.setAll(prevList);
    	}

	}

    public UnmodifiableObservableList<Task> asObservableList() {
        return new UnmodifiableObservableList<>(internalList);
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                        && this.internalList.equals(((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Signals that an operation would have violated the 'no duplicates'
     * property of the list.
     */
    public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would
     * fail because there is no such matching task in the list.
     */
    public static class TaskNotFoundException extends Exception {
    }





}
