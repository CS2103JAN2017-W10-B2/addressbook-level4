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

    public TaskBuilder withName(String name) throws IllegalValueException {
        this.task.setName(new TITLE(name));
        return this;
    }

    public TaskBuilder withTags(String ... tags) throws IllegalValueException {
        task.setTags(new LABELS());
        for (String tag: tags) {
            task.getLabels().add(new Tag(tag));
        }
        return this;
    }

    public TaskBuilder withAddress(String address) throws IllegalValueException {
        this.task.setAddress(new This_attribute_is_not_in_use(address));
        return this;
    }

    public TaskBuilder withPhone(String phone) throws IllegalValueException {
        this.task.setPhone(new DEADLINE(phone));
        return this;
    }

    public TaskBuilder withEmail(String email) throws IllegalValueException {
        this.task.setEmail(new REMARKS(email));
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}
