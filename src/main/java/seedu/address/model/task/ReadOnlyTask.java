package seedu.address.model.task;

import seedu.address.model.label.LABELS;

/**
 * A read-only immutable interface for a Task in the doitdoit!!.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyTask {

    Title getTitle();
    Deadline getDeadline();
    Remarks getRemarks();
    This_attribute_is_not_in_use getNot_in_use();

    /**
     * The returned TagList is a deep copy of the internal TagList,
     * changes on the returned list will not affect the person's internal tags.
     */
    LABELS getLabels();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getTitle().equals(this.getTitle()) // state checks here onwards
                && other.getDeadline().equals(this.getDeadline())
                && other.getRemarks().equals(this.getRemarks())
                && other.getNot_in_use().equals(this.getNot_in_use()));
    }

    /**
     * Formats the Task as text, showing all contact details.
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
                .append(" Labels: ");
        getLabels().forEach(builder::append);
        return builder.toString();
    }

}
