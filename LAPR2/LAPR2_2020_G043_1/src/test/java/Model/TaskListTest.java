/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raulcoelho
 */
public class TaskListTest {
    
    public TaskListTest() {
    }
    
    //ERROOOOOO
    /**
     * Test of newTask method, of class TaskList.
     */
    @Test
    public void testNewTask() {
        System.out.println("newTask");
        String id = "12";
        String briefDescription = "Design";
        Integer timeDuraction = 12;
        float costPerHour = 2.3F;
        String taskCategory = "Designer";
        TaskList instance = new TaskList();
        Task expResult = new Task("12", "Design", 12, 2.3F, "Designer");
        Task result = instance.newTask(id, briefDescription, timeDuraction, costPerHour, taskCategory);
        result.equals(expResult);
    }

    /**
     * Test of validateTaskId method, of class TaskList.
     */
    @Test
    public void testValidateTaskId() {
        System.out.println("validateTaskId");
        String id = "12";
        TaskList instance = new TaskList();
        boolean expResult = false;
        boolean result = instance.validateTaskId(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of taskRegister method, of class TaskList.
     */
    @Test
    public void testTaskRegister() {
        System.out.println("taskRegister");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        TaskList instance = new TaskList();
        boolean expResult = true;
        boolean result = instance.taskRegister(task);
        assertEquals(expResult, result);
    }

    /**
     * Test of addTask method, of class TaskList.
     */
    @Test
    public void testAddTask() {
        System.out.println("addTask");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        TaskList instance = new TaskList();
        boolean expResult = true;
        boolean result = instance.addTask(task);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateTask method, of class TaskList.
     */
    @Test
    public void testValidateTask() {
        System.out.println("validateTask");
        Task task = new Task("12", "Design", 12, 2.3F, "Designer");
        TaskList instance = new TaskList();
        boolean expResult = false;
        boolean result = instance.validateTask(task);
        assertEquals(expResult, result);
    }
}
