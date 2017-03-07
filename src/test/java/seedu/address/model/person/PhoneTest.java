package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.task.DEADLINE;

public class PhoneTest {

    @Test
    public void isValidPhone() {
        // invalid phone numbers
        assertFalse(DEADLINE.isValidDeadline("")); // empty string
        assertFalse(DEADLINE.isValidDeadline(" ")); // spaces only
        assertFalse(DEADLINE.isValidDeadline("phone")); // non-numeric
        assertFalse(DEADLINE.isValidDeadline("9011p041")); // alphabets within digits
        assertFalse(DEADLINE.isValidDeadline("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(DEADLINE.isValidDeadline("93121534"));
        assertTrue(DEADLINE.isValidDeadline("4")); // short phone numbers
        assertTrue(DEADLINE.isValidDeadline("124293842033123")); // long phone numbers
    }
}
