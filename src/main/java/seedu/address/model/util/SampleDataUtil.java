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
                    new Task(new Title("FIN3101 exam"), new Deadline("next monday 1pm"),
                            new Remarks("asap"), new StartTime("last thursday"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("MA3252 project"), new Deadline("first day next month"),
                            new Remarks("urgent"), new StartTime("last thursday"),
                               new UniqueLabelList("asap"), false),
                         new Task(new Title("Have dinner with Ming Rui"), new Deadline("next saturday 5:30pm"),
                            new Remarks("arrive early"), new StartTime("this sunday 6:30pm"),
                               new UniqueLabelList("heart"), false),
                         new Task(new Title("CS2103 meeting"), new Deadline("next week friday 3:30pm"),
                            new Remarks("urgent"), new StartTime("today"),
                               new UniqueLabelList("asap"), false),
                         new Task(new Title("Go shopping with Rei Yun"), new Deadline("this sunday 6:30pm"),
                            new Remarks("at Orchard"), new StartTime("next saturday 5:30pm"),
                               new UniqueLabelList("fun"), false),
                         new Task(new Title("Stock up on pizza"), new Deadline("tomorrow"),
                            new Remarks("can delay"), new StartTime("last thursday"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("CS2010 meeting"), new Deadline("first day next month"),
                            new Remarks("asap"), new StartTime("day after tomorrow"),
                               new UniqueLabelList("important"), false),
                         new Task(new Title("FIN3101 project"), new Deadline("tuesday 4pm"),
                            new Remarks("asap"), new StartTime("last thursday"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("MA3252 assignment"), new Deadline("tuesday 4pm"),
                            new Remarks("quality needed"), new StartTime("day after tomorrow"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("MA3252 meeting"), new Deadline("next monday 1pm"),
                            new Remarks("asap"), new StartTime("day after tomorrow"),
                               new UniqueLabelList("asap"), false),
                         new Task(new Title("CS2103 assignment"), new Deadline("next week friday 3:30pm"),
                            new Remarks("quality needed"), new StartTime("last thursday"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("Go shopping with Uncle Soo"), new Deadline("this sunday 6:30pm"),
                            new Remarks("arrive early"), new StartTime("next saturday 5:30pm"),
                               new UniqueLabelList("Funnnnn"), false),
                         new Task(new Title("Go shopping with Ray"), new Deadline("next friday 5pm"),
                            new Remarks("Clarke Quay"), new StartTime("this sunday 6:30pm"),
                               new UniqueLabelList("IMPORTANT"), false),
                         new Task(new Title("QF3101 meeting"), new Deadline("tuesday 4pm"),
                            new Remarks("quality needed"), new StartTime("last thursday"),
                               new UniqueLabelList("important"), false),
                         new Task(new Title("QF3101 project"), new Deadline("tuesday 4pm"),
                            new Remarks("urgent"), new StartTime("today"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("Have dinner with Rei Yun"), new Deadline("this sunday 6:30pm"),
                            new Remarks("at Orchard"), new StartTime("next friday 5pm"),
                               new UniqueLabelList("Funnnnn"), false),
                         new Task(new Title("Go buy pizza"), new Deadline("last thursday"),
                            new Remarks("can delay"), new StartTime("tomorrow"),
                               new UniqueLabelList("asap"), false),
                         new Task(new Title("Have dinner with Zhong Qi"), new Deadline("next saturday 5:30pm"),
                            new Remarks("at Orchard"), new StartTime("next friday 5pm"),
                               new UniqueLabelList("fun"), false),
                         new Task(new Title("ST3131 meeting"), new Deadline("first day next month"),
                            new Remarks("urgent"), new StartTime("day after tomorrow"),
                               new UniqueLabelList("asap"), false),
                         new Task(new Title("Have dinner with Irfan"), new Deadline("this sunday 6:30pm"),
                            new Remarks("at Orchard"), new StartTime("next friday 5pm"),
                               new UniqueLabelList("heart"), false),
                         new Task(new Title("Have dinner with Ray"), new Deadline("this sunday 6:30pm"),
                            new Remarks("Clarke Quay"), new StartTime("this sunday 6:30pm"),
                               new UniqueLabelList("heart"), false),
                         new Task(new Title("QF3101 assignment"), new Deadline("next monday 1pm"),
                            new Remarks("urgent"), new StartTime("last thursday"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("CS2010 assignment"), new Deadline("tuesday 4pm"),
                            new Remarks("asap"), new StartTime("today"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("ST3131 homework"), new Deadline("tuesday 4pm"),
                            new Remarks("urgent"), new StartTime("today"),
                               new UniqueLabelList("asap"), false),
                         new Task(new Title("Stock up on stationaries"), new Deadline("today"),
                            new Remarks("soon"), new StartTime("last thursday"),
                               new UniqueLabelList("important"), false),
                         new Task(new Title("FIN3101 homework"), new Deadline("first day next month"),
                            new Remarks("urgent"), new StartTime("last thursday"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("Have dinner with Uncle Soo"), new Deadline("next saturday 5:30pm"),
                            new Remarks("arrive early"), new StartTime("this sunday 6:30pm"),
                               new UniqueLabelList("fun"), false),
                         new Task(new Title("Go shopping with Irfan"), new Deadline("next saturday 5:30pm"),
                            new Remarks("arrive on time"), new StartTime("next friday 5pm"),
                               new UniqueLabelList("fun"), false),
                         new Task(new Title("ST3131 project"), new Deadline("first day next month"),
                            new Remarks("urgent"), new StartTime("last thursday"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("CS2010 project"), new Deadline("tuesday 4pm"),
                            new Remarks("quality needed"), new StartTime("today"),
                               new UniqueLabelList("asap"), false),
                         new Task(new Title("Go buy stationaries"), new Deadline("tomorrow"),
                            new Remarks("soon"), new StartTime("tomorrow"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("Have dinner with Zi Rei"), new Deadline("next saturday 5:30pm"),
                            new Remarks("at Orchard"), new StartTime("this sunday 6:30pm"),
                               new UniqueLabelList("fun"), false),
                         new Task(new Title("CS2010 exam"), new Deadline("tuesday 4pm"),
                            new Remarks("quality needed"), new StartTime("day after tomorrow"),
                               new UniqueLabelList("asap"), false),
                         new Task(new Title("FIN3101 assignment"), new Deadline("next week friday 3:30pm"),
                            new Remarks("urgent"), new StartTime("today"),
                               new UniqueLabelList("asap"), false),
                         new Task(new Title("MA3252 exam"), new Deadline("first day next month"),
                            new Remarks("urgent"), new StartTime("day after tomorrow"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("CS2103 homework"), new Deadline("first day next month"),
                            new Remarks("asap"), new StartTime("last thursday"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("Go buy sweets"), new Deadline("tomorrow"),
                            new Remarks("impt"), new StartTime("tomorrow"),
                               new UniqueLabelList("important"), false),
                         new Task(new Title("Go shopping with Zi Rei"), new Deadline("next friday 5pm"),
                            new Remarks("Clarke Quay"), new StartTime("this sunday 6:30pm"),
                               new UniqueLabelList("heart"), false),
                         new Task(new Title("ST3131 assignment"), new Deadline("tuesday 4pm"),
                            new Remarks("quality needed"), new StartTime("last thursday"),
                               new UniqueLabelList("important"), false),
                         new Task(new Title("CS2103 exam"), new Deadline("next week friday 3:30pm"),
                            new Remarks("asap"), new StartTime("last thursday"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("QF3101 homework"), new Deadline("next week friday 3:30pm"),
                            new Remarks("asap"), new StartTime("day after tomorrow"),
                               new UniqueLabelList("important"), false),
                         new Task(new Title("Go shopping with Zhong Qi"), new Deadline("next saturday 5:30pm"),
                            new Remarks("arrive early"), new StartTime("next friday 5pm"),
                               new UniqueLabelList("fun"), false),
                         new Task(new Title("CS2010 homework"), new Deadline("tuesday 4pm"),
                            new Remarks("quality needed"), new StartTime("today"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("MA3252 homework"), new Deadline("next monday 1pm"),
                            new Remarks("urgent"), new StartTime("today"),
                               new UniqueLabelList("important"), false),
                         new Task(new Title("Stock up on sweets"), new Deadline("last thursday"),
                            new Remarks("impt"), new StartTime("tomorrow"),
                               new UniqueLabelList("urgent"), false),
                         new Task(new Title("CS2103 project"), new Deadline("tuesday 4pm"),
                            new Remarks("urgent"), new StartTime("today"),
                               new UniqueLabelList("asap"), false),
                         new Task(new Title("ST3131 exam"), new Deadline("next week friday 3:30pm"),
                            new Remarks("asap"), new StartTime("today"),
                               new UniqueLabelList("important"), false),
                         new Task(new Title("Go shopping with Ming Rui"), new Deadline("this sunday 6:30pm"),
                            new Remarks("arrive early"), new StartTime("this sunday 6:30pm"),
                               new UniqueLabelList("Funnnnn"), false),
                         new Task(new Title("QF3101 exam"), new Deadline("tuesday 4pm"),
                            new Remarks("urgent"), new StartTime("last thursday"),
                               new UniqueLabelList("important"), false),
                         new Task(new Title("FIN3101 meeting"), new Deadline("next week friday 3:30pm"),
                            new Remarks("asap"), new StartTime("today"),
                               new UniqueLabelList("asap"), false)
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
