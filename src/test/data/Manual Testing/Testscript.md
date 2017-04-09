# doitdoit!! - Test Script

By : `Team Just Do It`  &nbsp;&nbsp;&nbsp;&nbsp; Since: `March 2017`  &nbsp;&nbsp;&nbsp;&nbsp;

---

## Loading Data

1. Prerequisite: **JDK `1.8.0_60`**  or later<br>
2. Copy [W10-B2][doitdoit!!].jar and SampleData.xml into the same folder.
3. double click on jar file to run the application.
4. Run command: `set_path [current directory]/SampleData.xml`

## Quick Start Guide

* **Introduction** doitdoit!! is a to do list application that allows users to store and keep track of tasks they need to do. 

* **Task Definitions** Tasks contains:
  * Title (compulsory) - short summary of task
  * Start time -  used to indicate start of task with a timing
  * Deadline - used to indicate end of task with a timing 
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

Scenario | Input Command  | Excpected Task Panel result | Excpected Feedback panel result
------------ | ------------- | ------------- | -------------
Content from cell 1 | Content from cell 2 | Content in the first column | Content in the second column

