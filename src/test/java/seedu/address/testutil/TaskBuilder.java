package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.label.UniqueLabelList;
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
    public TaskBuilder(TestTask taskToCopy) {
        this.task = new TestTask(taskToCopy);
    }

    public TaskBuilder withTitle(String title) throws IllegalValueException {
        this.task.setTitle(new Title(title));
        return this;
    }

    public TaskBuilder withLabels(String ... labels) throws IllegalValueException {
        task.setLabels(new UniqueLabelList());
        for (String label: labels) {
            task.getLabels().add(new Label(label));
        }
        return this;
    }

    public TaskBuilder with_attribute_not_in_use(String attribute_not_in_use) throws IllegalValueException {
        this.task.setNotInUse(new This_attribute_is_not_in_use(attribute_not_in_use));
        return this;
    }

    public TaskBuilder withDeadline(String deadline) throws IllegalValueException {
        this.task.setDeadline(new Deadline(deadline));
        return this;
    }

    public TaskBuilder withRemarks(String remarks) throws IllegalValueException {
        this.task.setRemarks(new Remarks(remarks));
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}
