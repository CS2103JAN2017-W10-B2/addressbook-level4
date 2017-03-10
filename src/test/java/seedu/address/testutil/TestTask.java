package seedu.address.testutil;

import seedu.address.model.label.LABELS;
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
    private LABELS labels;

    public TestTask() {
        labels = new LABELS();
    }

    /**
     * Creates a copy of {@code personToCopy}.
     */
    public TestTask(TestTask personToCopy) {
        this.title = personToCopy.getTitle();
        this.deadline = personToCopy.getDeadline();
        this.remarks = personToCopy.getRemarks();
        this.attribute_not_in_use = personToCopy.getNot_in_use();
        this.labels = personToCopy.getLabels();
    }

    public void setName(Title name) {
        this.title = name;
    }

    public void setAddress(This_attribute_is_not_in_use address) {
        this.attribute_not_in_use = address;
    }

    public void setEmail(Remarks email) {
        this.remarks = email;
    }

    public void setPhone(Deadline phone) {
        this.deadline = phone;
    }

    public void setTags(LABELS tags) {
        this.labels = tags;
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
    public LABELS getLabels() {
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
        this.getLabels().asObservableList().stream().forEach(s -> sb.append("l/" + s.tagName + " "));
        return sb.toString();
    }
}
