package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.task.TITLE;

public class NameTest {

    @Test
    public void isValidName() {
        // invalid name
        assertFalse(TITLE.isValidTitle("")); // empty string
        assertFalse(TITLE.isValidTitle(" ")); // spaces only
        assertFalse(TITLE.isValidTitle("^")); // only non-alphanumeric characters
        assertFalse(TITLE.isValidTitle("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TITLE.isValidTitle("peter jack")); // alphabets only
        assertTrue(TITLE.isValidTitle("12345")); // numbers only
        assertTrue(TITLE.isValidTitle("peter the 2nd")); // alphanumeric characters
        assertTrue(TITLE.isValidTitle("Capital Tan")); // with capital letters
        assertTrue(TITLE.isValidTitle("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
