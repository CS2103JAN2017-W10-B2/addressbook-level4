package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.ReadOnlyTask;

public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label deadline;
    @FXML
    private Label address;
    @FXML
    private Label remarks;
    @FXML
    private FlowPane label;

    public PersonCard(ReadOnlyTask person, int displayedIndex) {
        super(FXML);
        title.setText(person.getTitle().fullTitle);
        id.setText(displayedIndex + ". ");
        deadline.setText(person.getDeadline().value);
        address.setText(person.getNot_in_use().value);
        remarks.setText(person.getRemarks().value);
        initTags(person);
    }

    private void initTags(ReadOnlyTask person) {
        person.getLabels().forEach(tag -> label.getChildren().add(new Label(tag.labelName)));
    }
}
