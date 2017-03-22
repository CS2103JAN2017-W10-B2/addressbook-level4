//@@author A0143132X
package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.label.UniqueLabelList;
import seedu.address.model.label.Label;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Title;
import seedu.address.model.task.Task;
import seedu.address.model.task.StartTime;

/**
 * JAXB-friendly version of the Task.
 */
public class XmlAdaptedTask {

    @XmlElement(required = true)
    private String title;
    @XmlElement(required = false)
    private String deadline;
    @XmlElement(required = false)
    private String remarks;
    @XmlElement(required = false)
    private String startTime;

    @XmlElement
    private List<XmlAdaptedLabel> labeled = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedTask.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedTask() {}


    /**
     * Converts a given Task into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedTask
     */
    public XmlAdaptedTask(ReadOnlyTask source) {
        title = source.getTitle().fullTitle;
        if (source.hasDeadline()){
            deadline = source.getDeadline().value;
        }
        if (source.hasRemarks()){
            remarks = source.getRemarks().value;
        }
        if (source.hasStartTime()){
            startTime = source.getStartTime().value;
        }
        labeled = new ArrayList<>();
        for (Label tag : source.getLabels()) {
            labeled.add(new XmlAdaptedLabel(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted task object into the model's Task object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task
     */
    public Task toModelType() throws IllegalValueException {
        final List<Label> taskLabels = new ArrayList<>();
        for (XmlAdaptedLabel label : labeled) {
            taskLabels.add(label.toModelType());
        }
        final Title title = new Title(this.title);
        Deadline deadline = null;
        StartTime startTime = null;
        Remarks remarks = null;
        if (this.deadline != null){
            deadline = new Deadline(this.deadline);
        }
        if (this.remarks != null){
            remarks = new Remarks(this.remarks);
        }
        if (this.startTime != null){
            startTime = new StartTime(this.startTime);
        }
        final UniqueLabelList tags = new UniqueLabelList(taskLabels);
        return new Task(title, deadline, remarks, startTime, tags, false);
    }
}
