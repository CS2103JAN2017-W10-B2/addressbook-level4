package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.ReadOnlyAddressBook;

/** Indicates the AddressBook in the model has changed*/
public class ToDoListChangedEvent extends BaseEvent {

    public final ReadOnlyAddressBook data;

    public ToDoListChangedEvent(ReadOnlyAddressBook data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of tasks " + data.getPersonList().size() + ", number of labels " + data.getTagList().size();
    }
}
