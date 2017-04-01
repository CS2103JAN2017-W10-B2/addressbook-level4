package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ToDoList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 *
 */
public class TypicalTestTasks {

    public TestTask testExample1, testExample2, testExample3, testExample4, testExample5, testExample6;
    public TestTask testExample7, testExample8, testExample9;

    public TypicalTestTasks() {
        try {
            testExample1 = new TaskBuilder().withTitle("Project").withStartTime("10/11/17")
                    .withRemarks("send reminder to groupmates on whatsapp!!").withDeadline("10/11/17")
                    .withLabels("project").build();
            testExample2 = new TaskBuilder().withTitle("assignment due").withStartTime("10/12/17")
                    .withRemarks("hand in assignment at LT27").withDeadline("10/12/17").withLabels("science", "event")
                    .build();
            testExample3 = new TaskBuilder().withTitle("Entry Test").withDeadline("10/13/17")
                    .withRemarks("Project statement: Write about a theory that is significant to you.")
                    .withStartTime("10/13/17").build();
            testExample4 = new TaskBuilder().withTitle("Math assignment").withDeadline("11/10/17")
                    .withRemarks("print out assignment for the cover").withStartTime("11/10/17").build();
            testExample5 = new TaskBuilder().withTitle("Additional LEcture during recess week").withDeadline("11/23/17")
                    .withRemarks("bring jacket").withStartTime("11/23/17").build();
            testExample6 = new TaskBuilder().withTitle("Visit park").withDeadline("11/25/17")
                    .withRemarks("bring umbrella").withStartTime("11/25/17").build();
            testExample7 = new TaskBuilder().withTitle("Schedule meeting with prof").withDeadline("11/27/17")
                    .withRemarks("check email for updates").withStartTime("11/27/17").build();
            testExample8 = new TaskBuilder().withTitle("Midterms at 12pm").withDeadline("11/30/17")
                    .withRemarks("eat before").withStartTime("11/30/17").build();
            testExample9 = new TaskBuilder().withTitle("JUnit Test 9").withDeadline("12/23/17")
                    .withRemarks("find out more information from Mary").withStartTime("12/23/17").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadToDoListWithSampleData(ToDoList tdl) {
        for (TestTask task : new TypicalTestTasks().getTypicalTasks()) {
            try {
                tdl.addTask(new Task(task));
            } catch (UniqueTaskList.DuplicateTaskException e) {
                assert false : "not possible";
            }
        }
    }

    public TestTask[] getTypicalTasks() {
        // CHECKSTYLE.OFF: LineLength
        return new TestTask[] { testExample1, testExample2, testExample3, testExample4, testExample5, testExample6,
            testExample7 };
        // CHECKSTYLE.ON: LineLength
    }

    public ToDoList getTypicalToDoList() {
        ToDoList tdl = new ToDoList();
        loadToDoListWithSampleData(tdl);
        return tdl;
    }
}
