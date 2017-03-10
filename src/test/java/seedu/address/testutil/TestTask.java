package seedu.address.testutil;

import seedu.address.model.label.UniqueLabelList;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Title;
import seedu.address.model.task.This_attribute_is_not_in_use;

/**
 * A mutable person object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Title title;
    private This_attribute_is_not_in_use attribute_not_in_use;
    private Remarks remarks;
    private Deadline deadline;
    private UniqueLabelList labels;

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
        this.attribute_not_in_use = taskToCopy.getNot_in_use();
        this.labels = taskToCopy.getLabels();
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setNotInUse(This_attribute_is_not_in_use attribute_not_in_use) {
        this.attribute_not_in_use = attribute_not_in_use;
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
    public This_attribute_is_not_in_use getNot_in_use() {
        return attribute_not_in_use;
    }

    @Override
    public UniqueLabelList getLabels() {
        return labels;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getTitle().fullTitle + " ");
        sb.append("n/" + this.getNot_in_use().value + " ");
        sb.append("d/" + this.getDeadline().value + " ");
        sb.append("r/" + this.getRemarks().value + " ");
        this.getLabels().asObservableList().stream().forEach(s -> sb.append("l/" + s.labelName + " "));
        return sb.toString();
    }
}
