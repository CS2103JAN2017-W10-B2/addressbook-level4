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

1. Download the latest `doitdoit!!.jar` from the [releases](../../../releases) tab.
2. Copy the file to the folder you want to use as the home folder for your doitdoit!!.
3. Double-click the file to start the app. The GUI should appear in a few seconds.
   > <img src="images/Ui.png" width="600">

4. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window.
5. Some example commands you can try:
   * **`list`** : lists all tasks
   * **`add`**` study for the finals from: today till: next thursday` :
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

Adds a Task to doitdoit!!<br>
Format: `add TITLE (from: STARTTIME) (till: DEADLINE) (remark: REMARK) (#LABELS...)`
OR: `add TITLE (from: STARTTIME) (due: DEADLINE) (remark: REMARK) (#LABELS...)`

> Other than TITLE, all other information is purely optional.
> STARTTIME and DEADLINE inputs are very flexible!
> Try something like `day after like next thursday`, or `4 o'clock`.
> The program however takes in the date and time in American format.
> Meaning that it takes the date in the form month, day, year if the calendar date is given.
> Time is in 24hr format and is defaulted at current time if not specified.

Examples:

* `add Complete Assignment 1 from: now till: friday remark: 20% of final grade #Assignment`<br>
* `add Revise tutorial 1 #Assignment`<br>
* `add Do CS2103 T7 till: thursday 8pm remarks: Remember to make pull request on github #School`<br>
* `add Buy gift for mom due:7th September #Birthday #Family`<br>
* `add Finish studing for mid term from: now till: tomorrow #School #Exam`<br>

### 2.3. Listing tasks : `list`

Shows tasks of specified types in doitdoit!!.<br>
Format: `list TYPE`

> List tasks of the specified TYPE.
> Three TYPES are available: 'all', 'ongoing', 'completed'.

Examples:

* `list ongoing
  Lists the ongoing tasks.


### 2.4. Editing a task : `edit`

Edits an existing task in doitdoit!!.<br>
Format: `edit INDEX (TITLE) (from: STARTTIME) (till: DEADLINE) (remark: REMARK) (#LABELS...)`
OR: `edit INDEX (TITLE) (from: STARTTIME) (due: DEADLINE) (remarks: REMARK) (#LABELS...)`

> * Edits the task at the specified `INDEX`.
    The index refers to the index number shown in the last task listing.<br>
    The index **must be a positive integer** 1, 2, 3, ...
> * At least one of the optional fields must be provided.
> * Existing values will be updated to the input values.
> * Clear all LABELS by entering ` #`. Other fields cannot be cleared.

Examples:

* `edit 1 due: next Thursday`<br>
  Edits the due date of the 1st task to be next Thursday.
* `edit 2 CS2103 homework #`<br>
  Edits the TITLE of the 2nd task to be `CS2103 homework` and clears all existing LABELS.

### 2.5. Finding all tasks containing any keyword in their task title or remark: `find`

Finds tasks based on any of the given keywords.<br>
Format: `find KEYWORDS (MORE_KEYWORDS...)`

> * The search is not case sensitive. e.g `homework`(in task) will match `Homework` (keyword)
> * The order of the keywords does not matter. e.g. `Math Homework` will match `Homework Math`
> * Tasks matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `Homework` will match `Math Homework`
> * Only the task title and remarks are searched for keywords (substring match).
> * substrings will be matched e.g. `Homeworks` will match keyword `Homework`

Examples:

* `find homework`<br>
  Returns `CS2103 homework` and `EE4212 Homeworks`
* `find homework CS2103 EG2401`<br>
  Returns Any task having following words containing the substring in task summary `homework`, `CS2103`, or `EG2401`

### 2.6. Finding all tasks containing any exact keyword match in their task label: `find`
Finds tasks based on any of the given keywords.<br>
Format: `find #LABEL_KEYWORDS (#MORE_LABEL_KEYWORDS...)`

> * The search is not case sensitive. e.g `#important`(in task) will match `#IMPORTANT`(label keyword)
> * Label search: full word is matched. (full word match). e.g `#graded`(in task) will not match `#grade`(label keyword)

Examples:

  * `find #Assignment`<br>
  Returns `MA1100 #Assignment` and `MA3252 #assignment`

### 2.7. Deleting a task : `delete`

Deletes the specified task from doitdoit!!. Can be undone.<br>
Format: `delete INDEX`

> Deletes the task at the specified `INDEX`. <br>
> The index refers to the index number shown in the most recent listing.<br>
> The index **must be a positive integer** 1, 2, 3, ...

Examples:

* `list`<br>
  `delete 2`<br>
  Deletes the 2nd task in the doitdoit!!.
* `find homework`<br>
  `delete 1`<br>
  Deletes the 1st task in the results of the `find` command.

### 2.8. Completing a task : `edit INDEX c/`

Marks the specified task from the doitdoit!! as complete. Can be undone.<br>
Format: `edit INDEX c/completion_status`

> Marks the task at the specified `INDEX` ad completed or not completed. <br>
> The index refers to the index number shown in the most recent listing.<br>
> The index **must be a positive integer** 1, 2, 3, ...

Examples:

* `list`<br>
  `edit 3 c/yes`<br>
  Marks the 3rd task in the doitdoit!! as complted.
  Hence the task is no longer displayed as by default ongoing tasks are shown after each edit .
* `list completed `<br>
  `edit 2 c/no`<br>
  Marks the 2nd task in the completed task list as incompleted.
  After this, the list of ongoing tasks will be shown.

### 2.9. Undo last action : `undo`

Undo last action from the doitdoit!!. Able to undo up to last 11 actions.<br>
Format: `undo`

### 2.10. Set storage location : `set_path LOCATION`

Set the storage location as desired. This can be used to store data at a folder linked to the cloud.
Note that after set a new storage location, the application need to restart.
Note that a '.xml' file needs to be specified in the LOCATION paratmeter.<br>
Format: `set_path LOCATION`

Examples:

* `set_path f:/doitdoit!!.xml`<br>
Sets the stroage file to be 'doitdoit!!.xml' under disk f.

* `set_path default`
Sets the storage file to be default name under default folder.
### 2.8. Clearing all entries : `clear`

Clears all entries from the doitdoit!!. Can be undone<br>
Format: `clear`

### 2.11. Exiting the program : `exit`

Exits the program.<br>
Format: `exit`

### 2.12. Saving the data

doitdoit!! data are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.

## 3. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with
       the file that contains the data of your previous doitdoit!! folder.

## 4. Command Summary

Command | User input format
-------- | :--------
**Help** | `help`
**Add** | `add TITLE (from: STARTTIME) (till/due: DEADLINE) (remarks: REMARKS) (#LABELS...)`
**List** | `list`
**Edit** | `edit INDEX (TITLE) (from: STARTTIME) (till/due: DEADLINE) (remarks: REMARKS) (#LABELS)`
**Find** | `find (KEYWORDS) (LABEL_KEYWORDS)`
**Delete** | `delete INDEX`
**Complete** | `edit INDEX c/`
**Undo** | `undo`
**Set Storage Path** | `set_path LOCATION`
**Clear** | `clear`
**Exit** | `exit`
