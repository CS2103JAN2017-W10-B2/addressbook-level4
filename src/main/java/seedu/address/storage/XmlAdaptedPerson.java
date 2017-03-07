package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.This_attribute_is_not_in_use;
import seedu.address.model.person.REMARKS;
import seedu.address.model.person.TITLE;
import seedu.address.model.person.Task;
import seedu.address.model.person.DEADLINE;
import seedu.address.model.person.ReadOnlyTask;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.LABELS;

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
        name = source.getTitle().fullName;
        phone = source.getDeadline().value;
        email = source.getRemarks().value;
        address = source.getNot_in_use().value;
        tagged = new ArrayList<>();
        for (Tag tag : source.getLabels()) {
            tagged.add(new XmlAdaptedTag(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final TITLE name = new TITLE(this.name);
        final DEADLINE phone = new DEADLINE(this.phone);
        final REMARKS email = new REMARKS(this.email);
        final This_attribute_is_not_in_use address = new This_attribute_is_not_in_use(this.address);
        final LABELS tags = new LABELS(personTags);
        return new Task(name, phone, email, address, tags);
    }
}
