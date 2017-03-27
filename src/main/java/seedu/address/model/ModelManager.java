package seedu.address.model;

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

    /**
     * Initializes a ModelManager with the given doitdoit!! and userPrefs.
     */
    public ModelManager(ReadOnlyToDoList toDoList, UserPrefs userPrefs) {
        super();
        assert !CollectionUtil.isAnyNull(toDoList, userPrefs);

        logger.fine("Initializing with to do list: " + toDoList + " and user prefs " + userPrefs);

        this.toDoList = new ToDoList(toDoList);
        filteredTasks = new FilteredList<>(this.toDoList.getTaskList());
        undoStack = new Stack<LastSuccessfulAction>();
    }

    public ModelManager() {
        this(new ToDoList(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyToDoList newData) {
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
        updateFilteredListToShowAll();
        indicateToDoListChanged();
    }

    @Override
    public void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask)
            throws UniqueTaskList.DuplicateTaskException {
        assert editedTask != null;

        int toDoListIndex = filteredTasks.getSourceIndex(filteredTaskListIndex);
        toDoList.updateTask(toDoListIndex, editedTask);
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

    //========== Inner classes/interfaces used for filtering =================================================

    interface Expression {
        boolean satisfies(ReadOnlyTask task);
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
        String toString();
    }

    private class TitleAndRemarksQualifier implements Qualifier {
        private Set<String> titleKeyWords;

        TitleAndRemarksQualifier(Set<String> nameKeyWords) {
            this.titleKeyWords = nameKeyWords;
        }

        @Override
        public boolean run(ReadOnlyTask task) {
            if (task.hasRemarks()){
                return titleKeyWords.stream()
                        .filter(keyword -> StringUtil.containsSubstringIgnoreCase(task.getTitle().fullTitle, keyword))
                        .findAny()
                        .isPresent() ||
                        titleKeyWords.stream()
                        .filter(keyword -> StringUtil.containsSubstringIgnoreCase(task.getRemarks().value, keyword))
                        .findAny()
                        .isPresent();
                
            }
            return titleKeyWords.stream()
                    .filter(keyword -> StringUtil.containsSubstringIgnoreCase(task.getTitle().fullTitle, keyword))
                    .findAny()
                    .isPresent();
        }

        @Override
        public String toString() {
            return "title=" + String.join(", ", titleKeyWords);
        }
    }

	@Override
	public void undoTask() {
		LastSuccessfulAction lsa = undoStack.pop();
		if(lsa.isAdd){
			try {
				deleteTask(lsa.task);
			} catch (TaskNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(lsa.isDelete){
			try {
				addTask((Task) lsa.task);
			} catch (DuplicateTaskException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
