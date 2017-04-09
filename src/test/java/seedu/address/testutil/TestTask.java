package seedu.address.testutil;

import seedu.address.model.label.UniqueLabelList;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Title;

/**
 * A mutable person object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Title title;
    private StartTime startTime;
    private Remarks remarks;
    private Deadline deadline;
    private UniqueLabelList labels;
    private boolean isCompleted;

    public TestTask() {
        labels = new UniqueLabelList();
    }

    /**
     * Creates a copy of {@code personToCopy}.
     */
    public TestTask(TestTask taskToCopy) {
        this.title = taskToCopy.getTitle();
        this.deadline = taskToCopy.getDeadline();
        this.remarks = taskToCopy.getRemarks();
        this.startTime = taskToCopy.getStartTime();
        this.labels = taskToCopy.getLabels();
        this.isCompleted = taskToCopy.getIsCompleted();
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setNotInUse(StartTime startTime) {
        this.startTime = startTime;
    }

    public void setRemarks(Remarks remarks) {
        this.remarks = remarks;
    }

    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    public void setLabels(UniqueLabelList labels) {
        this.labels = labels;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public Title getTitle() {
        return title;
    }

    @Override
    public Deadline getDeadline() {
        return deadline;
    }

    @Override
    public Remarks getRemarks() {
        return remarks;
    }

    @Override
    public StartTime getStartTime() {
        return startTime;
    }

    @Override
    public UniqueLabelList getLabels() {
        return labels;
    }

    @Override
    public boolean getIsCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getTitle().fullTitle + " ");
        sb.append("from:" + this.getStartTime().value + " ");
        sb.append("till:" + this.getDeadline().value + " ");
        sb.append("remark:" + this.getRemarks().value + " ");
        this.getLabels().asObservableList().stream().forEach(s -> sb.append("#" + s.labelName + " "));
        return sb.toString();
    }
}
