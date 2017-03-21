package seedu.address.model.task;

import seedu.address.model.label.UniqueLabelList;

/**
 * A read-only immutable interface for a Task in the Todolist!!.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyTask {

    Title getTitle();
    Deadline getDeadline();
    Remarks getRemarks();
    StartTime getNot_in_use();
    Recurring getRecurring();
	boolean getIsCompleted();

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
                && other.getDeadline().equals(this.getDeadline())
                && other.getRemarks().equals(this.getRemarks())
                && other.getNot_in_use().equals(this.getNot_in_use())
                && other.getRecurring().equals(this.getRecurring()));
    }

    /**
     * Formats the Task as text, showing all task details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Deadline: ")
                .append(getDeadline())
                .append(" Remarks: ")
                .append(getRemarks())
                .append(" Not_in_use: ")
                .append(getNot_in_use())
                .append(" Completion: ")
                .append(getIsCompleted())
                .append(" Labels: ");
        getLabels().forEach(builder::append);
        return builder.toString();
    }

}
