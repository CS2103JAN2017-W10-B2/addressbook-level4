package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.TimeUtil;
import seedu.address.model.task.ReadOnlyTask;
public class TaskCard extends UiPart<Region> {

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
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label deadline;
    @FXML
    private Label startTime;
    @FXML
    private Label remarks;
    @FXML
    private FlowPane labels;

//@@author A0135795R
    public TaskCard(ReadOnlyTask task, int displayedIndex) {
        super(FXML);
        title.setText(task.getTitle().fullTitle);
        id.setText(displayedIndex + STOPPER_SYMBOL + SEPARATOR);
        deadline.setText(SEPARATOR);
        startTime.setText(SEPARATOR);
        remarks.setText(SEPARATOR);
        if (task.hasDeadline()) {
            String deadlineValue = task.getDeadline().value;
            deadline.setText(DEADLINE_STARTING_MESSAGE + TimeUtil.getFormattedDateTime(deadlineValue));
        }
        if (task.hasStartTime()) {
            String startTimeValue = task.getStartTime().value;
            startTime.setText(START_TIME_STARTING_MESSAGE + TimeUtil.getFormattedDateTime(startTimeValue));
        }
        if (task.hasRemarks()) {
            remarks.setText(task.getRemarks().value);
        }
        initTags(task);
    }
//@@author

    private void initTags(ReadOnlyTask task) {
        task.getLabels().forEach(label -> labels.getChildren().add(new Label(label.labelName)));
    }
}
