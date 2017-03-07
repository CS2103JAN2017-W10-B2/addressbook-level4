package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.task.TITLE;

public class NameTest {

    @Test
    public void isValidName() {
        // invalid name
        assertFalse(TITLE.isValidName("")); // empty string
        assertFalse(TITLE.isValidName(" ")); // spaces only
        assertFalse(TITLE.isValidName("^")); // only non-alphanumeric characters
        assertFalse(TITLE.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TITLE.isValidName("peter jack")); // alphabets only
        assertTrue(TITLE.isValidName("12345")); // numbers only
        assertTrue(TITLE.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(TITLE.isValidName("Capital Tan")); // with capital letters
        assertTrue(TITLE.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
