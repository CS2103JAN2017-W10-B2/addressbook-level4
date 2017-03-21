package seedu.address.model.task;

import seedu.address.commons.exceptions.IllegalValueException;

public class Recurring {
    public static final String MESSAGE_RECURRING_CONSTRAINTS = "Task recurring should specify frequency(daily/weekly/monthly/yearly) and times to repeat(1-366).";
    //public static final String RECURRING_VALIDATION_REGEX = "([dwmy])";
    private static final String RECURRING_SPLITTER = " ";
    private static final int RECURRING_PARAMETERS = 2;
    private static final int PERIOD_INDEX = 0;
    private static final int PERIOD_LIMIT = 1;
    private static final int PERIOD_MAX = 366;
    public final String period;
    public final String limit;
    public final String value;

    /**
     * Validates given recurring period.
     *
     * @throws IllegalValueException if given recurring string is invalid.
     */
    
    public Recurring(String recurring) throws IllegalValueException {
        assert recurring != null;
        String trimmedRecurring = recurring.trim(); 
        String[] splitRecurring = trimmedRecurring.split(RECURRING_SPLITTER);
        if (!isValidRecurring(splitRecurring)) {
            throw new IllegalValueException(MESSAGE_RECURRING_CONSTRAINTS);
        }
        this.period = splitRecurring[PERIOD_INDEX];
        this.limit = splitRecurring[PERIOD_LIMIT];
        this.value = this.period + " " + this.limit;
    }

    /**
     * Returns if a given string is a valid person remarks.
     */
    public static boolean isValidRecurring(String[] test) {
        if (test.length != RECURRING_PARAMETERS){
            return false;
        }
        try{
            int testLimit = Integer.valueOf(test[PERIOD_LIMIT]);
            if (testLimit<=0){
                return false;
            }
            if (testLimit>PERIOD_MAX){
                return false;
            }
        } catch(NumberFormatException e){
            return false;
        }
        if (test[PERIOD_INDEX].toLowerCase().equals("daily")){
            return true;
        }
        if (test[PERIOD_INDEX].toLowerCase().equals("weekly")){
            return true;
        }
        if (test[PERIOD_INDEX].toLowerCase().equals("monthly")){
            return true;
        }
        if (test[PERIOD_INDEX].toLowerCase().equals("yearly")){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Recurring // instanceof handles nulls
                && this.value.equals(((Recurring) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
