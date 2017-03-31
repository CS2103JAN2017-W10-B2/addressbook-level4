//@@author A0138831A
package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DeadlineTest {

    @Test
    public void isValidDeadline() {
        // invalid deadines
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("phone")); // non-numeric
        assertFalse(Deadline.isValidDeadline("9011p041")); // alphabets within
                                                           // digits
        // assertFalse(Deadline.isValidDeadline("9312 1534")); // spaces within
        // digits

        // valid deadlines
        assertTrue(Deadline.isValidDeadline("090917"));

    }
}
