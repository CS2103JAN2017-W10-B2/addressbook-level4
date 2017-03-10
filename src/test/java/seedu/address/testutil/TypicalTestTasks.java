package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ToDoList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 *
 */
public class TypicalTestTasks {

    public TestTask testExample1, testExample2, testExample3, testExample4, testExample5, testExample6, testExample7, testExample8, testExample9;

    public TypicalTestTasks() {
        try {
            testExample1 = new TaskBuilder().withTitle("JUnit Test 1")
                    .with_attribute_not_in_use("temp")
                    .withRemarks("send reminder to groupmates on whatsapp!!")
                    .withDeadline("111117")
                    .withLabels("project").build();
            testExample2 = new TaskBuilder().withTitle("JUnit Test 2")
            		.with_attribute_not_in_use("temp")
                    .withRemarks("at LT27, be there 15 mins earlier")
                    .withDeadline("101217")
                    .withLabels("science", "event").build();
            testExample3 = new TaskBuilder().withTitle("JUnit Test 3")
            		.withDeadline("130712")
                    .withRemarks("Project statement: Write about a theory that is significant to you.")
                    .with_attribute_not_in_use("temp").build();
            testExample4 = new TaskBuilder().withTitle("JUnit Test 4")
            		.withDeadline("081017")
                    .withRemarks("print out assignment for the cover").with_attribute_not_in_use("temp").build();
            testExample5 = new TaskBuilder().withTitle("JUnit Test 5")
            		.withDeadline("231112")
                    .withRemarks("bring jacket")
                    .with_attribute_not_in_use("temp").build();
            testExample6 = new TaskBuilder().withTitle("JUnit Test 6")
            		.withDeadline("101217")
                    .withRemarks("bring umbrella")
                    .with_attribute_not_in_use("temp").build();
            testExample7 = new TaskBuilder().withTitle("JUnit Test 7")
            		.withDeadline("140917")
                    .withRemarks("check email for updates")
                    .with_attribute_not_in_use("temp").build();
            testExample8 = new TaskBuilder().withTitle("JUnit Test 8")
            		.withDeadline("231117")
                    .withRemarks("eat before")
                    .with_attribute_not_in_use("temp").build();
            testExample9 = new TaskBuilder().withTitle("JUnit Test 9")
            		.withDeadline("231217")
                    .withRemarks("find out more information from Mary")
                    .with_attribute_not_in_use("temp").build();
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
        return new TestTask[]{testExample1, testExample2, testExample3, testExample4, testExample5, testExample6, testExample7};
    }

    public ToDoList getTypicalToDoList() {
        ToDoList tdl = new ToDoList();
        loadToDoListWithSampleData(tdl);
        return tdl;
    }
}
