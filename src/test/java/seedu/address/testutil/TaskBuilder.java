package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DEADLINE;
import seedu.address.model.task.REMARKS;
import seedu.address.model.task.TITLE;
import seedu.address.model.task.This_attribute_is_not_in_use;
import seedu.address.model.tag.LABELS;

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

    public TaskBuilder withName(String title) throws IllegalValueException {
        this.task.setName(new TITLE(title));
        return this;
    }

    public TaskBuilder withTags(String ... labels) throws IllegalValueException {
        task.setTags(new LABELS());
        for (String label: labels) {
            task.getLabels().add(new Tag(label));
        }
        return this;
    }

    public TaskBuilder withAddress(String attribute_not_in_use) throws IllegalValueException {
        this.task.setAddress(new This_attribute_is_not_in_use(attribute_not_in_use));
        return this;
    }

    public TaskBuilder withPhone(String deadline) throws IllegalValueException {
        this.task.setPhone(new DEADLINE(deadline));
        return this;
    }

    public TaskBuilder withEmail(String remarks) throws IllegalValueException {
        this.task.setEmail(new REMARKS(remarks));
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}
