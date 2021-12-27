/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.MakePaymentTask;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Miguel
 */
public class Organization implements Serializable {

    /**
     * The Collaborator of the organization
     */
    private Collaborator colab;

    /**
     * The Manager of the organization
     */
    private Manager mana;

    /**
     * The MakePaymentTask
     */
    private MakePaymentTask task;

    /**
     * The Timer
     */
    private Timer timer;

    /**
     * The name of the Organization
     */
    private String name;

    /**
     * The NIF of the Organization
     */
    private String NIF;

    /**
     * The TaskList
     */
    private TaskList taskList;

    /**
     * The payment transaction list
     */
    private PaymentTransactionList paymentTransactionList;

    /**
     * Creates an instance of Organization receiving the nam and NIF by
     * parameter
     *
     * @param name
     * @param NIF
     */
    public Organization(String name, String NIF) {
        this.name = name;
        this.NIF = NIF;
        taskList = new TaskList();
        paymentTransactionList = new PaymentTransactionList();
    }

    /**
     * Returns the Collbaborator
     *
     * @return the colab
     */
    public Collaborator getColab() {
        return colab;
    }

    /**
     * Returns the Manager
     *
     * @return the mana
     */
    public Manager getMana() {
        return mana;
    }

    /**
     * Returns the name of the organization
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the NIF of the organization
     *
     * @return the NIF
     */
    public String getNIF() {
        return NIF;
    }

    /**
     * Returns the task list of the organization
     *
     * @return the task list
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Returns the payment transaction list of the organization
     *
     * @return the task list
     */
    public PaymentTransactionList getPaymentTransactionList() {
        return paymentTransactionList;
    }

    /**
     * Creates a new Collaborator to the organization
     *
     * @param nameC
     * @param emailC
     * @return the Collaborator
     */
    public Collaborator newCollaborator(String nameC, String emailC) {
        this.colab = new Collaborator(nameC, emailC);
        return getColab();
    }

    /**
     * Crates a new Manager to the organization
     *
     * @param nameM
     * @param emailM
     * @return the Manager
     */
    public Manager newManager(String nameM, String emailM) {
        this.mana = new Manager(nameM, emailM);
        return getMana();
    }

    /**
     * Validates and registers the date of the payment
     *
     * @param day
     * @return the date
     */
    public Calendar validateDate(String day) {
        String[] line = day.split(" ");
        String[] date = line[0].split("-");
        String[] hours = line[1].trim().split(":");
        Calendar cal = new GregorianCalendar();
        cal.set(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]), Integer.parseInt(hours[0]), Integer.parseInt(hours[1]), Integer.parseInt(hours[2]));
        return cal;
    }

    /**
     * Schedules the payments that have not been made
     *
     * @param day
     */
    public void schedulePayment(Date day) {
        task = new MakePaymentTask();
        timer = new Timer();
        task.setOrganization(this);
        timer.schedule(task, day);
    }

    /**
     * Returns the textual description of the organization with the format:
     * name, nif, manager, collaborator
     *
     * @return the textual description
     */
    @Override
    public String toString() {
        return String.format("Name:%s \nNIF: %s \n%s \n%s", getName(), getNIF(), getMana(), getColab());
    }

    /**
     * Verifies if the organization passed by parameter is the same as the
     * current one
     *
     * @param otherObject
     * @return if is the same
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Organization otherOrganization = (Organization) otherObject;
        return getName().equalsIgnoreCase(otherOrganization.getName())
                && getNIF().equalsIgnoreCase(otherOrganization.getNIF())
                && getColab().equals(otherOrganization.getColab()) && getMana().equals(otherOrganization.getMana());
    }
}
