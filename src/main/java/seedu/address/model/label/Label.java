package seedu.address.model.label;


import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Label in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidLabelName(String)}
 */
public class Label {

    public static final String MESSAGE_LABEL_CONSTRAINTS = "Labels names should be alphanumeric";
    public static final String TAG_VALIDATION_REGEX = "\\p{Alnum}+";

    public final String labelName;

    /**
     * Validates given label name.
     *
     * @throws IllegalValueException if the given label name string is invalid.
     */
    public Label(String name) throws IllegalValueException {
        assert name != null;
        String trimmedName = name.trim();
        if (!isValidLabelName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_LABEL_CONSTRAINTS);
        }
        this.labelName = trimmedName;
    }

    /**
     * Returns true if a given string is a valid label name.
     */
    public static boolean isValidLabelName(String test) {
        return test.matches(TAG_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Label // instanceof handles nulls
                && this.labelName.equals(((Label) other).labelName)); // state check
    }

    @Override
    public int hashCode() {
        return labelName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + labelName + ']';
    }

}
