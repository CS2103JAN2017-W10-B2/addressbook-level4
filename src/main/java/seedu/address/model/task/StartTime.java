package seedu.address.model.task;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's start time in the toDoList.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartTime(String)}
 */
public class StartTime {

    public static final String MESSAGE_START_TIME_CONSTRAINTS = "Task start time should only contain numbers";
    public static final String START_TIME_VALIDATION_REGEX = "\\d{4}(\\s+\\d{6})?|\\d{6}(\\s+\\d{4})?|";

    public final String value;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given start time string is invalid.
     */
    public StartTime(String startTime) throws IllegalValueException {
        assert startTime != null;
        String trimmedStartTime = startTime.trim();
        if (!isValidStartTime(trimmedStartTime)) {
            throw new IllegalValueException(MESSAGE_START_TIME_CONSTRAINTS);
        }
        this.value = trimmedStartTime;
    }

    /**
     * Returns true if a given string is a valid task start time.
     */
    public static boolean isValidStartTime(String test) {
        return test.matches(START_TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTime // instanceof handles nulls
                && this.value.equals(((StartTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
