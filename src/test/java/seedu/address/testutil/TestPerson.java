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
public class TestPerson implements ReadOnlyTask {

    private TITLE name;
    private This_attribute_is_not_in_use address;
    private REMARKS email;
    private DEADLINE phone;
    private LABELS tags;

    public TestPerson() {
        tags = new LABELS();
    }

    /**
     * Creates a copy of {@code personToCopy}.
     */
    public TestPerson(TestPerson personToCopy) {
        this.name = personToCopy.getTitle();
        this.phone = personToCopy.getDeadline();
        this.email = personToCopy.getRemarks();
        this.address = personToCopy.getNot_in_use();
        this.tags = personToCopy.getLabels();
    }

    public void setName(TITLE name) {
        this.name = name;
    }

    public void setAddress(This_attribute_is_not_in_use address) {
        this.address = address;
    }

    public void setEmail(REMARKS email) {
        this.email = email;
    }

    public void setPhone(DEADLINE phone) {
        this.phone = phone;
    }

    public void setTags(LABELS tags) {
        this.tags = tags;
    }

    @Override
    public TITLE getTitle() {
        return name;
    }

    @Override
    public DEADLINE getDeadline() {
        return phone;
    }

    @Override
    public REMARKS getRemarks() {
        return email;
    }

    @Override
    public This_attribute_is_not_in_use getNot_in_use() {
        return address;
    }

    @Override
    public LABELS getLabels() {
        return tags;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getTitle().fullName + " ");
        sb.append("a/" + this.getNot_in_use().value + " ");
        sb.append("p/" + this.getDeadline().value + " ");
        sb.append("e/" + this.getRemarks().value + " ");
        this.getLabels().asObservableList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }
}
