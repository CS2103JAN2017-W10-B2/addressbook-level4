package seedu.address.model;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.model.label.Label;
import seedu.address.model.label.UniqueLabelList;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.model.task.UniqueTaskList.DuplicateTaskException;

/**
 * Wraps all data at the to-do-list level Duplicates are not allowed (by .equals
 * comparison)
 */
public class ToDoList implements ReadOnlyToDoList {

    private final UniqueTaskList tasks;
    private UniqueTaskList tempTasks;
    private final UniqueLabelList labels;

    /*
     * The 'unusual' code block below is an non-static initialization block,
     * sometimes used to avoid duplication between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are
     * other ways to avoid duplication among constructors.
     */
    {
        tasks = new UniqueTaskList();
        labels = new UniqueLabelList();
        tempTasks = new UniqueTaskList();
    }

    public ToDoList() {
    }

    /**
     * Creates a ToDoList!! using the Tasks and Labels in the {@code toBeCopied}
     */
    public ToDoList(ReadOnlyToDoList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    public void setTasks(List<? extends ReadOnlyTask> tasks) throws UniqueTaskList.DuplicateTaskException {
        this.tasks.setTasks(tasks);
    }

    public void setLabels(Collection<Label> labels) throws UniqueLabelList.DuplicateLabelException {
        this.labels.setLabels(labels);
    }

    public void resetData(ReadOnlyToDoList newData) {
        assert newData != null;

        tasks.backupIntoUndoStack();

        this.tempTasks.setTasks(this.tasks);

        try {
            setTasks(newData.getTaskList());
        } catch (UniqueTaskList.DuplicateTaskException e) {
            assert false : "doitdoit!! should not have duplicate tasks";
        }
        try {
            setLabels(newData.getLabelList());
        } catch (UniqueLabelList.DuplicateLabelException e) {
            assert false : "doitdoit!! should not have duplicate labels";
        }
        syncMasterLabelListWith(tasks);
    }

    // @@author A0115333U
    public void sort_tasks() {
        this.tasks.sort();
    }
    // @@author

    //// task-level operations

    /**
     * Adds a task to the TodoList!!. Also checks the new task's labels and
     * updates {@link #labels} with any new labels found, and updates the Label
     * objects in the task to point to those in {@link #labels}.
     *
     * @throws UniqueTaskList.DuplicateTaskException
     *             if an equivalent task already exists.
     */
    public void addTask(Task p) throws UniqueTaskList.DuplicateTaskException {
        syncMasterLabelListWith(p);
        tasks.add(p);
    }

    /**
     * Updates the task in the list at position {@code index} with
     * {@code editedReadOnlyTask}. {@code ToDoList}'s label list will be updated
     * with the labels of {@code editedReadOnlyTask}.
     *
     * @see #syncMasterLabelListWith(Task)
     *
     * @throws DuplicateTaskException
     *             if updating the task's details causes the task to be
     *             equivalent to another existing task in the list.
     * @throws IndexOutOfBoundsException
     *             if {@code index} < 0 or >= the size of the list.
     */
    public void updateTask(int index, ReadOnlyTask editedReadOnlyTask) throws UniqueTaskList.DuplicateTaskException {
        assert editedReadOnlyTask != null;

        Task editedTask = new Task(editedReadOnlyTask);
        syncMasterLabelListWith(editedTask);
        // TODO: the labels master list will be updated even though the below
        // line fails.
        // This can cause the labels master list to have additional labels that
        // are not labelged to any task
        // in the task list.
        tasks.updateTask(index, editedTask);
    }

    /**
     * Ensures that every label in this task: - exists in the master list
     * {@link #labels} - points to a Label object in the master list
     */
    private void syncMasterLabelListWith(Task task) {
        final UniqueLabelList taskLabels = task.getLabels();
        labels.mergeFrom(taskLabels);

        // Create map with values = label object references in the master list
        // used for checking task label references
        final Map<Label, Label> masterLabelObjects = new HashMap<>();
        labels.forEach(label -> masterLabelObjects.put(label, label));

        // Rebuild the list of task labels to point to the relevant labels in
        // the master label list.
        final Set<Label> correctLabelReferences = new HashSet<>();
        taskLabels.forEach(label -> correctLabelReferences.add(masterLabelObjects.get(label)));
        task.setLabels(new UniqueLabelList(correctLabelReferences));
    }

    /**
     * Ensures that every label in these tasks: - exists in the master list
     * {@link #labels} - points to a Label object in the master list
     *
     * @see #syncMasterLabelListWith(Task)
     */
    private void syncMasterLabelListWith(UniqueTaskList tasks) {
        tasks.forEach(this::syncMasterLabelListWith);
    }

    public boolean removeTask(ReadOnlyTask key) throws UniqueTaskList.TaskNotFoundException {
        if (tasks.remove(key)) {
            return true;
        } else {
            throw new UniqueTaskList.TaskNotFoundException();
        }
    }

    /**
     * undo recent command implementations in uniquetasklist.java
     */
    public void undoTask() throws EmptyStackException {
        tasks.undoTask();

    }

    //// label-level operations

    public void addLabel(Label t) throws UniqueLabelList.DuplicateLabelException {
        labels.add(t);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asObservableList().size() + " tasks, " + labels.asObservableList().size() + " labels";
        // TODO: refine later
    }

    @Override
    public ObservableList<ReadOnlyTask> getTaskList() {
        return new UnmodifiableObservableList<>(tasks.asObservableList());
    }

    @Override
    public ObservableList<Label> getLabelList() {
        return new UnmodifiableObservableList<>(labels.asObservableList());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ToDoList // instanceof handles nulls
                        && this.tasks.equals(((ToDoList) other).tasks)
                        && this.labels.equalsOrderInsensitive(((ToDoList) other).labels));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing
        // your own
        return Objects.hash(tasks, labels);
    }

    /*
     * public void backup() { tasks.backupIntoUndoStack(); }
     */

}
