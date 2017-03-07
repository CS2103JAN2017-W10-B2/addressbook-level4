package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AddressTest {

    @Test
    public void isValidAddress() {
        // invalid addresses
        assertFalse(This_attribute_is_not_in_use.isValidAddress("")); // empty string
        assertFalse(This_attribute_is_not_in_use.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(This_attribute_is_not_in_use.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(This_attribute_is_not_in_use.isValidAddress("-")); // one character
        assertTrue(This_attribute_is_not_in_use.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
