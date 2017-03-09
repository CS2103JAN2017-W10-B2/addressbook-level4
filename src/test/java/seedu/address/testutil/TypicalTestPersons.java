package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniquePersonList;

/**
 *
 */
public class TypicalTestPersons {

    public TestTask testExample1, testExample2, testExample3, testExample4, testExample5, testExample6, testExample7, testExample8, testExample9;

    public TypicalTestPersons() {
        try {
            testExample1 = new TaskBuilder().withTitle("JUnit Test 1")
                    .with_attribute_not_in_use("temp")
                    .withRemarks("alice@gmail.com")
                    .withDeadline("85355255")
                    .withLabels("friends").build();
            testExample2 = new TaskBuilder().withTitle("JUnit Test 2")
            		.with_attribute_not_in_use("temp")
                    .withRemarks("johnd@gmail.com")
                    .withDeadline("98765432")
                    .withLabels("owesMoney", "friends").build();
            testExample3 = new TaskBuilder().withTitle("JUnit Test 3")
            		.withDeadline("95352563")
                    .withRemarks("heinz@yahoo.com")
                    .with_attribute_not_in_use("temp").build();
            testExample4 = new TaskBuilder().withTitle("JUnit Test 4")
            		.withDeadline("87652533")
                    .withRemarks("cornelia@google.com").with_attribute_not_in_use("temp").build();
            testExample5 = new TaskBuilder().withTitle("JUnit Test 5")
            		.withDeadline("9482224")
                    .withRemarks("werner@gmail.com")
                    .with_attribute_not_in_use("temp").build();
            testExample6 = new TaskBuilder().withTitle("JUnit Test 6")
            		.withDeadline("9482427")
                    .withRemarks("lydia@gmail.com")
                    .with_attribute_not_in_use("temp").build();
            testExample7 = new TaskBuilder().withTitle("JUnit Test 7")
            		.withDeadline("9482442")
                    .withRemarks("anna@google.com")
                    .with_attribute_not_in_use("temp").build();
            testExample8 = new TaskBuilder().withTitle("JUnit Test 8")
            		.withDeadline("8482424")
                    .withRemarks("stefan@mail.com")
                    .with_attribute_not_in_use("temp").build();
            testExample9 = new TaskBuilder().withTitle("JUnit Test 9")
            		.withDeadline("8482131")
                    .withRemarks("hans@google.com")
                    .with_attribute_not_in_use("temp").build();
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
        return new TestTask[]{testExample1, testExample2, testExample3, testExample4, testExample5, testExample6, testExample7};
    }

    public AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        loadAddressBookWithSampleData(ab);
        return ab;
    }
}
