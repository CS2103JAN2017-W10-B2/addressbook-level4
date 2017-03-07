package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmailTest {

    @Test
    public void isValidEmail() {
        // blank email
        assertFalse(REMARKS.isValidEmail("")); // empty string
        assertFalse(REMARKS.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(REMARKS.isValidEmail("@webmail.com")); // missing local part
        assertFalse(REMARKS.isValidEmail("peterjackwebmail.com")); // missing '@' symbol
        assertFalse(REMARKS.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(REMARKS.isValidEmail("-@webmail.com")); // invalid local part
        assertFalse(REMARKS.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(REMARKS.isValidEmail("peter jack@webmail.com")); // spaces in local part
        assertFalse(REMARKS.isValidEmail("peterjack@web mail.com")); // spaces in domain name
        assertFalse(REMARKS.isValidEmail("peterjack@@webmail.com")); // double '@' symbol
        assertFalse(REMARKS.isValidEmail("peter@jack@webmail.com")); // '@' symbol in local part
        assertFalse(REMARKS.isValidEmail("peterjack@webmail@com")); // '@' symbol in domain name

        // valid email
        assertTrue(REMARKS.isValidEmail("PeterJack_1190@WEB.Mail.com"));
        assertTrue(REMARKS.isValidEmail("a@b"));  // minimal
        assertTrue(REMARKS.isValidEmail("test@localhost"));   // alphabets only
        assertTrue(REMARKS.isValidEmail("123@145"));  // numeric local part and domain name
        assertTrue(REMARKS.isValidEmail("a1@sg50.org"));  // mixture of alphanumeric and dot characters
        assertTrue(REMARKS.isValidEmail("_user_@_do_main_.com_"));    // underscores
        assertTrue(REMARKS.isValidEmail("peter_jack@a_very_long_domain_AVLD.com"));   // long domain name
        assertTrue(REMARKS.isValidEmail("if.you.dream.it_you.can.do.it@youth_email.com"));    // long local part
    }
}
