package seedu.address.storage;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.ToDoListChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyToDoList;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ToDoList data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ToDoListStorage toDoListStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(ToDoListStorage toDoListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.toDoListStorage = toDoListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    public StorageManager(String toDoListFilePath, String userPrefsFilePath) {
        this(new XmlAddressBookStorage(toDoListFilePath), new TimUserPrefsStorage(userPrefsFilePath));
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ToDoList methods ==============================

    @Override
    public String getToDoListFilePath() {
        return toDoListStorage.getToDoListFilePath();
    }

    @Override
    public Optional<ReadOnlyToDoList> readToDoList() throws DataConversionException, IOException {
        return readToDoList(toDoListStorage.getToDoListFilePath());
    }

    @Override
    public Optional<ReadOnlyToDoList> readToDoList(String filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return toDoListStorage.readToDoList(filePath);
    }

    @Override
    public void saveToDoList(ReadOnlyToDoList toDoList) throws IOException {
        saveToDoList(toDoList, toDoListStorage.getToDoListFilePath());
    }

    @Override
    public void saveToDoList(ReadOnlyToDoList toDoList, String filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        toDoListStorage.saveToDoList(toDoList, filePath);
    }


    @Override
    @Subscribe
    public void handleToDoListChangedEvent(ToDoListChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveToDoList(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

}
