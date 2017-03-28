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
            testExample1 = new TaskBuilder().withTitle("Project")
                    .withStartTime("temp")
                    .withRemarks("send reminder to groupmates on whatsapp!!")
                    .withDeadline("111117")
                    .withLabels("project").build();
            testExample2 = new TaskBuilder().withTitle("assignment due")
                    .withStartTime("temp")
                    .withRemarks("hand in assignment at LT27")
                    .withDeadline("101217")
                    .withLabels("science", "event").build();
            testExample3 = new TaskBuilder().withTitle("Entry Test")
                    .withDeadline("130712")
                    .withRemarks("Project statement: Write about a theory that is significant to you.")
                    .withStartTime("temp").build();
            testExample4 = new TaskBuilder().withTitle("Math assignment")
                    .withDeadline("081017")
                    .withRemarks("print out assignment for the cover").withStartTime("temp").build();
            testExample5 = new TaskBuilder().withTitle("Additional LEcture during recess week")
                    .withDeadline("231112")
                    .withRemarks("bring jacket")
                    .withStartTime("temp").build();
            testExample6 = new TaskBuilder().withTitle("Visit park")
                    .withDeadline("101217")
                    .withRemarks("bring umbrella")
                    .withStartTime("temp").build();
            testExample7 = new TaskBuilder().withTitle("Schedule meeting with prof")
                    .withDeadline("140917")
                    .withRemarks("check email for updates")
                    .withStartTime("temp").build();
            testExample8 = new TaskBuilder().withTitle("Midterms at 12pm")
                    .withDeadline("231117")
                    .withRemarks("eat before")
                    .withStartTime("temp").build();
            testExample9 = new TaskBuilder().withTitle("JUnit Test 9")
                    .withDeadline("231217")
                    .withRemarks("find out more information from Mary")
                    .withStartTime("temp").build();
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
        return new TestTask[]{testExample1, testExample2, testExample3, testExample4, testExample5,
                testExample6, testExample7};
    }

    public ToDoList getTypicalToDoList() {
        ToDoList tdl = new ToDoList();
        loadToDoListWithSampleData(tdl);
        return tdl;
    }
}
