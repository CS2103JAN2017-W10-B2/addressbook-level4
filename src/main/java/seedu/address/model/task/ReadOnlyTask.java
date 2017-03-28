//@@author A0143132X
package seedu.address.model.task;

import seedu.address.model.label.UniqueLabelList;

/**
 * A read-only immutable interface for a Task in the Todolist!!.
 * Implementations should guarantee: title is present and not null, field values are validated.
 */
public interface ReadOnlyTask {

    Title getTitle();
    Deadline getDeadline();
    Remarks getRemarks();
    StartTime getStartTime();
	boolean getIsCompleted();
	
	
	default boolean hasDeadline() {
        return getDeadline() != null;
    }
	
	default boolean hasRemarks() {
        return getRemarks() != null;
    }
	
	default boolean hasStartTime() {
        return getStartTime() != null;
    }

    /**
     * The returned LabelList is a deep copy of the internal LabelList,
     * changes on the returned list will not affect the task's internal labels.
     */
    UniqueLabelList getLabels();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getTitle().equals(this.getTitle()) // state checks here onwards
                && ((!other.hasDeadline() && !this.hasDeadline()) 
                        || (other.hasDeadline() && this.hasDeadline() 
                                && other.getDeadline().equals(this.getDeadline())))
                && ((!other.hasRemarks() && !this.hasRemarks()) 
                        || (other.hasRemarks() && this.hasRemarks() 
                                && other.getRemarks().equals(this.getRemarks())))
                && ((!other.hasStartTime() && !this.hasStartTime()) 
                        || (other.hasStartTime() && this.hasStartTime() 
                                && other.getStartTime().equals(this.getStartTime()))));
    }

    /**
     * Formats the Task as text, showing all task details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle());
        if (hasStartTime()){
            builder.append(" Start Time: ")
                   .append(getStartTime());
        }
        if (hasDeadline()){
            builder.append(" Deadline: ")
                   .append(getDeadline());
        }
        if (hasRemarks()){
            builder.append(" Remarks: ")
                   .append(getRemarks());
        }
        if (getIsCompleted()){
            builder.append(" Completion: ")
                   .append(getIsCompleted());
        }
        if (!getLabels().isEmpty()){
            builder.append(" Labels: ");
            getLabels().forEach(builder::append);
        }
        return builder.toString();
    }

}
