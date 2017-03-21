package seedu.address.model.task;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.TimeUtil;

/**
 * Represents a Task's deadline in the doitdoit!!.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {

    public static final String MESSAGE_DEADLINE_CONSTRAINTS = "Task deadline is not accepted, try a more formal "
    		+ "definition.";

    public final String value;

    /**
     * Validates given deadline.
     *
     * @throws IllegalValueException if given deadline is invalid.
     */
    public Deadline(String deadline) throws IllegalValueException {
        assert deadline != null;
        String trimmedDeadline = deadline.trim();
        if (!isValidDeadline(trimmedDeadline)) {
            throw new IllegalValueException(MESSAGE_DEADLINE_CONSTRAINTS);
        }
        this.value = trimmedDeadline;
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        return TimeUtil.isValidDateTimeExist(test);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instance of handles nulls
                && this.value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
