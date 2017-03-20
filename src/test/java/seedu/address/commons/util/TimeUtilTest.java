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
		assertNull(TimeUtil.getDate("99999"));
		assertNull(TimeUtil.getDate("9999999"));
		
		//invalid date
		assertNull(TimeUtil.getDate("000100"));
		assertNull(TimeUtil.getDate("010000"));
		assertNull(TimeUtil.getDate("011300"));
		assertNull(TimeUtil.getDate("320100"));
		assertNull(TimeUtil.getDate("290217"));
	}
	
	@Test
	public void isInvalidTime() {
		//invalid length
		assertNull(TimeUtil.getTime("999"));
		assertNull(TimeUtil.getTime("99999"));
		
		//invalid time
		assertNull(TimeUtil.getTime("2400"));
	}
	
	@Test
	public void isValidDate(){
		assertNotNull(TimeUtil.getDate("010100"));
		assertNotNull(TimeUtil.getDate("311299"));
		assertNotNull(TimeUtil.getDate("290216"));
	}
	
	@Test
	public void isValidTime(){
		assertNotNull(TimeUtil.getTime("0000"));
		assertNotNull(TimeUtil.getTime("2359"));
	}
	
}
