package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.task.Title;

public class TitleTest {

    @Test
    public void isValidTitle() {
        // invalid name
        assertFalse(Title.isValidTitle("")); // empty string
        assertFalse(Title.isValidTitle(" ")); // spaces only
        assertFalse(Title.isValidTitle("^")); // only non-alphanumeric characters
        assertFalse(Title.isValidTitle("doit*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Title.isValidTitle("past year paper")); // alphabets only
        assertTrue(Title.isValidTitle("12345")); // numbers only
        assertTrue(Title.isValidTitle("2nd week tutorial")); // alphanumeric characters
        assertTrue(Title.isValidTitle("Exercise three")); // with capital letters
        assertTrue(Title.isValidTitle("Chapter three practice and past year paper")); // long names
    }
}
