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
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedPerson {

    @XmlElement(required = true)
    private String title;
    @XmlElement(required = false)
    private String deadline;
    @XmlElement(required = false)
    private String remarks;
    @XmlElement(required = false)
    private String startTime;

    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedPerson.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedPerson() {}


    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedPerson(ReadOnlyTask source) {
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
        tagged = new ArrayList<>();
        for (Label tag : source.getLabels()) {
            tagged.add(new XmlAdaptedTag(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Task toModelType() throws IllegalValueException {
        final List<Label> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
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
        final UniqueLabelList tags = new UniqueLabelList(personTags);
        return new Task(title, deadline, remarks, startTime, tags, false);
    }
}
