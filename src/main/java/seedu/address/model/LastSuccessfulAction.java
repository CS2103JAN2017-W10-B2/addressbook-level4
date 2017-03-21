package seedu.address.model;


import seedu.address.model.task.Task;

public class LastSuccessfulAction {
	
	public Task task;
	public boolean isAdd;
	public boolean isDelete;
	public boolean isEdit;
	public boolean isClear;
	
	public LastSuccessfulAction(Task task, boolean isAdd, boolean isDelete, 
			boolean isEdit, boolean isClear ){
		
		this.task = task;
		this.isAdd = isAdd;
		this.isDelete = isDelete;
		this.isEdit = isEdit;
		this.isClear = isClear;
		
	}

}
