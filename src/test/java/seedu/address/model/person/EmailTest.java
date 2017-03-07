package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.task.REMARKS;

public class EmailTest {

    @Test
    public void isValidEmail() {
        // blank email
        assertFalse(REMARKS.isValidRemarks("")); // empty string
        assertFalse(REMARKS.isValidRemarks(" ")); // spaces only

        // missing parts
        assertFalse(REMARKS.isValidRemarks("@webmail.com")); // missing local part
        assertFalse(REMARKS.isValidRemarks("peterjackwebmail.com")); // missing '@' symbol
        assertFalse(REMARKS.isValidRemarks("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(REMARKS.isValidRemarks("-@webmail.com")); // invalid local part
        assertFalse(REMARKS.isValidRemarks("peterjack@-")); // invalid domain name
        assertFalse(REMARKS.isValidRemarks("peter jack@webmail.com")); // spaces in local part
        assertFalse(REMARKS.isValidRemarks("peterjack@web mail.com")); // spaces in domain name
        assertFalse(REMARKS.isValidRemarks("peterjack@@webmail.com")); // double '@' symbol
        assertFalse(REMARKS.isValidRemarks("peter@jack@webmail.com")); // '@' symbol in local part
        assertFalse(REMARKS.isValidRemarks("peterjack@webmail@com")); // '@' symbol in domain name

        // valid email
        assertTrue(REMARKS.isValidRemarks("PeterJack_1190@WEB.Mail.com"));
        assertTrue(REMARKS.isValidRemarks("a@b"));  // minimal
        assertTrue(REMARKS.isValidRemarks("test@localhost"));   // alphabets only
        assertTrue(REMARKS.isValidRemarks("123@145"));  // numeric local part and domain name
        assertTrue(REMARKS.isValidRemarks("a1@sg50.org"));  // mixture of alphanumeric and dot characters
        assertTrue(REMARKS.isValidRemarks("_user_@_do_main_.com_"));    // underscores
        assertTrue(REMARKS.isValidRemarks("peter_jack@a_very_long_domain_AVLD.com"));   // long domain name
        assertTrue(REMARKS.isValidRemarks("if.you.dream.it_you.can.do.it@youth_email.com"));    // long local part
    }
}
