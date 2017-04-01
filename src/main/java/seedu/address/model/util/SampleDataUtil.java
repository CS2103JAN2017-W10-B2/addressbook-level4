//@@author A0115333U
package seedu.address.model.util;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyToDoList;
import seedu.address.model.ToDoList;
import seedu.address.model.label.UniqueLabelList;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.UniqueTaskList.DuplicateTaskException;

public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        try {
            return new Task[] {
                new Task(new Title("CS2103"), new Deadline("10/10/17"), new Remarks("asap"),
                    new StartTime("10/10/17"),
                    new UniqueLabelList("important"), false),
                new Task(new Title("EE4212 CA1"), new Deadline("20/10/17"), new Remarks("quality needed"),
                    new StartTime("20/10/17"),
                    new UniqueLabelList("urgent"), false),
                new Task(new Title("EG2401 Tutorial4"), new Deadline("11/11/17"), new Remarks("asap"),
                    new StartTime("11/11/17"),
                    new UniqueLabelList("important"), false),
                new Task(new Title("CS2010 MidTerm"), new Deadline("21/11/17"), new Remarks("optional"),
                    new StartTime("21/11/17"),
                    new UniqueLabelList("optional"), false),
                new Task(new Title("CS2103 demo"), new Deadline("12/12/17"), new Remarks("asap"),
                    new StartTime("12/12/17"),
                    new UniqueLabelList("important"), false),
                new Task(new Title("CS2010 Assignment2"), new Deadline("22/12/17"), new Remarks("asap"),
                    new StartTime("22/12/17"),
                    new UniqueLabelList("urgent"), false)
            };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }
  //@@author

    public static ReadOnlyToDoList getSampleToDoList() {
        try {
            ToDoList sampleTDL = new ToDoList();
            for (Task sampleTask : getSampleTasks()) {
                sampleTDL.addTask(sampleTask);
            }
            return sampleTDL;
        } catch (DuplicateTaskException e) {
            throw new AssertionError("sample data cannot contain duplicate tasks", e);
        }
    }
}
