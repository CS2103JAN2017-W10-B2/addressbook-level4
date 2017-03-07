package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.This_attribute_is_not_in_use;
import seedu.address.model.person.REMARKS;
import seedu.address.model.person.TITLE;
import seedu.address.model.person.DEADLINE;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.LABELS;

/**
 *
 */
public class PersonBuilder {

    private TestPerson person;

    public PersonBuilder() {
        this.person = new TestPerson();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(TestPerson personToCopy) {
        this.person = new TestPerson(personToCopy);
    }

    public PersonBuilder withName(String name) throws IllegalValueException {
        this.person.setName(new TITLE(name));
        return this;
    }

    public PersonBuilder withTags(String ... tags) throws IllegalValueException {
        person.setTags(new LABELS());
        for (String tag: tags) {
            person.getLabels().add(new Tag(tag));
        }
        return this;
    }

    public PersonBuilder withAddress(String address) throws IllegalValueException {
        this.person.setAddress(new This_attribute_is_not_in_use(address));
        return this;
    }

    public PersonBuilder withPhone(String phone) throws IllegalValueException {
        this.person.setPhone(new DEADLINE(phone));
        return this;
    }

    public PersonBuilder withEmail(String email) throws IllegalValueException {
        this.person.setEmail(new REMARKS(email));
        return this;
    }

    public TestPerson build() {
        return this.person;
    }

}
