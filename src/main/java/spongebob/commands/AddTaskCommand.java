package spongebob.commands;

import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.tasktype.KrustyKrabTask;
import spongebob.ui.components.MainWindow;

/**
 * Represents the command to add a task to the task list. This command is used
 * to add a new task to the Krusty Krab task list based on the input details
 * provided by the user.
 * This is reserved for undoing the delete task command.
 */
public class AddTaskCommand extends KrustyKrabTaskCommand {
    private int taskIndex;
    private KrustyKrabTask taskToAdd;

    /**
     * Custom constructor for AddTaskCommand that takes in the task index and the
     * task to add. This constructor is used to create an AddTaskCommand with
     * specific details about the task to be added back to the task list, which is
     * typically used for undoing a delete command. Since this command is reserved
     * for undoing the delete command, it does not require any input details from
     * the user, and the task index and task to add are provided directly when the
     * command is created for the undo operation.
     *
     * @param taskIndex The index at which the task should be added back to the task
     *                  list.
     * @param taskToAdd The task that should be added back to the task list.
     */
    public AddTaskCommand(int taskIndex, KrustyKrabTask taskToAdd) {
        super("");
        this.taskIndex = taskIndex;
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the command to add a task to the task list. This method adds the
     * specified task back to the task list at the given index. This command is
     * typically used as part of the undo functionality to restore a task that was
     * previously deleted from the task list. Since this command is reserved for
     * undoing the delete command, it does not perform any validation on the input
     * details, as the task index and task to add are provided directly when the
     * command is created for the undo operation.
     *
     * @param guiWindow The main GUI window to display the results in. Irrelevant
     *                  for this command since it is reserved for undoing the delete
     *                  command, but it is included as a parameter to maintain
     *                  consistency with the execute method signature in the
     *                  KrustyKrabTaskCommand class.
     * @param taskList  The Krusty Krab task list to operate on, which is the task
     *                  list that the task will be added back to when this command
     *                  is executed as part of an undo operation.
     * @param isUndo    Whether the command is being executed as part of an undo
     *                  operation. Irrelevant for this command since it is reserved
     *                  for undoing the delete command, but it is included as a
     *                  parameter to maintain consistency with the execute method
     *                  signature in the KrustyKrabTaskCommand class.
     */
    @Override
    public void execute(MainWindow guiWindow, KrustyKrabTaskList taskList, boolean isUndo) {
        taskList.addTask(this.taskIndex, this.taskToAdd);
    }

    @Override
    protected void assertValidInput() {
        // No validation needed for AddTaskCommand since it is reserved for undoing the
        // delete command and there is no input to check
    }
}
