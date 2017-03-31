//@@author A0135795R
package seedu.address.commons.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TimeUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isInvalidDateTime() {
        //invalid DateTime
        assertNull(TimeUtil.getDateTime("CS2103 tutorial"));
        assertNull(TimeUtil.getDateTime("2400"));
        assertNull(TimeUtil.getDateTime("0060"));

        //unaccepted DateTime
        assertNull(TimeUtil.getDateTime("mom's birthday"));
        assertNull(TimeUtil.getDateTime("that day"));
        assertNull(TimeUtil.getDateTime("today or tomorrow"));

        //get DateTime wrong
        assertFalse(LocalDateTime.of(2016, 4, 3, 16, 00).isEqual(TimeUtil.getDateTime("03/04/2016 4:00pm")));
        assertFalse(LocalDateTime.of(2016, 3, 3, 16, 00).isEqual(TimeUtil.getDateTime("030316 4pm")));
    }

    @Test
    public void isValidDateTime() {
        //acceptable DateTime
        assertNotNull(TimeUtil.getDateTime("Wednesday"));
        assertNotNull(TimeUtil.getDateTime("ThUrSDay"));
        assertNotNull(TimeUtil.getDateTime("day after tomorrow"));
        assertNotNull(TimeUtil.getDateTime("day before next thursday"));
        assertNotNull(TimeUtil.getDateTime("4 o'clock"));

        //get DateTime correct
        assertTrue(LocalDateTime.of(2017, 4, 1, 16, 49).isEqual(TimeUtil.getDateTime("first day of april 4:49pm")));
    }

}
