# doitdoit!! - User Guide

By : `Team Just Do It`  &nbsp;&nbsp;&nbsp;&nbsp; Since: `March 2017`  &nbsp;&nbsp;&nbsp;&nbsp;

---

1. [Quick Start](#quick-start)
2. [Features](#features)
3. [FAQ](#faq)
4. [Command Summary](#command-summary)

## 1. Quick Start

0. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>

   > Having any Java 8 version is not enough. <br>
   > This app will not work with earlier versions of Java 8.

1. Download the latest `todolist.jar` from the [releases](../../../releases) tab.
2. Copy the file to the folder you want to use as the home folder for your Address Book.
3. Double-click the file to start the app. The GUI should appear in a few seconds.
   > <img src="images/Ui.png" width="600">

4. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window.
5. Some example commands you can try:
   * **`list`** : lists all contacts
   * **`add`**` study for the finals from today till next thursday` :
     adds a new task to study for the finals until next thursday.
   * **`delete`**` 1` : deletes the first task shown in the current list
   * **`exit`** : exits the app
6. Refer to the [Features](#features) section below for details of each command.<br>


## 2. Features

> **Command Format**
>
> * `PARAMETER NAME` all caps means to enter the parameters this must have a value!
> * `(OPTIONAL PARAMETER)` this bracket means that parameter is optional.
> * `...` which follows parameter mean that the parameter can have multiple inputs.
> * Parameters can be in any order and of any length.

### 2.1. Viewing help : `help`

Format: `help`

> Help is also shown if you enter an incorrect command e.g. `abcd`
> So just type something random to get a quick guide on the commands!

### 2.2. Adding a task: `add`

Adds a Task to the task list<br>
Format: `add TITLE (from STARTTIME) (till DEADLINE) (remark: REMARKS) (label: LABELS...)`

> Other than TITLE, all other information is purely optional
> STARTTIME and DEADLINE inputs are very flexible!
> Try something like `day after like next thursday`, or `4 o'clock`.
> The program however takes in the date and time in American format.
> Meaning that it takes the date in the form month, day, year if the calendar date is given.
> Time is in 24hr format and is defaulted at current time if not specified.

Examples:

* `add Complete Assignment 1 from now till friday remark: 20% of final grade label: Uni Assignment`<br>
* `add Revise tutorial 1 label: Uni Assignment`<br>
* `add Do CS2103 T7 till thursday 8pm remark: Remember to make pull request on github label: School`<br>
* `add Buy gift for mom till 7th September label: Birthday Family`<br>
* `add Finish studing for mid term from now till tomorrow label: School Exam`<br>

### 2.3. Listing all tasks : `list`

Shows a list of all tasks in the to do list.<br>
Format: `list`

### 2.4. Editing a task : `edit`

Edits an existing task in the ToDoList.<br>
Format: `edit INDEX (TITLE) (from STARTTIME) (till DEADLINE) (remark: REMARKS) (label: LABELS)`

> * Edits the task at the specified `INDEX`.
    The index refers to the index number shown in the last task listing.<br>
    The index **must be a positive integer** 1, 2, 3, ...
> * At least one of the optional fields must be provided.
> * Existing values will be updated to the input values.
> * Clear all LABELS by entering `label: `, similarly for REMARKS, type `remark:`

Examples:

* `edit 1 till next thursday`<br>
  Edits the due date of the 1st task to be next thursday.
* `edit 2 CS2103 homework label:`<br>
  Edits the TITLE of the 2nd task to be `CS2103 homework` and clears all existing LABELS.

### 2.5. Finding all tasks containing any keyword in their task summary: `find`

Finds tasks whose task summarys contain any of the given keywords.<br>
Format: `find KEYWORD (MORE_KEYWORDS)`

> * The search is case sensitive. e.g `hans` will not match `Hans`
> * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
> * Only the task summary is searched.
> * Only full words will be matched e.g. `Han` will not match `Hans`
> * Tasks matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Hans` will match `Hans Bo`

Examples:

* `find homework`<br>
  Returns `CS2103 homework` but not `EE4212 Homework`
* `find homework CS2103 EG2401`<br>
  Returns Any task having following words in task summary `homework`, `CS2103`, or `EG2401`

### 2.6. Deleting a task : `delete`

Deletes the specified task from the todolist. Can be undone.<br>
Format: `delete INDEX`

> Deletes the task at the specified `INDEX`. <br>
> The index refers to the index number shown in the most recent listing.<br>
> The index **must be a positive integer** 1, 2, 3, ...

Examples:

* `list`<br>
  `delete 2`<br>
  Deletes the 2nd task in the todolist.
* `search homework`<br>
  `delete 1`<br>
  Deletes the 1st task in the results of the `search` command.

### 2.7. Select a Task : `select`

Selects the task identified by the index used in the task listing.<br>
Format: `select INDEX`

> Selects the task and loads the task detail according to the 'TITLE`.<br>
> The Title is ina string format

Examples:

* `list`<br>
  `select Assignment 1`<br>
  Selects Assignment 1 in the task list.

### 2.8. Completing a task : `complete`

Marks the specified task from the todolist as complete. Can be undone.<br>
Format: `complete INDEX`

> Marks the task at the specified `INDEX` complete. <br>
> The index refers to the index number shown in the most recent listing.<br>
> The index **must be a positive integer** 1, 2, 3, ...

Examples:

* `list`<br>
  `complete 3`<br>
  Deletes the 3rd task in the todolist.
* `search reports`<br>
  `delete 2`<br>
  Deletes the 2nd task in the results of the `search` command.

### 2.9. Clearing all entries : `clear`

Clears all entries from the todolist.<br>
Format: `clear`

### 2.10. Exiting the program : `exit`

Exits the program.<br>
Format: `exit`

### 2.11. Saving the data

Todolist data are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.

## 3. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with
       the file that contains the data of your previous Address Book folder.

## 4. Command Summary

* **Help** : `help` <br>
  e.g.

* **Add**  `add TITLE (from STARTTIME) (till DEADLINE) (remark: REMARKS) (label: LABELS...)` <br>
  e.g. `add Do CS2103 T7 from today till last day of month remark: Remember to do properly`

* **List** : `list` <br>
  e.g.

* **Edit** : `edit INDEX (TITLE) (from STARTTIME) (till DEADLINE) (remark: REMARKS) (label: LABELS)` <br>
  e.g. `edit 2 CS2103 homework label: `<br>

* **Find** : `find KEYWORD (MORE_KEYWORDS)` <br>
  e.g. `find homework`

* **Delete** : `delete INDEX` <br>
   e.g. `delete 3`

* **Select** : `select INDEX` <br>
  e.g.`select 2`

* **Complete** : `complete INDEX` <br>
  e.g.`complete 3`<br>

* **Clear** : `clear`

* **Exit** : `exit` <br>
  e.g.


