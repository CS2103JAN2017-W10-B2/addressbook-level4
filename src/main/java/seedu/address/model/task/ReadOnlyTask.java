package seedu.address.model.task;

import seedu.address.model.tag.LABELS;

/**
 * A read-only immutable interface for a Person in the addressbook.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyTask {

    TITLE getTitle();
    DEADLINE getDeadline();
    REMARKS getRemarks();
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
     * Formats the person as text, showing all contact details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Phone: ")
                .append(getDeadline())
                .append(" Email: ")
                .append(getRemarks())
                .append(" Address: ")
                .append(getNot_in_use())
                .append(" Tags: ");
        getLabels().forEach(builder::append);
        return builder.toString();
    }

}