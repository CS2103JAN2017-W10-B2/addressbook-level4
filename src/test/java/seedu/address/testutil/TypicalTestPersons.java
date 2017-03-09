package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniquePersonList;

/**
 *
 */
public class TypicalTestPersons {

    public TestTask alice, benson, carl, daniel, elle, fiona, george, hoon, ida;

    public TypicalTestPersons() {
        try {
            alice = new TaskBuilder().withTitle("Alice Pauline")
                    .with_attribute_not_in_use("123, Jurong West Ave 6, #08-111").withRemarks("alice@gmail.com")
                    .withDeadline("85355255")
                    .withLabels("friends").build();
            benson = new TaskBuilder().withTitle("Benson Meier").with_attribute_not_in_use("311, Clementi Ave 2, #02-25")
                    .withRemarks("johnd@gmail.com").withDeadline("98765432")
                    .withLabels("owesMoney", "friends").build();
            carl = new TaskBuilder().withTitle("Carl Kurz").withDeadline("95352563")
                    .withRemarks("heinz@yahoo.com").with_attribute_not_in_use("wall street").build();
            daniel = new TaskBuilder().withTitle("Daniel Meier").withDeadline("87652533")
                    .withRemarks("cornelia@google.com").with_attribute_not_in_use("10th street").build();
            elle = new TaskBuilder().withTitle("Elle Meyer").withDeadline("9482224")
                    .withRemarks("werner@gmail.com").with_attribute_not_in_use("michegan ave").build();
            fiona = new TaskBuilder().withTitle("Fiona Kunz").withDeadline("9482427")
                    .withRemarks("lydia@gmail.com").with_attribute_not_in_use("little tokyo").build();
            george = new TaskBuilder().withTitle("George Best").withDeadline("9482442")
                    .withRemarks("anna@google.com").with_attribute_not_in_use("4th street").build();

            // Manually added
            hoon = new TaskBuilder().withTitle("Hoon Meier").withDeadline("8482424")
                    .withRemarks("stefan@mail.com").with_attribute_not_in_use("little india").build();
            ida = new TaskBuilder().withTitle("Ida Mueller").withDeadline("8482131")
                    .withRemarks("hans@google.com").with_attribute_not_in_use("chicago ave").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadAddressBookWithSampleData(AddressBook ab) {
        for (TestTask person : new TypicalTestPersons().getTypicalPersons()) {
            try {
                ab.addPerson(new Task(person));
            } catch (UniquePersonList.DuplicatePersonException e) {
                assert false : "not possible";
            }
        }
    }

    public TestTask[] getTypicalPersons() {
        return new TestTask[]{alice, benson, carl, daniel, elle, fiona, george};
    }

    public AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        loadAddressBookWithSampleData(ab);
        return ab;
    }
}
