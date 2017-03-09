package seedu.address.testutil;

import seedu.address.model.tag.LABELS;
import seedu.address.model.task.DEADLINE;
import seedu.address.model.task.REMARKS;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.TITLE;
import seedu.address.model.task.This_attribute_is_not_in_use;

/**
 * A mutable person object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private TITLE title;
    private This_attribute_is_not_in_use attribute_not_in_use;
    private REMARKS remarks;
    private DEADLINE deadline;
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

    public void setName(TITLE name) {
        this.title = name;
    }

    public void setAddress(This_attribute_is_not_in_use address) {
        this.attribute_not_in_use = address;
    }

    public void setEmail(REMARKS email) {
        this.remarks = email;
    }

    public void setPhone(DEADLINE phone) {
        this.deadline = phone;
    }

    public void setTags(LABELS tags) {
        this.labels = tags;
    }

    @Override
    public TITLE getTitle() {
        return title;
    }

    @Override
    public DEADLINE getDeadline() {
        return deadline;
    }

    @Override
    public REMARKS getRemarks() {
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
