package seedu.address.model.task;

import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.label.UniqueLabelList;

/**
 * Represents a Task in the doitdoit!!.
 * Guarantees: title is present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private Title title;
    private Deadline deadline;
    private Remarks remarks;
    private StartTime startTime;
	private Boolean isCompleted;

    private UniqueLabelList labels;

    /**
     * Every field must be present and not null.
     * @param isCompleted TODO
     */
    public Task(Title title, Deadline deadline, Remarks remarks, StartTime startTime, UniqueLabelList labels, Boolean isCompleted) {
        assert title != null;
        this.title = title;
        this.deadline = deadline;
        this.remarks = remarks;
        this.startTime = startTime;
        this.isCompleted = isCompleted;
        this.labels = new UniqueLabelList(labels); // protect internal tags from changes in the arg list
    }

    /**
     * Creates a copy of the given ReadOnlyTask.
     */
    public Task(ReadOnlyTask source) {
        this(source.getTitle(), source.getDeadline(), source.getRemarks(), source.getStartTime(), source.getLabels(),
        	source.getIsCompleted());
    }

    public void setTitle(Title title) {
        assert title != null;
        this.title = title;
    }

    @Override
    public Title getTitle() {
        return title;
    }

    public void setDeadline(Deadline deadline) {
        assert deadline != null;
        this.deadline = deadline;
    }

    @Override
    public Deadline getDeadline() {
        return deadline;
    }

    public void setRemarks(Remarks remark) {
        assert remark != null;
        this.remarks = remark;
    }

    @Override
    public Remarks getRemarks() {
        return remarks;
    }

    public void setStartTime(StartTime startTime) {
        assert startTime != null;
        this.startTime = startTime;
    }

    @Override
    public StartTime getStartTime() {
        return startTime;
    }
    
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public boolean getIsCompleted() {
        return isCompleted;
    }

    @Override
    public UniqueLabelList getLabels() {
        return new UniqueLabelList(labels);
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setLabels(UniqueLabelList replacement) {
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
        this.setStartTime(replacement.getStartTime());
        this.setLabels(replacement.getLabels());
        this.setIsCompleted(replacement.getIsCompleted());
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
        return Objects.hash(title, deadline, remarks, startTime, labels);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
