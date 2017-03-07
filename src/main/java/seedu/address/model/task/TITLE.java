package seedu.address.model.task;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's title in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class TITLE {

    public static final String MESSAGE_TITLE_CONSTRAINTS =
            "Task titles should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String TITLE_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullTitle;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public TITLE(String title) throws IllegalValueException {
        assert title != null;
        String trimmedTitle = title.trim();
        if (!isValidTitle(trimmedTitle)) {
            throw new IllegalValueException(MESSAGE_TITLE_CONSTRAINTS);
        }
        this.fullTitle = trimmedTitle;
    }

    /**
     * Returns true if a given string is a valid person name.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(TITLE_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullTitle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TITLE // instanceof handles nulls
                && this.fullTitle.equals(((TITLE) other).fullTitle)); // state check
    }

    @Override
    public int hashCode() {
        return fullTitle.hashCode();
    }

}
