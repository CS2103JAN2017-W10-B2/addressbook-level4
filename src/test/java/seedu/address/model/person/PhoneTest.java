package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.task.DEADLINE;

public class PhoneTest {

    @Test
    public void isValidPhone() {
        // invalid phone numbers
        assertFalse(DEADLINE.isValidPhone("")); // empty string
        assertFalse(DEADLINE.isValidPhone(" ")); // spaces only
        assertFalse(DEADLINE.isValidPhone("phone")); // non-numeric
        assertFalse(DEADLINE.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(DEADLINE.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(DEADLINE.isValidPhone("93121534"));
        assertTrue(DEADLINE.isValidPhone("4")); // short phone numbers
        assertTrue(DEADLINE.isValidPhone("124293842033123")); // long phone numbers
    }
}
