//@@author A0138831A
package seedu.address.model;


import seedu.address.model.task.ReadOnlyTask;

public class LastSuccessfulAction {

    public ReadOnlyTask task;
    public boolean isAdd;
    public boolean isDelete;
    public boolean isEdit;
    public boolean isClear;

    public LastSuccessfulAction(ReadOnlyTask task, boolean isAdd, boolean isDelete,
            boolean isEdit, boolean isClear) {

        this.task = task;
        this.isAdd = isAdd;
        this.isDelete = isDelete;
        this.isEdit = isEdit;
        this.isClear = isClear;

    }

}
