package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.label.LABELS;
import seedu.address.model.label.Label;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.Title;
import seedu.address.model.task.This_attribute_is_not_in_use;

/**
 *
 */
public class TaskBuilder {

    private TestTask task;

    public TaskBuilder() {
        this.task = new TestTask();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public TaskBuilder(TestTask personToCopy) {
        this.task = new TestTask(personToCopy);
    }

    public TaskBuilder withTitle(String title) throws IllegalValueException {
        this.task.setName(new Title(title));
        return this;
    }

    public TaskBuilder withLabels(String ... labels) throws IllegalValueException {
        task.setTags(new LABELS());
        for (String label: labels) {
            task.getLabels().add(new Label(label));
        }
        return this;
    }

    public TaskBuilder with_attribute_not_in_use(String attribute_not_in_use) throws IllegalValueException {
        this.task.setAddress(new This_attribute_is_not_in_use(attribute_not_in_use));
        return this;
    }

    public TaskBuilder withDeadline(String deadline) throws IllegalValueException {
        this.task.setPhone(new Deadline(deadline));
        return this;
    }

    public TaskBuilder withRemarks(String remarks) throws IllegalValueException {
        this.task.setEmail(new Remarks(remarks));
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}
