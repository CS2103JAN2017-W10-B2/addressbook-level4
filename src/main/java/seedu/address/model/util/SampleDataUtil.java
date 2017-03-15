package seedu.address.model.util;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ToDoList;
import seedu.address.model.ReadOnlyToDoList;
import seedu.address.model.label.UniqueLabelList;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.Title;
import seedu.address.model.task.Task;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.UniqueTaskList.DuplicateTaskException;

public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        try {
            return new Task[] {
                new Task(new Title("CS2103"), new Deadline("25032017"), new Remarks("asap"),
                    new StartTime("N/A"),
                    new UniqueLabelList("important"), false),
                new Task(new Title("EE4212 CA1"), new Deadline("26032017"), new Remarks("quality needed"),
                    new StartTime("N/A"),
                    new UniqueLabelList("urgent"), false),
                new Task(new Title("EG2401 Tutorial4"), new Deadline("27032017"), new Remarks("asap"),
                    new StartTime("N/A"),
                    new UniqueLabelList("important"), false),
                new Task(new Title("CS2010 MidTerm"), new Deadline("28032017"), new Remarks("optional"),
                    new StartTime("N/A"),
                    new UniqueLabelList("optional"), false),
                new Task(new Title("CS2103"), new Deadline("29032017"), new Remarks("asap"),
                    new StartTime("N/A"),
                    new UniqueLabelList("important"), false),
                new Task(new Title("CS2010 Assignment2"), new Deadline("30032017"), new Remarks("asap"),
                    new StartTime("N/A"),
                    new UniqueLabelList("urgent"), false)
            };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyToDoList getSampleAddressBook() {
        try {
            ToDoList sampleAB = new ToDoList();
            for (Task samplePerson : getSampleTasks()) {
                sampleAB.addTask(samplePerson);
            }
            return sampleAB;
        } catch (DuplicateTaskException e) {
            throw new AssertionError("sample data cannot contain duplicate tasks", e);
        }
    }
}
