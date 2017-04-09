# doitdoit!! - Test Script

By : `Team Just Do It`  &nbsp;&nbsp;&nbsp;&nbsp; Since: `March 2017`  &nbsp;&nbsp;&nbsp;&nbsp;

---

## Loading Data

1. Prerequisite: **JDK `1.8.0_60`**  or later<br>
2. Copy [W10-B2][doitdoit!!].jar and SampleData.xml into the same folder.
3. double click on jar file to run the application.
4. Run command: `set_path [current directory]/SampleData.xml`
5. Restart Application.

## Quick Start Guide

* **Introduction** doitdoit!! is a to do list application that allows users to store and keep track of tasks they need to do. 

* **Task Definitions** Tasks contains:
  * Title (compulsory) - short summary of task
  * Start time -  used to indicate start of task with a timing
  * Deadline - used to indicate end of task with a timing. 
  * Remark - more details of a task
  * Label - categories to assign tasks to for easy reference
  * Completion status - false (not completed) by default
  As only title is compulsory, many different types of tasks, such as events, may be stored.

* **Layout** The Graphical User Interface (GUI) of doitdoit!! contains from top to bottom:
  * Tasks Panel - Title of task is displayed next to their index. Labels are displayed in grey boxes below the title. Start time, end time and remark are displayed in vertical order.
  * Feedback panel - Shows results of command execution.
  * Command box - Commands from user are input here.

## Testing

### Add Command

Command Format: `add TITLE (from: STARTTIME) (till: DEADLINE) (remarks: REMARKS) (#LABELS...)`
OR: `add TITLE (from: STARTTIME) (due: DEADLINE) (remarks: REMARKS) (#LABELS...)`

Scenario | Input Command  | Expected task panel results | Expected command box results
------------ | ------------- | -------------  | ------------- 
Add a task with all fields | `add go on a diet from: 04/12/17 12am till: next Sunday 12am remark: avoid oily and fatty foods #health #greens` | Task entered at index 20 | New task added: go on a diet Start Time: 0:00am 10 Apr 2017 Deadline: 0:00am 16 Apr 2017 Remarks: avoid oily and fatty foods Labels: [health][greens]
Add a task with deadline | `add finish math assignment due: 04/19/17 2pm remark: submit during lecture #graded #math` | Task entered at index 20 | New task added: finish math assignment Deadline: 2:00pm 19 Apr 2017 Remarks: submit during lecture Labels: [graded][math]
Add a task with title only | `add send email to boss` | Task entered at index 1 | New task added: send email to boss

### List Command

Command Format: `list [type]`
List tasks of the specified type. Three types are available: 'all', 'ongoing', 'completed'.

Scenario | Input Command  | Expected task panel results | Expected command box results
------------ | ------------- | -------------  | ------------- 
List all tasks. | `list all` | 54 tasks listed | Listed all tasks
List completed tasks. | `list completed` | 0 tasks listed |Listed completed tasks
List ongoing tasks. | `list ongoing` | 54 tasks listed | Listed ongoing tasks
List incorrect syntax. | `List` | Command box turns red. |Unknown command. Type 'help' to get help!

## Edit Command

Command Format: `edit INDEX (TITLE) (from: STARTTIME) (till: DEADLINE) (remarks: REMARKS) (#LABELS)` 
OR: `edit INDEX (TITLE) (from: STARTTIME) (due: DEADLINE) (remarks: REMARKS) (#LABELS)`

Scenario | Input Command  | Expected task panel results | Expected command box results
------------ | ------------- | -------------  | ------------- 
Edit title of a task. | `edit 7 Go buy stationeries` | Task updated at index 7 | Edited Task: Go buy stationeries Start Time: 1:31am 11 Apr 2017 Deadline: 1:31am 11 Apr 2017
Edit deadline of a task. | `edit 9 due: 04/14/17 4pm` | Task updated at index 7 | Edited Task: FIN3101 project Start Time: 1:31am 6 Apr 2017 Deadline: 4:00pm 14 Apr 2017
Edit to remaove labels. | `dit 2 #` | Task updated at index 2 | Edited Task: Go buy pizza Deadline: 1:31am 6 Apr 2017

## Find Command

Command Format: `find (KEYWORDS...) (#LABEL_KEYWORDS...)` 

Scenario | Input Command  | Expected task panel results | Expected command box results
------------ | ------------- | -------------  | ------------- 
Find all dinner plans. | `find dinner` | 7 tasks listed in task panel. | 7 task(s) listed!
Find tasks with label 'urgent'. | `find #urgent` | 8 tasks listed in task panel. | 8 task(s) listed!
Find tasks with label 'urgen'. | `find #urgen` | 0 tasks listed in task panel. | 0 task(s) listed!
Find all project related tasks. | `find project`| 6 tasks listed in task panel. | 6 task(s) listed!

## Delete Command

Command Format: `delete INDEX` 

Scenario | Input Command  | Expected task panel results | Expected command box results
------------ | ------------- | -------------  | ------------- 
Delete a task | `delete 1` | Task specified is deleted and removed from task panel. Remaining tasks are renumbered. | Deleted task: QF3101 project Deadline: 4:00pm 11 Apr 2017 Remarks: urgent

## Complete Command

Command Format: `edit INDEX c/completion_status` 
completion_status available are: "yes", "no"

Scenario | Input Command  | Expected task panel results | Expected command box results
------------ | ------------- | -------------  | ------------- 
Complete a task | `edit 3 c/yes` | 52 tasks listed. | Edited Task: FIN3101 project Start Time: 1:31am 6 Apr 2017 Deadline: 4:00pm 14 Apr 2017 Completion: true
Mark an incomplete task as incomplete | `edit 6 c/no` | no change | Edited Task: Stock up on pizza Start Time: 1:31am 6 Apr 2017 Deadline: 1:31am 11 Apr 2017 Labels: [urgent]
Mark a complete task as incomplete | `list completed` `edit 1 c/no` | 53 tasks listed. Editted task found at index 29. | Edited Task: FIN3101 project Start Time: 1:31am 6 Apr 2017 Deadline: 4:00pm 14 Apr 2017

## Undo Command

Command Format: `undo` 

Scenario | Input Command  | Expected task panel results | Expected command box results
------------ | ------------- | -------------  | ------------- 
Undo an action | `delete 1` | 52 tasks listed.  | Previous command undone!

## Clear Command

Command Format: `clear` 

Scenario | Input Command  | Expected task panel results | Expected command box results
------------ | ------------- | -------------  | ------------- 
Clears all data in to do list. | `clear` | 0 task listed.  | doitdoit!! has been cleared!

## Set storage Command

Command Format: `set_path LOCATION` 

Scenario | Input Command  | Expected task panel results | Expected command box results
------------ | ------------- | -------------  | ------------- 
Specify location and file name of storage file. | `set_path newFileName.xml` | 0 task listed.  | New storage path set: newFileName.xml. Please restart the App.

## Exit Command

Command Format: `exit` 

Scenario | Input Command  | Expected task panel results | Expected command box results
------------ | ------------- | -------------  | ------------- 
Exits doitdoit!!  | `exit` | doitodit!! closes.  | -
