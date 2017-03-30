package seedu.address.model;

import java.util.EmptyStackException;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Logger;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.commons.events.model.ToDoListChangedEvent;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.model.task.UniqueTaskList.DuplicateTaskException;
import seedu.address.model.task.UniqueTaskList.TaskNotFoundException;

/**
 * Represents the in-memory model of the doitdoit!! data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ToDoList toDoList;
    private final FilteredList<ReadOnlyTask> filteredTasks;
    public Stack<LastSuccessfulAction> undoStack;
    public Stack<ReadOnlyTask> editStack;

    /**
     * Initializes a ModelManager with the given doitdoit!! and userPrefs.
     */
    public ModelManager(ReadOnlyToDoList toDoList, UserPrefs userPrefs) {
        super();
        assert !CollectionUtil.isAnyNull(toDoList, userPrefs);

        logger.fine("Initializing with to do list: " + toDoList + " and user prefs " + userPrefs);

        this.toDoList = new ToDoList(toDoList);
        filteredTasks = new FilteredList<>(this.toDoList.getTaskList());
        filteredTasks.setPredicate(ReadOnlyTask->!ReadOnlyTask.getIsCompleted());
        undoStack = new Stack<LastSuccessfulAction>();
        editStack = new Stack<ReadOnlyTask>();
    }

    public ModelManager() {
        this(new ToDoList(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyToDoList newData) {
        undoStack.push(new LastSuccessfulAction(new Task(), false, false, false, true));
        toDoList.resetData(newData);
        indicateToDoListChanged();
    }

    @Override
    public ReadOnlyToDoList getToDoList() {
        return toDoList;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateToDoListChanged() {
        raise(new ToDoListChangedEvent(toDoList));
    }

    @Override
    public synchronized void deleteTask(ReadOnlyTask target) throws TaskNotFoundException {
        undoStack.push(new LastSuccessfulAction(target, false, true, false, false));
        toDoList.removeTask(target);
        indicateToDoListChanged();
    }

    @Override
    public synchronized void addTask(Task task) throws UniqueTaskList.DuplicateTaskException {
        undoStack.push(new LastSuccessfulAction(task, true, false, false, false));
        toDoList.addTask(task);
        toDoList.sort_tasks();
        updateFilteredListToShowOngoing();
        indicateToDoListChanged();
    }

    @Override
    public void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask)
            throws UniqueTaskList.DuplicateTaskException {
        undoStack.push(new LastSuccessfulAction(editedTask, false, false, true, false));


        assert editedTask != null;

        int toDoListIndex = filteredTasks.getSourceIndex(filteredTaskListIndex);
        toDoList.updateTask(toDoListIndex, editedTask);
        toDoList.sort_tasks();
        indicateToDoListChanged();
    }
  //@@author A0138831A
    @Override
    public void updateEditStack(ReadOnlyTask taskToEdit) {
        editStack.push(taskToEdit);

    }
  //@@author A0138831A
    @Override
    public void undoTask() throws EmptyStackException  {

        if (!undoStack.empty()) {


            LastSuccessfulAction lsa = undoStack.pop();
            if (lsa.isAdd) {
                try {
                    undoAddTask(lsa.task);
                } catch (TaskNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (lsa.isDelete) {
                try {
                    undoDeleteTask((Task) lsa.task);
                } catch (DuplicateTaskException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (lsa.isClear) {

                try {
                    toDoList.undoResetData();
                } catch (DuplicateTaskException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            Task edited = (Task) editStack.pop();

            if (lsa.isEdit) {
                try {
                    removeEditedTask(lsa.task); // to remove the add of edited task
                    reAdd(edited); //add original task before edit
                } catch (TaskNotFoundException | DuplicateTaskException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }


    }
  //@@author A0138831A
    private void reAdd(Task task) throws UniqueTaskList.DuplicateTaskException  {
        toDoList.addTask(task);
        updateFilteredListToShowAll();
        indicateToDoListChanged();


    }
  //@@author A0138831A
    private void removeEditedTask(ReadOnlyTask task) throws TaskNotFoundException  {
        toDoList.removeTask(task);
        indicateToDoListChanged();
    }
  //@@author A0138831A
    private void undoDeleteTask(Task task) throws UniqueTaskList.DuplicateTaskException {
        toDoList.addTask(task);
        updateFilteredListToShowAll();
        indicateToDoListChanged();

    }
  //@@author A0138831A
    private void undoAddTask(ReadOnlyTask task)  throws TaskNotFoundException {
        toDoList.removeTask(task);
        indicateToDoListChanged();

    }

    //=========== Filtered Task List Accessors =============================================================

    @Override
    public UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList() {
        return new UnmodifiableObservableList<>(filteredTasks);
    }

    @Override
    public void updateFilteredListToShowAll() {
        filteredTasks.setPredicate(null);
    }

    @Override
    public void updateFilteredListToShowCompleted() {
        filteredTasks.setPredicate(ReadOnlyTask->ReadOnlyTask.getIsCompleted());
    }

    @Override
    public void updateFilteredListToShowOngoing() {
        filteredTasks.setPredicate(ReadOnlyTask->!ReadOnlyTask.getIsCompleted());
    }

    @Override
    public void updateFilteredTaskList(Set<String> keywords) {
        updateFilteredTaskList(new PredicateExpression(new TitleAndRemarksQualifier(keywords)));
    }

    private void updateFilteredTaskList(Expression expression) {
        filteredTasks.setPredicate(expression::satisfies);
    }

    @Override
    public void updateFilteredTaskListByLabel(Set<String> keywords) {
        updateFilteredTaskList(new PredicateExpression(new LabelsQualifier(keywords)));
    }



    //========== Inner classes/interfaces used for filtering =================================================

    interface Expression {
        boolean satisfies(ReadOnlyTask task);
        @Override
        String toString();
    }

    private class PredicateExpression implements Expression {

        private final Qualifier qualifier;

        PredicateExpression(Qualifier qualifier) {
            this.qualifier = qualifier;
        }

        @Override
        public boolean satisfies(ReadOnlyTask task) {
            return qualifier.run(task);
        }

        @Override
        public String toString() {
            return qualifier.toString();
        }
    }

    interface Qualifier {
        boolean run(ReadOnlyTask task);
        @Override
        String toString();
    }

    private class TitleAndRemarksQualifier implements Qualifier {
        private Set<String> keyWords;

        TitleAndRemarksQualifier(Set<String> keyWords) {
            this.keyWords = keyWords;
        }

        @Override
        public boolean run(ReadOnlyTask task) {
            if (task.hasRemarks()) {
                return keyWords.stream()
                        .filter(keyword -> StringUtil.containsSubstringIgnoreCase(task.getTitle().fullTitle, keyword))
                        .findAny()
                        .isPresent() ||
                        keyWords.stream()
                        .filter(keyword -> StringUtil.containsSubstringIgnoreCase(task.getRemarks().value, keyword))
                        .findAny()
                        .isPresent();

            }
            return keyWords.stream()
                    .filter(keyword -> StringUtil.containsSubstringIgnoreCase(task.getTitle().fullTitle, keyword))
                    .findAny()
                    .isPresent();
        }

        @Override
        public String toString() {
            return "title and remarks=" + String.join(", ", keyWords);
        }
    }

    private class LabelsQualifier implements Qualifier {
        private Set<String> keyWords;

        LabelsQualifier(Set<String> keyWords) {
            this.keyWords = keyWords;
        }

        @Override
        public boolean run(ReadOnlyTask task) {
            if (!task.getLabels().isEmpty()) {
                return keyWords.stream()
                        .filter(keyword -> StringUtil.containsWordIgnoreCase(task.getLabels()
                                .getStringRepresentation(), keyword))
                        .findAny()
                        .isPresent();
            }
            return false;
        }

        @Override
        public String toString() {
            return "labels=" + String.join(", ", keyWords);
        }
    }

}
