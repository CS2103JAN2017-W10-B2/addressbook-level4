package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.task.Remarks;

public class EmailTest {

    @Test
    public void isValidEmail() {
        // blank email
        assertFalse(Remarks.isValidRemarks("")); // empty string
        assertFalse(Remarks.isValidRemarks(" ")); // spaces only

        // missing parts
        assertFalse(Remarks.isValidRemarks("@webmail.com")); // missing local part
        assertFalse(Remarks.isValidRemarks("peterjackwebmail.com")); // missing '@' symbol
        assertFalse(Remarks.isValidRemarks("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Remarks.isValidRemarks("-@webmail.com")); // invalid local part
        assertFalse(Remarks.isValidRemarks("peterjack@-")); // invalid domain name
        assertFalse(Remarks.isValidRemarks("peter jack@webmail.com")); // spaces in local part
        assertFalse(Remarks.isValidRemarks("peterjack@web mail.com")); // spaces in domain name
        assertFalse(Remarks.isValidRemarks("peterjack@@webmail.com")); // double '@' symbol
        assertFalse(Remarks.isValidRemarks("peter@jack@webmail.com")); // '@' symbol in local part
        assertFalse(Remarks.isValidRemarks("peterjack@webmail@com")); // '@' symbol in domain name

        // valid email
        assertTrue(Remarks.isValidRemarks("PeterJack_1190@WEB.Mail.com"));
        assertTrue(Remarks.isValidRemarks("a@b"));  // minimal
        assertTrue(Remarks.isValidRemarks("test@localhost"));   // alphabets only
        assertTrue(Remarks.isValidRemarks("123@145"));  // numeric local part and domain name
        assertTrue(Remarks.isValidRemarks("a1@sg50.org"));  // mixture of alphanumeric and dot characters
        assertTrue(Remarks.isValidRemarks("_user_@_do_main_.com_"));    // underscores
        assertTrue(Remarks.isValidRemarks("peter_jack@a_very_long_domain_AVLD.com"));   // long domain name
        assertTrue(Remarks.isValidRemarks("if.you.dream.it_you.can.do.it@youth_email.com"));    // long local part
    }
}
