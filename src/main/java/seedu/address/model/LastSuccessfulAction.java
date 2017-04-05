//@@author A0138831A
package seedu.address.model;

import seedu.address.model.task.ReadOnlyTask;



public class LastSuccessfulAction {

    public ReadOnlyTask task;
    public TaskType tasktype;

    public LastSuccessfulAction(ReadOnlyTask task, TaskType tasktype) {

        this.task = task;
        this.tasktype = tasktype;

    }

}
