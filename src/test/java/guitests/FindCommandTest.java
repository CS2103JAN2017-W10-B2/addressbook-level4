package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.testutil.TestTask;

public class FindCommandTest extends ToDoListGuiTest {

    @Test
    public void find_nonEmptyList() {
        assertFindResult("find orange"); // no results
        assertFindResult("find #pokemon"); // no results

        assertFindResult("find #project", td.testExample1);
        assertFindResult("find assignment", td.testExample2, td.testExample4); // multiple
                                                                               // results

        // find after deleting one result
        commandBox.runCommand("delete 1");
        assertFindResult("find assignment", td.testExample4);
    }

    @Test
    public void find_emptyList() {
        commandBox.runCommand("clear");
        assertFindResult("find module"); // no results
    }

    @Test
    public void find_invalidCommand_fail() {
        commandBox.runCommand("findgeorge");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    private void assertFindResult(String command, TestTask... expectedHits) {
        commandBox.runCommand(command);
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " task(s) listed!");
        assertTrue(taskListPanel.isListMatching(expectedHits));
    }
}
