package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RemarksTest {

    @Test
    public void isValidRemarks() {
        // blank email
        //assertFalse(Remarks.isValidRemarks("")); // empty string
        assertFalse(Remarks.isValidRemarks(" ")); // spaces only




        // valid remarks
        assertTrue(Remarks.isValidRemarks("refer to textbook"));
        assertTrue(Remarks.isValidRemarks("9"));  // minimal
        assertTrue(Remarks.isValidRemarks("refer to chapter 9"));
        assertTrue(Remarks.isValidRemarks("P@$$w0rd"));  // numeric local part and domain name
        assertTrue(Remarks.isValidRemarks("a1@sg50.org"));  // mixture of alphanumeric and dot characters
        assertTrue(Remarks.isValidRemarks("_user_@_do_main_.com_"));    // underscores
        assertTrue(Remarks.isValidRemarks("peter & jack wiil take a_very_long time to go to the market"));   // long

    }
}
