/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Autorization.UserSession;
import Model.Freelancer;
import Model.FreelancerRegister;
import Model.Organization;
import Model.OrganizationRegister;
import Model.PaymentTransaction;
import Model.PaymentTransactionList;
import Model.Platform;
import Model.Task;
import Model.TaskList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel
 */
public class CreatePaymentTransactionController {

    /**
     * The platform
     */
    private Platform plat;
    
    /**
     * The aplicationPot
     */
    private AplicationPOT aplicationPOT;
    
    /**
     * The registry of the organizations
     */
    private OrganizationRegister rorg;
    
    /**
     * The organization
     */
    private Organization org;
    
    /**
     * The list of the tasks of the organization
     */
    private TaskList tasklist;
    
    /**
     * The registry of the freelancers
     */
    private FreelancerRegister rfree;
    
    /**
     * The list of tasks contained in the tasklist
     */
    private List<Task> tl;
    
    /**
     * The List of PaymentTransaction of the organization
     */
    private PaymentTransactionList ptl;
    
    /**
     * The list of payment transactions contained in the ptl
     */
    private PaymentTransaction pt;
    
    /**
     * Creates an instance of the CreatePaymentTransactionController initializing the platform
     */
    public CreatePaymentTransactionController() {
        this.plat = aplicationPOT.getInstance().getPlatform();
    }

    /**
     * Returns the list of tasks of the organization
     * 
     * @return the task list
     */
    public List<Task> getTaskList() {
        List<Task> copy = new ArrayList<>();
        AplicationPOT app = aplicationPOT.getInstance();
        UserSession session = app.getAtualSession();
        String email = session.getUserEmail();
        rorg = plat.getOrganizationRegister();
        org = rorg.getOrganizationByEmail(email);
        tasklist = org.getTaskList();
        tl = tasklist.getTaskList();
        for(Task task : tl){
            if(task.getPaymentTransaction()==null){
                copy.add(task);
            }
        }
        return copy;
    }
    
    /**
     * Returns the list of freelancer in the platform
     * 
     * @return the list of freelancers
     */
    public List<Freelancer> getFreelancerList() {
        rfree = plat.getFreelancerRegister();
        return rfree.getFreelancersList();
    }
    
    /**
     * Creates a new payment transaction  in the payment transaction list receiving the task, freelancer, transaction id, end date, delay and
     * a brief description of quality by parameter
     * 
     * @param task
     * @param freelancer
     * @param transId
     * @param endDate
     * @param delay
     * @param briefDescriptionQaulity 
     */
      public void newPaymentTransaction(Task task, Freelancer freelancer, String transId, String endDate, float delay, String briefDescriptionQaulity) {
        getTaskList();
        ptl = org.getPaymentTransactionList();
        pt = ptl.newPaymentTransaction(task, freelancer, transId, endDate, delay, briefDescriptionQaulity);
    }
      
      /**
     * Registers the payment transaction in the list of PaymentTransactions
     */
    public void registerPaymentTransaction(){
        ptl.registerPaymentTransaction(pt);
    }
    
    /**
     * Adds a task to the freelancer task list
     * 
     * @param free
     * @param task
     * @return if the task was added
     */
    public boolean addTaskToFreelancer(Freelancer free, Task task){
        return free.addTask(task);
    }

    /**
     * Calculate the amount that will be paid to the freelancer
     * 
     * @param free
     * @param task
     * @return the amount
     */
    public float calculatePayment(Freelancer free, Task task) {
        float amount = pt.calculatePayment(free, task);
        return amount;
    }
}
