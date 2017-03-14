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
    private String name;
    @XmlElement(required = true)
    private String phone;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String address;

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
        name = source.getTitle().fullTitle;
        phone = source.getDeadline().value;
        email = source.getRemarks().value;
        address = source.getNot_in_use().value;
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
        final Title name = new Title(this.name);
        final Deadline phone = new Deadline(this.phone);
        final Remarks email = new Remarks(this.email);
        final StartTime address = new StartTime(this.address);
        final UniqueLabelList tags = new UniqueLabelList(personTags);
        return new Task(name, phone, email, address, tags);
    }
}
