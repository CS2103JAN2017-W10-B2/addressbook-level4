package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StartTimeTest {

    @Test
    public void isValidAddress() {
        // invalid addresses
        assertFalse(StartTime.isValidStartTime("")); // empty string
        assertFalse(StartTime.isValidStartTime(" ")); // spaces only

        // valid addresses
        assertTrue(StartTime.isValidStartTime("today 4 o'clock"));
        assertTrue(StartTime.isValidStartTime("12/12/17")); // date
        assertTrue(StartTime.isValidStartTime("2pm")); //just time
    }
}
