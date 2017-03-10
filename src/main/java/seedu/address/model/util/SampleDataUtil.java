package seedu.address.model.util;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.tag.LABELS;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.REMARKS;
import seedu.address.model.task.TITLE;
import seedu.address.model.task.Task;
import seedu.address.model.task.This_attribute_is_not_in_use;
import seedu.address.model.task.UniquePersonList.DuplicatePersonException;

public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        try {
            return new Task[] {
                new Task(new TITLE("CS2103"), new Deadline("25032017"), new REMARKS("asap"),
                    new This_attribute_is_not_in_use("N/A"),
                    new LABELS("important")),
                new Task(new TITLE("EE4212 CA1"), new Deadline("26032017"), new REMARKS("quality needed"),
                    new This_attribute_is_not_in_use("N/A"),
                    new LABELS("urgent")),
                new Task(new TITLE("EG2401 Tutorial4"), new Deadline("27032017"), new REMARKS("asap"),
                    new This_attribute_is_not_in_use("N/A"),
                    new LABELS("important")),
                new Task(new TITLE("CS2010 MidTerm"), new Deadline("28032017"), new REMARKS("optional"),
                    new This_attribute_is_not_in_use("N/A"),
                    new LABELS("optional")),
                new Task(new TITLE("CS2103"), new Deadline("29032017"), new REMARKS("asap"),
                    new This_attribute_is_not_in_use("N/A"),
                    new LABELS("important")),
                new Task(new TITLE("CS2010 Assignment2"), new Deadline("30032017"), new REMARKS("asap"),
                    new This_attribute_is_not_in_use("N/A"),
                    new LABELS("urgent"))
            };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        try {
            AddressBook sampleAB = new AddressBook();
            for (Task samplePerson : getSampleTasks()) {
                sampleAB.addPerson(samplePerson);
            }
            return sampleAB;
        } catch (DuplicatePersonException e) {
            throw new AssertionError("sample data cannot contain duplicate persons", e);
        }
    }
}
