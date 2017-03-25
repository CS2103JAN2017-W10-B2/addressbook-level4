//@@author A0115333U
package seedu.address.commons.util;

import java.util.Comparator;

import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Deadline;
import seedu.address.commons.util.TimeUtil;

/**
 * Comparator function for sorting by date.
 */
public class SortUtil implements Comparator<ReadOnlyTask> {
	  @Override
	  public int compare(ReadOnlyTask x, ReadOnlyTask y) {
	    assert x!=null;
	    assert y!=null;
	    return compare(x.getDeadline(), y.getDeadline());
	  }

	  private static int compare(Deadline a, Deadline b) {
	    return TimeUtil.getDateTime(a.toString()).isBefore(TimeUtil.getDateTime(b.toString()))  ? -1
	         : TimeUtil.getDateTime(a.toString()).isAfter(TimeUtil.getDateTime(b.toString()))  ? 1
	         : 0;
	  }
	}


/*
toDoList.getTaskList().sort(new Comparator<ReadOnlyTask>(){
	public int compare(ReadOnlyTask x, ReadOnlyTask y) {
	    return compare(x.getDeadline(), y.getDeadline());
	  }

	  private int compare(Deadline a, Deadline b) {
		  if (a!=null && b!=null){
			  return TimeUtil.getDateTime(a.toString()).isBefore(TimeUtil.getDateTime(b.toString()))  ? -1
					: TimeUtil.getDateTime(a.toString()).isAfter(TimeUtil.getDateTime(b.toString()))  ? 1
					: 0;
		  }
		  else{
			  return 0;
		  }
	  }
});*/