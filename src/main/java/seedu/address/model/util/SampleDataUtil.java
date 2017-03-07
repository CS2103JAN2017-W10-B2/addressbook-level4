package seedu.address.model.util;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.tag.LABELS;
import seedu.address.model.task.DEADLINE;
import seedu.address.model.task.REMARKS;
import seedu.address.model.task.TITLE;
import seedu.address.model.task.Task;
import seedu.address.model.task.This_attribute_is_not_in_use;
import seedu.address.model.task.UniquePersonList.DuplicatePersonException;

public class SampleDataUtil {
    public static Task[] getSamplePersons() {
        try {
            return new Task[] {
                new Task(new TITLE("Alex Yeoh"), new DEADLINE("87438807"), new REMARKS("alexyeoh@gmail.com"),
                    new This_attribute_is_not_in_use("Blk 30 Geylang Street 29, #06-40"),
                    new LABELS("friends")),
                new Task(new TITLE("Bernice Yu"), new DEADLINE("99272758"), new REMARKS("berniceyu@gmail.com"),
                    new This_attribute_is_not_in_use("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new LABELS("colleagues", "friends")),
                new Task(new TITLE("Charlotte Oliveiro"), new DEADLINE("93210283"), new REMARKS("charlotte@yahoo.com"),
                    new This_attribute_is_not_in_use("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new LABELS("neighbours")),
                new Task(new TITLE("David Li"), new DEADLINE("91031282"), new REMARKS("lidavid@google.com"),
                    new This_attribute_is_not_in_use("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new LABELS("family")),
                new Task(new TITLE("Irfan Ibrahim"), new DEADLINE("92492021"), new REMARKS("irfan@outlook.com"),
                    new This_attribute_is_not_in_use("Blk 47 Tampines Street 20, #17-35"),
                    new LABELS("classmates")),
                new Task(new TITLE("Roy Balakrishnan"), new DEADLINE("92624417"), new REMARKS("royb@gmail.com"),
                    new This_attribute_is_not_in_use("Blk 45 Aljunied Street 85, #11-31"),
                    new LABELS("colleagues"))
            };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        try {
            AddressBook sampleAB = new AddressBook();
            for (Task samplePerson : getSamplePersons()) {
                sampleAB.addPerson(samplePerson);
            }
            return sampleAB;
        } catch (DuplicatePersonException e) {
            throw new AssertionError("sample data cannot contain duplicate persons", e);
        }
    }
}
