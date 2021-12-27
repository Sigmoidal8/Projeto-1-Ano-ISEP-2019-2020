package Controller;

import Autorization.UserSession;
import Model.Organization;
import Model.OrganizationRegister;
import Model.Platform;
import Model.Task;
import Model.TaskList;

public class CreateTaskController {

    /**
     * The AplicationPOT
     */
    private AplicationPOT aplicationPOT;

    /**
     * A Task
     */
    private Task task;

    /**
     * The TaskList of an organization
     */
    private TaskList taskList;

    /**
     * An Organization
     */
    private Organization org;

    /**
     * The registry of organizations of the platform
     */
    private OrganizationRegister orgReg;

    /**
     * The Platform
     */
    private Platform plat;

    /**
     * Creates an instance of CreateTaskController initializing the platfom
     */
    public CreateTaskController() {
        this.plat = aplicationPOT.getInstance().getPlatform();
    }

    /**
     * Creates a new task and adds it to the task list of the organization receiving the id, a brief description, the time duration, the cost per hour,
     * and the task category by parameter
     * 
     * @param id
     * @param briefDescription
     * @param TimeDuration
     * @param CostPerHour
     * @param taskCategory
     * @return if the task was added
     */
    public boolean newTask(String id, String briefDescription, Integer TimeDuration, float CostPerHour, String taskCategory) {
        AplicationPOT app = AplicationPOT.getInstance();
        UserSession session = app.getAtualSession();
        String email = session.getUserEmail();

        orgReg = plat.getOrganizationRegister();
        org = orgReg.getOrganizationByEmail(email);
        taskList = org.getTaskList();
        if (taskList.validateTaskId(id) == false) {
            task = taskList.newTask(id, briefDescription, TimeDuration, CostPerHour, taskCategory);
            return true;
        }
        return false;
    }

    /**
     * Registers the task and adds it to the task list
     * 
     * @return if the task was added
     */
    public boolean taskRegister() {
        return taskList.taskRegister(task);
    }
}

