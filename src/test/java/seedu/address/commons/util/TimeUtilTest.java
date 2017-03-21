//@@author A0135795R
package seedu.address.commons.util;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TimeUtilTest {

	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void isInvalidDate() {
		//invalid length
		assertNull(TimeUtil.getDateTime("99999"));
		assertNull(TimeUtil.getDateTime("9999999"));
		
		//invalid date
		assertNull(TimeUtil.getDateTime("000100"));
		assertNull(TimeUtil.getDateTime("010000"));
		assertNull(TimeUtil.getDateTime("011300"));
		assertNull(TimeUtil.getDateTime("320100"));
		assertNull(TimeUtil.getDateTime("290217"));
	}
	
	@Test
	public void isInvalidTime() {
		//invalid length
		assertNull(TimeUtil.getDateTime("999"));
		assertNull(TimeUtil.getDateTime("99999"));
		
		//invalid time
		assertNull(TimeUtil.getDateTime("2400"));
	}
	
	@Test
	public void isValidDate(){
		assertNotNull(TimeUtil.getDateTime("010100"));
		assertNotNull(TimeUtil.getDateTime("311299"));
		assertNotNull(TimeUtil.getDateTime("290216"));
	}
	
	@Test
	public void isValidTime(){
		assertNotNull(TimeUtil.getDateTime("0000"));
		assertNotNull(TimeUtil.getDateTime("2359"));
	}
	
}
