package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StartTimeTest {

    @Test
    public void isValidAddress() {
        // @@author A0115333U
        // invalid addresses
        assertFalse(StartTime.isValidStartTime("")); // empty string
        assertFalse(StartTime.isValidStartTime(" ")); // spaces only
        assertFalse(StartTime.isValidStartTime("Hello")); // non-numeric
        assertFalse(StartTime.isValidStartTime("1212qwer12")); // alphabets
                                                               // within
        // digits
        // @@author

        // valid start time
        assertTrue(StartTime.isValidStartTime("today 4 o'clock"));
        assertTrue(StartTime.isValidStartTime("12/12/17")); // date
        assertTrue(StartTime.isValidStartTime("2pm")); // just time
    }
}
