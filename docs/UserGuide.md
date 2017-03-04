# doitdoit!! - User Guide

By : `Team SE-EDU`  &nbsp;&nbsp;&nbsp;&nbsp; Since: `Jun 2016`  &nbsp;&nbsp;&nbsp;&nbsp; Licence: `MIT`
TODO: Ray[List], Rei[Delete, Complete], ZongQi[edit, search], Irfan[add, select]

---

1. [Quick Start](#quick-start)
2. [Features](#features)
3. [FAQ](#faq)
4. [Command Summary](#command-summary)

## 1. Quick Start

0. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>

   > Having any Java 8 version is not enough. <br>
   > This app will not work with earlier versions of Java 8.

1. Download the latest `addressbook.jar` from the [releases](../../../releases) tab.
2. Copy the file to the folder you want to use as the home folder for your Address Book.
3. Double-click the file to start the app. The GUI should appear in a few seconds.
   > <img src="images/Ui.png" width="600">

4. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window.
5. Some example commands you can try:
   * **`list`** : lists all contacts
   * **`add`**` John Doe p/98765432 e/johnd@gmail.com a/John street, block 123, #01-01` :
     adds a contact named `John Doe` to the Address Book.
   * **`delete`**` 3` : deletes the 3rd contact shown in the current list
   * **`exit`** : exits the app
6. Refer to the [Features](#features) section below for details of each command.<br>


## 2. Features

> **Command Format**
>
> * Words in `UPPER_CASE` are the parameters.
> * Items in `SQUARE_BRACKETS` are optional.
> * Items with `...` after them can have multiple instances.
> * Parameters can be in any order.

### 2.1. Viewing help : `help`

Format: `help`

> Help is also shown if you enter an incorrect command e.g. `abcd`

### 2.2. Adding a task: `add`

Adds a Task to the task list<br>
Format: `add TITLE [e/END_DATE_DDMMYYYY] [r/REMARKS] [l/LABELS]...`

> Other than TITLE, all other information is optional
> Each task can have any number of tags (including 0)
> To add more than 1 labels, space out the labels with colon ","

Examples:

* `add Complete Assignment 1 e/08022017 r/20% of final grade l/Uni Assignment`<br>
* `add Revise tutorial 1 l/Uni Assignment`<br>
* `add Do CS2103 T7 d/080317 r/Remember to make pull request on github l/School`<br>
* `add Buy gift for mom d/20042017 l/Birthday, Family`<br>
* `add Finish studing for mid term d/06032017 l/School, Exam`<br>

### 2.3. Listing all persons : `list`

Shows a list of all persons in the address book.<br>
Format: `list`

### 2.4. Editing a task : `edit`

Edits an existing task in the ToDoList.<br>
Format: `edit INDEX [TASK_SUMMARY] [d/DUE_DATE_DDMMYY] [r/REMARKS] [l/LABELS]...`

> * Edits the task at the specified `INDEX`.
    The index refers to the index number shown in the last task listing.<br>
    The index **must be a positive integer** 1, 2, 3, ...
> * At least one of the optional fields must be provided.
> * Existing values will be updated to the input values.
> * When editing labels, the existing labels of the task will be removed i.e adding of labels is not cumulative.
> * You can remove all the person's labels by typing `l/` without specifying any labels after it.

Examples:

* `edit 1 d/05032017`<br>
  Edits the due date of the 1st task to be `05032017` respectively.
* `edit 2 CS2103 homework l/`<br>
  Edits the task summary of the 2nd task to be `CS2103 homework` and clears all existing labels.

### 2.5. Finding all tasks containing any keyword in their task summary: `find`

Finds tasks whose task summarys contain any of the given keywords.<br>
Format: `find KEYWORD [MORE_KEYWORDS]`

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

Selects the task identified by the title used in the task listing.<br>
Format: `select TITLE`

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

* **Add**  `add NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]...` <br>
  e.g. `add James Ho p/22224444 e/jamesho@gmail.com a/123, Clementi Rd, 1234665 t/friend t/colleague`

* **Clear** : `clear`

* **Delete** : `delete INDEX` <br>
   e.g. `delete 3`

* **Find** : `find KEYWORD [MORE_KEYWORDS]` <br>
  e.g. `find James Jake`

* **List** : `list` <br>
  e.g.

* **Help** : `help` <br>
  e.g.

* **Select** : `select INDEX` <br>
  e.g.`select 2`


