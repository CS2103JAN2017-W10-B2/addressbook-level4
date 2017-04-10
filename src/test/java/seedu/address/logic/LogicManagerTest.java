package seedu.address.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.common.eventbus.Subscribe;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.model.ToDoListChangedEvent;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.events.ui.ShowHelpRequestEvent;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyToDoList;
import seedu.address.model.ToDoList;
import seedu.address.model.label.Label;
import seedu.address.model.label.UniqueLabelList;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.model.task.Remarks;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.storage.StorageManager;

public class LogicManagerTest {

    /**
     * See https://github.com/junit-team/junit4/wiki/rules#temporaryfolder-rule
     */
    @Rule
    public TemporaryFolder saveFolder = new TemporaryFolder();

    private Model model;
    private Logic logic;

    // These are for checking the correctness of the events raised
    private ReadOnlyToDoList latestSavedToDoList;
    private boolean helpShown;
    private int targetedJumpIndex;

    @Subscribe
    private void handleLocalModelChangedEvent(ToDoListChangedEvent abce) {
        latestSavedToDoList = new ToDoList(abce.data);
    }

    @Subscribe
    private void handleShowHelpRequestEvent(ShowHelpRequestEvent she) {
        helpShown = true;
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent je) {
        targetedJumpIndex = je.targetIndex;
    }

    @Before
    public void setUp() {
        model = new ModelManager();
        String tempAddressBookFile = saveFolder.getRoot().getPath() + "TempAddressBook.xml";
        String tempPreferencesFile = saveFolder.getRoot().getPath() + "TempPreferences.json";
        logic = new LogicManager(model, new StorageManager(tempAddressBookFile, tempPreferencesFile));
        EventsCenter.getInstance().registerHandler(this);

        latestSavedToDoList = new ToDoList(model.getToDoList()); // last saved
                                                                 // assumed to
                                                                 // be up to
                                                                 // date
        helpShown = false;
        targetedJumpIndex = -1; // non yet
    }

    @After
    public void tearDown() {
        EventsCenter.clearSubscribers();
    }

    @Test
    public void execute_invalid() {
        String invalidCommand = "       ";
        assertCommandFailure(invalidCommand, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

    /**
     * Executes the command, confirms that a CommandException is not thrown and
     * that the result message is correct. Also confirms that both the 'address
     * book' and the 'last shown list' are as specified.
     *
     * @see #assertCommandBehavior(boolean, String, String, ReadOnlyToDoList,
     *      List)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage, ReadOnlyToDoList expectedToDoList,
            List<? extends ReadOnlyTask> expectedShownList) {
        assertCommandBehavior(false, inputCommand, expectedMessage, expectedToDoList, expectedShownList);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that
     * the result message is correct. Both the 'address book' and the 'last
     * shown list' are verified to be unchanged.
     *
     * @see #assertCommandBehavior(boolean, String, String, ReadOnlyToDoList,
     *      List)
     */
    private void assertCommandFailure(String inputCommand, String expectedMessage) {
        ToDoList expectedToDoList = new ToDoList(model.getToDoList());
        List<ReadOnlyTask> expectedShownList = new ArrayList<>(model.getFilteredTaskList());
        assertCommandBehavior(true, inputCommand, expectedMessage, expectedToDoList, expectedShownList);
    }

    /**
     * Executes the command, confirms that the result message is correct and
     * that a CommandException is thrown if expected and also confirms that the
     * following three parts of the LogicManager object's state are as
     * expected:<br>
     * - the internal address book data are same as those in the
     * {@code expectedAddressBook} <br>
     * - the backing list shown by UI matches the {@code shownList} <br>
     * - {@code expectedAddressBook} was saved to the storage file. <br>
     */
    private void assertCommandBehavior(boolean isCommandExceptionExpected, String inputCommand, String expectedMessage,
            ReadOnlyToDoList expectedToDoList, List<? extends ReadOnlyTask> expectedShownList) {

        try {
            CommandResult result = logic.execute(inputCommand);
            assertFalse("CommandException expected but was not thrown.", isCommandExceptionExpected);
            assertEquals(expectedMessage, result.feedbackToUser);
        } catch (CommandException e) {
            assertTrue("CommandException not expected but was thrown.", isCommandExceptionExpected);
            assertEquals(expectedMessage, e.getMessage());
        }

        // Confirm the ui display elements should contain the right data
        assertEquals(expectedShownList, model.getFilteredTaskList());

        // Confirm the state of data (saved and in-memory) is as expected
        assertEquals(expectedToDoList, model.getToDoList());
        assertEquals(expectedToDoList, latestSavedToDoList);
    }

    @Test
    public void execute_unknownCommandWord() {
        String unknownCommand = "uicfhmowqewca";
        assertCommandFailure(unknownCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_help() {
        assertCommandSuccess("help", HelpCommand.SHOWING_HELP_MESSAGE, new ToDoList(), Collections.emptyList());
        assertTrue(helpShown);
    }

    @Test
    public void execute_exit() {
        assertCommandSuccess("exit", ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, new ToDoList(), Collections.emptyList());
    }

    @Test
    public void execute_clear() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        model.addTask(helper.generateTask(1));
        model.addTask(helper.generateTask(2));
        model.addTask(helper.generateTask(3));

        assertCommandSuccess("clear", ClearCommand.MESSAGE_SUCCESS, new ToDoList(), Collections.emptyList());
    }

    //@@author A0138831A
    /**
     * A test to test undo add
     * @throws Exception
     */
    @Test
    public void execute_undoAdd() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Model currmodel = model;
        model.addTask(helper.generateTask(1));
        model.addTask(helper.generateTask(2));
        model.undoTask();
        model.undoTask();

        assertEquals(currmodel, model);
    }

    /**
     * A test to test undo delete
     * @throws Exception
     */
    @Test
    public void execute_undoDelete() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        ReadOnlyTask target = helper.generateTask(2);
        Model currmodel = model;
        model.addTask((Task) target);
        model.deleteTask(target);
        model.undoTask();

        assertEquals(currmodel, model);
    }

    /**
     * A test to test undo clear
     * @throws Exception
     */
    @Test
    public void execute_undoClear() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        model.addTask(helper.generateTask(1));
        model.addTask(helper.generateTask(2));
        Model currmodel = model;
        model.resetData(new ToDoList());
        model.undoTask();

        assertEquals(currmodel, model);
    }

    /**
     * A test to test multiple undoes
     * @throws Exception
     */
    @Test
    public void execute_multipleUndo() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        ReadOnlyTask target = helper.generateTask(3);
        Model currmodel = model;
        model.addTask(helper.generateTask(1));
        model.addTask(helper.generateTask(2));
        model.addTask((Task) target);
        model.deleteTask(target);
        model.resetData(new ToDoList());
        model.undoTask();
        model.undoTask();
        model.undoTask();
        model.undoTask();
        model.undoTask();

        assertEquals(currmodel, model);
    }

    // @@author A0143132X
    @Test
    public void execute_add_invalidPersonData() {
        assertCommandFailure("add [\\;] from:12/12/17 remark:valid, remarks", Title.MESSAGE_TITLE_CONSTRAINTS);
        assertCommandFailure("add Valid Title till:goat stimulator", Deadline.MESSAGE_DEADLINE_CONSTRAINTS);
        assertCommandFailure("add Valid Title from:mamamia till:12/12/17", StartTime.MESSAGE_START_TIME_CONSTRAINTS);
        assertCommandFailure("add Valid Title remark:invalid#remarks", Remarks.MESSAGE_REMARKS_CONSTRAINTS);
        assertCommandFailure("add Valid Title #invalid_-[.label", Label.MESSAGE_LABEL_CONSTRAINTS);
    }
    // @@author

    @Test
    public void execute_add_successful() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Task toBeAdded = helper.ex1CS2103();
        ToDoList expectedAB = new ToDoList();
        expectedAB.addTask(toBeAdded);

        // execute command and verify result
        assertCommandSuccess(helper.generateAddCommand(toBeAdded), String.format(AddCommand.MESSAGE_SUCCESS, toBeAdded),
                expectedAB, expectedAB.getTaskList());

    }

    @Test
    public void execute_addDuplicate_notAllowed() throws Exception {
        // setup expectations
        TestDataHelper helper = new TestDataHelper();
        Task toBeAdded = helper.ex1CS2103();

        // setup starting state
        model.addTask(toBeAdded); // person already in internal address book

        // execute command and verify result
        assertCommandFailure(helper.generateAddCommand(toBeAdded), AddCommand.MESSAGE_DUPLICATE_TASK);

    }

    // @@author A0115333U
    @Test
    public void execute_list_showsAllTasks() throws Exception {
        // prepare expectations
        TestDataHelper helper = new TestDataHelper();
        ToDoList expectedAB = helper.generateToDoList(2);
        List<? extends ReadOnlyTask> expectedList = expectedAB.getTaskList();

        // prepare ToDoList state
        helper.addToModel(model, 2);

        assertCommandSuccess("list all", String.format(ListCommand.MESSAGE_SUCCESS, "all"), expectedAB, expectedList);
    }

    // @@author A0115333U
    @Test
    public void execute_list_showsOngoingTasks() throws Exception {
        // prepare expectations
        TestDataHelper helper = new TestDataHelper();
        ToDoList expectedAB = helper.generateToDoList(2);
        FilteredList<? extends ReadOnlyTask> expectedfilteredTasks;
        expectedfilteredTasks = new FilteredList<>(expectedAB.getTaskList());
        expectedfilteredTasks.setPredicate(ReadOnlyTask -> !ReadOnlyTask.getIsCompleted());

        // prepare ToDoList state
        helper.addToModel(model, 2);

        assertCommandSuccess("list ongoing", String.format(ListCommand.MESSAGE_SUCCESS, "ongoing"), expectedAB,
                expectedfilteredTasks);
    }

    // @@author A0115333U
    @Test
    public void execute_list_showsCompletedTasks() throws Exception {
        // prepare expectations
        TestDataHelper helper = new TestDataHelper();
        ToDoList expectedAB = helper.generateToDoList(2);
        FilteredList<? extends ReadOnlyTask> expectedfilteredTasks;
        expectedfilteredTasks = new FilteredList<>(expectedAB.getTaskList());
        expectedfilteredTasks.setPredicate(ReadOnlyTask -> ReadOnlyTask.getIsCompleted());

        // prepare ToDoList state
        helper.addToModel(model, 2);

        assertCommandSuccess("list completed", String.format(ListCommand.MESSAGE_SUCCESS, "completed"), expectedAB,
                expectedfilteredTasks);
    }
    // @@author

    /**
     * Confirms the 'invalid argument index number behaviour' for the given
     * command targeting a single person in the shown list, using visible index.
     *
     * @param commandWord
     *            to test assuming it targets a single person in the last shown
     *            list based on visible index.
     */
    private void assertIncorrectIndexFormatBehaviorForCommand(String commandWord, String expectedMessage)
            throws Exception {
        assertCommandFailure(commandWord, expectedMessage); // index missing
        assertCommandFailure(commandWord + " +1", expectedMessage); // index
                                                                    // should be
                                                                    // unsigned
        assertCommandFailure(commandWord + " -1", expectedMessage); // index
                                                                    // should be
                                                                    // unsigned
        assertCommandFailure(commandWord + " 0", expectedMessage); // index
                                                                   // cannot be
                                                                   // 0
        assertCommandFailure(commandWord + " not_a_number", expectedMessage);
    }

    /**
     * Confirms the 'invalid argument index number behaviour' for the given
     * command targeting a single person in the shown list, using visible index.
     *
     * @param commandWord
     *            to test assuming it targets a single person in the last shown
     *            list based on visible index.
     */
    private void assertIndexNotFoundBehaviorForCommand(String commandWord) throws Exception {
        String expectedMessage = MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
        TestDataHelper helper = new TestDataHelper();
        List<Task> personList = helper.generateTaskList(2);

        // set AB state to 2 persons
        model.resetData(new ToDoList());
        for (Task p : personList) {
            model.addTask(p);
        }

        assertCommandFailure(commandWord + " 3", expectedMessage);
    }

    @Test
    public void execute_selectInvalidArgsFormat_errorMessageShown() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE);
        assertIncorrectIndexFormatBehaviorForCommand("select", expectedMessage);
    }

    @Test
    public void execute_selectIndexNotFound_errorMessageShown() throws Exception {
        assertIndexNotFoundBehaviorForCommand("select");
    }

    // @@author A0115333U
    @Test
    public void execute_select_jumpsToCorrectPerson() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        List<Task> threePersons = helper.generateTaskList(3);

        ToDoList expectedAB = helper.generateToDoList(threePersons);
        FilteredList<? extends ReadOnlyTask> expectedfilteredTasks;
        expectedfilteredTasks = new FilteredList<>(expectedAB.getTaskList());
        expectedfilteredTasks.setPredicate(ReadOnlyTask -> !ReadOnlyTask.getIsCompleted());

        ToDoList expectedABdisplay = new ToDoList();
        for (ReadOnlyTask p : expectedfilteredTasks) {
            Task p1 = new Task(p);
            expectedABdisplay.addTask(p1);
            expectedABdisplay.sort_tasks();
        }

        helper.addToModel(model, threePersons);

        assertCommandSuccess("select 2", String.format(SelectCommand.MESSAGE_SELECT_PERSON_SUCCESS, 2), expectedAB,
                expectedABdisplay.getTaskList());
        assertEquals(1, targetedJumpIndex);
        assertEquals(model.getFilteredTaskList().get(1), expectedfilteredTasks.get(1));
    }
    // @@author

    @Test
    public void execute_deleteInvalidArgsFormat_errorMessageShown() throws Exception {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
        assertIncorrectIndexFormatBehaviorForCommand("delete", expectedMessage);
    }

    @Test
    public void execute_deleteIndexNotFound_errorMessageShown() throws Exception {
        assertIndexNotFoundBehaviorForCommand("delete");
    }

    // @@author A0115333U
    @Test
    public void execute_delete_removesCorrectPerson() throws Exception {
        // prepare Expectations
        TestDataHelper helper = new TestDataHelper();
        List<Task> fivePersons = helper.generateTaskList(5);

        ToDoList expectedAB = helper.generateToDoList(fivePersons);
        FilteredList<? extends ReadOnlyTask> expectedfilteredTasks;
        expectedfilteredTasks = new FilteredList<>(expectedAB.getTaskList());
        expectedfilteredTasks.setPredicate(ReadOnlyTask -> !ReadOnlyTask.getIsCompleted());

        ToDoList expectedABdisplay = new ToDoList();
        for (ReadOnlyTask p : expectedfilteredTasks) {
            Task p1 = new Task(p);
            expectedABdisplay.addTask(p1);
            expectedABdisplay.sort_tasks();
        }

        ReadOnlyTask todelete = expectedfilteredTasks.get(1);
        expectedABdisplay.removeTask(todelete);
        expectedAB.removeTask(todelete);

        // prepare ToDoList state
        helper.addToModel(model, fivePersons);

        assertCommandSuccess("delete 2", String.format(DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, todelete), expectedAB,
                expectedABdisplay.getTaskList());
    }
    // @@author

    @Test
    public void execute_find_invalidArgsFormat() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);
        assertCommandFailure("find ", expectedMessage);
    }

    @Test
    public void execute_find_matchesSubstringInTitles() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task pTarget1 = helper.generateTaskWithTitle("bla bla KEY bla");
        Task pTarget2 = helper.generateTaskWithTitle("bla KEY bla bceofeia");
        Task p1 = helper.generateTaskWithTitle("KE Y");
        Task p2 = helper.generateTaskWithTitle("KEYKEYKEY sduauo");

        List<Task> fourPersons = helper.generateTaskList(p1, pTarget1, p2, pTarget2);
        ToDoList expectedAB = helper.generateToDoList(fourPersons);
        List<Task> expectedList = helper.generateTaskList(pTarget1, p2, pTarget2);
        helper.addToModel(model, fourPersons);

        assertCommandSuccess("find KEY", Command.getMessageForTaskListShownSummary(expectedList.size()), expectedAB,
                expectedList);
    }

    @Test
    public void execute_find_isNotCaseSensitive() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task p1 = helper.generateTaskWithTitle("bla bla KEY bla");
        Task p2 = helper.generateTaskWithTitle("bla KEY bla bceofeia");
        Task p3 = helper.generateTaskWithTitle("key key");
        Task p4 = helper.generateTaskWithTitle("KEy sduauo");

        List<Task> fourPersons = helper.generateTaskList(p3, p1, p4, p2);
        ToDoList expectedAB = helper.generateToDoList(fourPersons);
        List<Task> expectedList = fourPersons;
        helper.addToModel(model, fourPersons);

        assertCommandSuccess("find KEY", Command.getMessageForTaskListShownSummary(expectedList.size()), expectedAB,
                expectedList);
    }

    @Test
    public void execute_find_matchesIfAnyKeywordPresent() throws Exception {
        TestDataHelper helper = new TestDataHelper();
        Task pTarget1 = helper.generateTaskWithTitle("bla bla KEY bla");
        Task pTarget2 = helper.generateTaskWithTitle("bla rAnDoM bla bceofeia");
        Task pTarget3 = helper.generateTaskWithTitle("key key");
        Task p1 = helper.generateTaskWithTitle("sduauo");

        List<Task> fourPersons = helper.generateTaskList(pTarget1, p1, pTarget2, pTarget3);
        ToDoList expectedAB = helper.generateToDoList(fourPersons);
        List<Task> expectedList = helper.generateTaskList(pTarget1, pTarget2, pTarget3);
        helper.addToModel(model, fourPersons);

        assertCommandSuccess("find key rAnDoM", Command.getMessageForTaskListShownSummary(expectedList.size()),
                expectedAB, expectedList);
    }

    /**
     * A utility class to generate test data.
     */
    class TestDataHelper {

        Task ex1CS2103() throws Exception {
            Title name = new Title("CS2103 Exercise 1");
            Deadline deadline = new Deadline("11/11/17");
            Remarks remark = new Remarks("adam@gmail.com");
            StartTime privateAddress = new StartTime("11/11/17");
            Label tag1 = new Label("tag1");
            Label tag2 = new Label("longertag2");
            UniqueLabelList tags = new UniqueLabelList(tag1, tag2);
            return new Task(name, deadline, remark, privateAddress, tags, false);
        }

        /**
         * Generates a valid person using the given seed. Running this function
         * with the same parameter values guarantees the returned person will
         * have the same state. Each unique seed will generate a unique Person
         * object.
         *
         * @param seed
         *            used to generate the person data field values
         */
        // @@author A0115333U
        Task generateTask(int seed) throws Exception {
            return new Task(new Title("Title " + seed), new Deadline("12/12/17"), new Remarks(seed + "@email"),
                    new StartTime("12/12/17"),
                    new UniqueLabelList(new Label("tag" + Math.abs(seed)), new Label("tag" + Math.abs(seed + 1))),
                    seed % 2 == 0);
        }
        // @@author

        /** Generates the correct add command based on the person given */
        String generateAddCommand(Task p) {
            StringBuffer cmd = new StringBuffer();

            cmd.append("add ");

            cmd.append(p.getTitle().toString());
            cmd.append(" remark:").append(p.getRemarks());
            cmd.append(" till:").append(p.getDeadline());
            cmd.append(" from:").append(p.getStartTime());

            UniqueLabelList tags = p.getLabels();
            for (Label t : tags) {
                cmd.append(" #").append(t.labelName);
            }

            return cmd.toString();
        }

        /**
         * Generates an AddressBook with auto-generated persons.
         */
        ToDoList generateToDoList(int numGenerated) throws Exception {
            ToDoList todoList = new ToDoList();
            addToToDoList(todoList, numGenerated);
            return todoList;
        }

        /**
         * Generates an AddressBook based on the list of Persons given.
         */
        ToDoList generateToDoList(List<Task> tasks) throws Exception {
            ToDoList todoList = new ToDoList();
            addToToDoList(todoList, tasks);
            return todoList;
        }

        /**
         * Adds auto-generated Person objects to the given AddressBook
         *
         * @param todoList
         *            The AddressBook to which the Persons will be added
         */
        void addToToDoList(ToDoList todoList, int numGenerated) throws Exception {
            addToToDoList(todoList, generateTaskList(numGenerated));
        }

        /**
         * Adds the given list of Persons to the given AddressBook
         */

        // @@author A0115333U
        void addToToDoList(ToDoList todoList, List<Task> tasksToAdd) throws Exception {
            for (Task p : tasksToAdd) {
                todoList.addTask(p);
                todoList.sort_tasks();
            }
        }
        // @@author

        /**
         * Adds auto-generated Person objects to the given model
         *
         * @param model
         *            The model to which the Persons will be added
         */
        void addToModel(Model model, int numGenerated) throws Exception {
            addToModel(model, generateTaskList(numGenerated));
        }

        /**
         * Adds the given list of Persons to the given model
         */
        void addToModel(Model model, List<Task> tasksToAdd) throws Exception {
            for (ReadOnlyTask p : tasksToAdd) {
                Task p1 = new Task(p);
                model.addTask(p1);
            }
        }

        /**
         * Generates a list of Persons based on the flags.
         */
        List<Task> generateTaskList(int numGenerated) throws Exception {
            List<Task> tasks = new ArrayList<>();
            for (int i = 1; i <= numGenerated; i++) {
                tasks.add(generateTask(i));
            }
            return tasks;
        }

        List<Task> generateTaskList(Task... tasks) {
            return Arrays.asList(tasks);
        }

        /**
         * Generates a Person object with given name. Other fields will have
         * some dummy values.
         */
        Task generateTaskWithTitle(String name) throws Exception {
            return new Task(new Title(name), new Deadline("1"), new Remarks("1@email"), new StartTime("House of 1"),
                    new UniqueLabelList(new Label("tag")), false);
        }
    }
}
