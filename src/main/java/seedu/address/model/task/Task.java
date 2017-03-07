package seedu.address.model.task;

import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.LABELS;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private TITLE title;
    private DEADLINE deadline;
    private REMARKS remarks;
    private This_attribute_is_not_in_use not_in_use;

    private LABELS labels;

    /**
     * Every field must be present and not null.
     */
    public Task(TITLE title, DEADLINE deadline, REMARKS remarks, This_attribute_is_not_in_use not_in_use, LABELS labels) {
        assert !CollectionUtil.isAnyNull(title, deadline, remarks, not_in_use, labels);
        this.title = title;
        this.deadline = deadline;
        this.remarks = remarks;
        this.not_in_use = not_in_use;
        this.labels = new LABELS(labels); // protect internal tags from changes in the arg list
    }

    /**
     * Creates a copy of the given ReadOnlyTask.
     */
    public Task(ReadOnlyTask source) {
        this(source.getTitle(), source.getDeadline(), source.getRemarks(), source.getNot_in_use(), source.getLabels());
    }

    public void setTitle(TITLE title) {
        assert title != null;
        this.title = title;
    }

    @Override
    public TITLE getTitle() {
        return title;
    }

    public void setDeadline(DEADLINE deadline) {
        assert deadline != null;
        this.deadline = deadline;
    }

    @Override
    public DEADLINE getDeadline() {
        return deadline;
    }

    public void setRemarks(REMARKS remark) {
        assert remark != null;
        this.remarks = remark;
    }

    @Override
    public REMARKS getRemarks() {
        return remarks;
    }

    public void setNot_in_use(This_attribute_is_not_in_use not_in_use) {
        assert not_in_use != null;
        this.not_in_use = not_in_use;
    }

    @Override
    public This_attribute_is_not_in_use getNot_in_use() {
        return not_in_use;
    }

    @Override
    public LABELS getLabels() {
        return new LABELS(labels);
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setLabels(LABELS replacement) {
        labels.setLabels(replacement);
    }

    /**
     * Updates this task with the details of {@code replacement}.
     */
    public void resetData(ReadOnlyTask replacement) {
        assert replacement != null;

        this.setTitle(replacement.getTitle());
        this.setDeadline(replacement.getDeadline());
        this.setRemarks(replacement.getRemarks());
        this.setNot_in_use(replacement.getNot_in_use());
        this.setLabels(replacement.getLabels());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instance of handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, deadline, remarks, not_in_use, labels);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
