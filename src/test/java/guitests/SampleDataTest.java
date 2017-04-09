package guitests;

import seedu.address.model.ToDoList;
import seedu.address.testutil.TestUtil;

public class SampleDataTest extends ToDoListGuiTest {
    @Override
    protected ToDoList getInitialData() {
        // return null to force test app to load data from file only
        return null;
    }

    @Override
    protected String getDataFileLocation() {
        // return a non-existent file location to force test app to load sample
        // data
        return TestUtil.getFilePathInSandboxFolder("SomeFileThatDoesNotExist1234567890.xml");
    }

}
