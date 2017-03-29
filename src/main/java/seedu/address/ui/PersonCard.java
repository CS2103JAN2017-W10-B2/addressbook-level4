package seedu.address.ui;

import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.TimeUtil;
import seedu.address.model.task.ReadOnlyTask;

public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

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

    public PersonCard(ReadOnlyTask person, int displayedIndex) {
        super(FXML);
        name.setText(person.getTitle().fullTitle);
        id.setText(displayedIndex + ". ");
        phone.setText("");
        address.setText("");
        email.setText("");
        if (person.hasDeadline()) {
            LocalDateTime deadline = TimeUtil.getDateTime(person.getDeadline().value);
            phone.setText("Deadline: " + deadline.toLocalDate().toString());
        }
        if (person.hasStartTime()) {
            LocalDateTime startTime = TimeUtil.getDateTime(person.getStartTime().value);
            address.setText("Start Time: " + startTime.toLocalDate().toString());
        }
        if (person.hasRemarks()) {
            email.setText(person.getRemarks().value);
        }
        initTags(person);
    }

    private void initTags(ReadOnlyTask person) {
        person.getLabels().forEach(tag -> tags.getChildren().add(new Label(tag.labelName)));
    }
}
