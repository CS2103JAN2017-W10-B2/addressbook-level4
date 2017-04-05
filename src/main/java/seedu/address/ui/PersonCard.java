package seedu.address.ui;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.TimeUtil;
import seedu.address.model.task.ReadOnlyTask;
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final String STOPPER_SYMBOL = ".";
    private static final String SEPARATOR = " ";
    private static final String DEADLINE_STARTING_MESSAGE = "Deadline  : ";
    private static final String START_TIME_STARTING_MESSAGE = "Start Time: ";
    private static final int NOON = 12;
    private static final int BEFORE_NOON_CUTOFF = 0;
    private static final String TIME_SEPARATOR = ":";
    private static final String AM = "am";
    private static final String PM = "pm";
    private static final String INVALID_MONTH = "You should not be seeing this.";
    private static final int YEAR_DIVISOR = 100;

    private static final int JAN_NUM = 1;
    private static final String JAN = "Jan";
    private static final int FEB_NUM = 2;
    private static final String FEB = "Feb";
    private static final int MAR_NUM = 3;
    private static final String MAR = "Mar";
    private static final int APR_NUM = 4;
    private static final String APR = "Apr";
    private static final int MAY_NUM = 5;
    private static final String MAY = "May";
    private static final int JUN_NUM = 6;
    private static final String JUN = "Jun";
    private static final int JUL_NUM = 7;
    private static final String JUL = "Jul";
    private static final int AUG_NUM = 8;
    private static final String AUG = "Aug";
    private static final int SEP_NUM = 9;
    private static final String SEP = "Sep";
    private static final int OCT_NUM = 10;
    private static final String OCT = "Oct";
    private static final int NOV_NUM = 11;
    private static final String NOV = "Nov";
    private static final int DEC_NUM = 12;
    private static final String DEC = "Dec";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

//@@author A0135795R
    public PersonCard(ReadOnlyTask person, int displayedIndex) {
        super(FXML);
        name.setText(person.getTitle().fullTitle);
        id.setText(displayedIndex + STOPPER_SYMBOL + SEPARATOR);
        phone.setText(SEPARATOR);
        address.setText(SEPARATOR);
        email.setText(SEPARATOR);
        if (person.hasDeadline()) {
            LocalDateTime deadline = getDateTime(person);
            phone.setText(DEADLINE_STARTING_MESSAGE + getFormattedDateTime(deadline));
        }
        if (person.hasStartTime()) {
            LocalDateTime startTime = getDateTime(person);
            address.setText(START_TIME_STARTING_MESSAGE + getFormattedDateTime(startTime));
        }
        if (person.hasRemarks()) {
            email.setText(person.getRemarks().value);
        }
        initTags(person);
    }

    private LocalDateTime getDateTime(ReadOnlyTask person) {
        return TimeUtil.getDateTime(person.getDeadline().value);
    }

    private String getFormattedDateTime(LocalDateTime dateTime) {
        return getStringTime(dateTime) + SEPARATOR + getStringDate(dateTime);
    }

    private String getStringTime(LocalDateTime dateTime) {
        String time;
        if (isBeforeNoon(dateTime)) {
            time = Integer.toString(dateTime.getHour());
            time = time.concat(TIME_SEPARATOR);
            time = time.concat(Integer.toString(dateTime.getMinute()));
            time = time.concat(AM);
        } else {
            int hour = dateTime.getHour();
            if (hour != NOON) {
                hour = hour - NOON;
            }
            time = Integer.toString(hour);
            time = time.concat(TIME_SEPARATOR);
            time = time.concat(Integer.toString(dateTime.getMinute()));
            time = time.concat(PM);
        }
        return time;
    }

    private String getStringDate(LocalDateTime dateTime) {
        String date;
        String month = INVALID_MONTH;
        switch (dateTime.getMonthValue()) {
        case JAN_NUM:
            month = JAN;
            break;
        case FEB_NUM:
            month = FEB;
            break;
        case MAR_NUM:
            month = MAR;
            break;
        case APR_NUM:
            month = APR;
            break;
        case MAY_NUM:
            month = MAY;
            break;
        case JUN_NUM:
            month = JUN;
            break;
        case JUL_NUM:
            month = JUL;
            break;
        case AUG_NUM:
            month = AUG;
            break;
        case SEP_NUM:
            month = SEP;
            break;
        case OCT_NUM:
            month = OCT;
            break;
        case NOV_NUM:
            month = NOV;
            break;
        case DEC_NUM:
            month = DEC;
            break;
        }
        date = Integer.toString(dateTime.getDayOfMonth());
        date = date.concat(SEPARATOR + month);
        String year = Integer.toString(dateTime.getYear() % YEAR_DIVISOR);
        date = date.concat(SEPARATOR + year);
        return date;
    }

    private boolean isBeforeNoon(LocalDateTime dateTime) {
        return dateTime.toLocalTime().compareTo(LocalTime.NOON) < BEFORE_NOON_CUTOFF;
    }
//@@author

    private void initTags(ReadOnlyTask person) {
        person.getLabels().forEach(tag -> tags.getChildren().add(new Label(tag.labelName)));
    }
}
