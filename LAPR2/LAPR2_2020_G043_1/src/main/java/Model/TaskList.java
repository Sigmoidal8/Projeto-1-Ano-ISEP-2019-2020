package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {

    /**
     * The list of tasks
     */
    private List<Task> taskList;
    /**
     * A task instance
     */
    private Task task;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates a new Task receiving the id, briefDescription, timeDuraction,
     * costPerHour, taskCategory
     *
     * @param id
     * @param briefDescription
     * @param timeDuraction
     * @param costPerHour
     * @param taskCategory
     * @return
     */
    public Task newTask(String id, String briefDescription, Integer timeDuraction, float costPerHour, String taskCategory) {
        Task task = new Task(id, briefDescription, timeDuraction, costPerHour, taskCategory);
        return task;
    }

    /**
     * Validates by id if the task is already in the list
     *
     * @param id
     * @return if the task is already in the list
     */
    public boolean validateTaskId(String id) {
        for (Task task : taskList) {
            if (task.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a task that isnt already int the task list
     *
     * @param task
     * @return if the task was added
     */
    public boolean taskRegister(Task task) {
        if (!validateTask(task)) {
            return addTask(task);
        }
        return false;
    }

    /**
     * Adds a task to the task list
     *
     * @param task
     * @return
     */
    public boolean addTask(Task task) {
        return taskList.add(task);
    }

    /**
     * Validates if the task is already in the list
     *
     * @param task
     * @return
     */
    public boolean validateTask(Task task) {
        return taskList.contains(task);
    }

    /**
     * Receives a taskId and returns the task with the same id
     *
     * @param taskId
     * @return the task with the same if received by parameter
     */
    public Task getTaskById(String taskId) {
        for (Task obj : getTaskList()) {
            if (obj.getId().equalsIgnoreCase(taskId)) {
                return obj;
            }
        }
        return null;
    }

    /**
     * Creates a string list of tasks
     *
     * @return the list of tasks
     */
    @Override
    public String toString() {
        List<Task> copy = new ArrayList<>(getTaskList());

        StringBuilder t = new StringBuilder();
        for (Task task : copy) {
            t.append(task);
            t.append("\n");
        }
        return t.toString().trim();
    }

    /**
     *
     * @return the task list
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * @return the task
     */
    public Task getTask() {
        return task;
    }

}
