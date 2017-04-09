//@@author A0138831A
package seedu.address.model;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.task.FixedSizeStack;

public class FixedSizeStackTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private FixedSizeStack<Integer> fixedSizeStack = new FixedSizeStack<Integer>();

    @Test
    public void constructor() {
    	assertEquals(Collections.emptyList(),fixedSizeStack);
    }

    @Test
    public void testDefaultFixedSize() {
        fixedSizeStack.push(1);
        fixedSizeStack.push(2);
        fixedSizeStack.push(3);
        fixedSizeStack.push(4);
        fixedSizeStack.push(5);
        fixedSizeStack.push(6);
        fixedSizeStack.push(7);
        fixedSizeStack.push(8);
        fixedSizeStack.push(9);
        fixedSizeStack.push(10);
        fixedSizeStack.push(11);
        fixedSizeStack.push(12);
        assertEquals(fixedSizeStack.size(), 11);
    }

    @Test
    public void testUserDefineSize() {
    	int size = 2;
        fixedSizeStack = new FixedSizeStack<Integer>(size);
        fixedSizeStack.push(1);
        fixedSizeStack.push(2);
        fixedSizeStack.push(3);
        fixedSizeStack.push(4);
        assertEquals(fixedSizeStack.size(), size);
    }
}
