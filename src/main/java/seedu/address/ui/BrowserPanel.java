package seedu.address.ui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.util.FxViewUtil;
import seedu.address.model.task.ReadOnlyTask;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    private static final String FXML = "BrowserPanel.fxml";

    @FXML
    private VBox browser;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * @param placeholder The AnchorPane where the BrowserPanel must be inserted
     */
    public BrowserPanel(AnchorPane placeholder) {
        super(FXML);
        placeholder.setOnKeyPressed(Event::consume); // To prevent triggering events for typing inside the
        // loaded Web page.
        FxViewUtil.applyAnchorBoundaryParameters(browser, 0.0, 0.0, 0.0, 0.0);
        placeholder.getChildren().add(browser);
    }

    public void loadPersonPage(ReadOnlyTask person) {
        name.setText(person.getTitle().fullTitle);
        phone.setText("");
        address.setText("");
        email.setText("");
        if (person.hasDeadline()) {
            phone.setText(person.getDeadline().value);
        }
        if (person.hasStartTime()) {
            address.setText(person.getStartTime().value);
        }
        if (person.hasRemarks()) {
            email.setText(person.getRemarks().value);
        }
        initTags(person);
    }

    private void initTags(ReadOnlyTask person) {
        tags.getChildren().clear();
        person.getLabels().forEach(tag -> tags.getChildren().add(new Label(tag.labelName)));
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

}
