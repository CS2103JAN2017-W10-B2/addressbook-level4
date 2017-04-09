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
    private FlowPane tags;

//@@author A0135795R
    public TaskCard(ReadOnlyTask task, int displayedIndex) {
        super(FXML);
        title.setText(task.getTitle().fullTitle);
        id.setText(displayedIndex + STOPPER_SYMBOL + SEPARATOR);
        if (task.hasDeadline()) {
            String deadlineValue = task.getDeadline().value;
            deadline.setText(DEADLINE_STARTING_MESSAGE + TimeUtil.getFormattedDateTime(deadlineValue));
        } else {
            deadline.setText(SEPARATOR);
        }
        if (task.hasStartTime()) {
            String startTimeValue = task.getStartTime().value;
            startTime.setText(START_TIME_STARTING_MESSAGE + TimeUtil.getFormattedDateTime(startTimeValue));
        } else {
            startTime.setText(SEPARATOR);
        }
        if (task.hasRemarks()) {
            remarks.setText(task.getRemarks().value);
        } else {
            remarks.setText(SEPARATOR);
        }
        initTags(task);
    }
//@@author

    private void initTags(ReadOnlyTask task) {
        task.getLabels().forEach(label -> tags.getChildren().add(new Label(label.labelName)));
    }
}
