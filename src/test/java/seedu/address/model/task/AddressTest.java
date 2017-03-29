package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AddressTest {

    @Test
    public void isValidAddress() {
        // invalid addresses
        assertFalse(StartTime.isValidStartTime("")); // empty string
        assertFalse(StartTime.isValidStartTime(" ")); // spaces only

        // valid addresses
        assertTrue(StartTime.isValidStartTime("Blk 456, Den Road, #01-355"));
        assertTrue(StartTime.isValidStartTime("-")); // one character
        assertTrue(StartTime.isValidStartTime("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
    }
}
