package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.task.Deadline;

public class DeadlineTest {

    @Test
    public void isValidPhone() {
        // invalid deadines
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("phone")); // non-numeric
        assertFalse(Deadline.isValidDeadline("9011p041")); // alphabets within digits
        assertFalse(Deadline.isValidDeadline("9312 1534")); // spaces within digits

        // valid deadlines
        assertTrue(Deadline.isValidDeadline("090917"));
       
        
    }
}
