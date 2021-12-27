/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Miguel
 */
public class PaymentTransaction implements Serializable {

    /**
     * The id of the payment transaction
     */
    private String id;

    /**
     * Verification if the payment transaction was payed
     */
    private boolean payed;

    /**
     * The amount of the payment transaction
     */
    private float amount;

    /**
     * The task related to the payment transaction
     */
    private Task task;

    /**
     * The freelancer related to the payment transaction
     */
    private Freelancer freelancer;

    /**
     * The execution task associated with the payment transaction
     */
    private ExecutionTask execTask;

    /**
     * Creates an instance of PaymentTransaction with the task, freelancer,
     * transaction id, end date, delay and a description of quality passed by
     * parameter
     *
     * @param task
     * @param freelancer
     * @param transId
     * @param endDate
     * @param delay
     * @param briefDescriptionQuality
     */
    public PaymentTransaction(Task task, Freelancer freelancer, String transId, String endDate, float delay, String briefDescriptionQuality) {
        this.task = task;
        this.freelancer = freelancer;
        this.id = transId;
        this.payed = false;
        this.execTask = new ExecutionTask(endDate, delay, briefDescriptionQuality);
        this.task.setPaymentTransaction(this);
        this.freelancer.addTask(this.task);
        this.amount = calculatePayment(freelancer, task);
    }

    /**
     * Returns if the payment has been made
     *
     * @return if the payment has been made
     */
    public boolean getPaymentMade() {
        return payed;
    }

    /**
     * Returns the amount of the payment
     *
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Returns the freelancer associated with the payment
     *
     * @return the freelancer
     */
    public Freelancer getFreelancer() {
        return freelancer;
    }

    /**
     * Returns the id of the payment
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the execution task of the payment
     *
     * @return the execTask
     */
    public ExecutionTask getExecutionTask() {
        return execTask;
    }

    /**
     * Changes the payed status to the one passed by parameter
     *
     * @param payed
     */
    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    /**
     * Calculates the amount of the payment receiving the freelancer and the
     * task by parameter
     *
     * @param free
     * @param task
     * @return the amount
     */
    public float calculatePayment(Freelancer free, Task task) {
        float cost = task.getCostPerHour();
        float timeDuration = task.getTimeDuration();
        if (free.getLevelOfExpertise().equalsIgnoreCase("Senior")) {
            amount = cost * 2 * timeDuration;
        } else {
            amount = cost * timeDuration;
        }
        return amount;
    }

    /**
     * Returns a textual description of the payment transaction with the format
     * : freelancer name, id
     *
     * @return the textual description
     */
    public String StringFormat() {
        return String.format("Freelancer Name: %s; TransID: %s", getFreelancer().getName(), getId());
    }

    /**
     * Returns a textual description of the payment transaction with the format:
     * id, status, amount, organization, task, freelancer, executio task
     *
     * @return the textual description
     */
    @Override
    public String toString() {
        return String.format("ID: %s, Status: %b, Amount: %d, Task: %s, Freelancer: %s, Execution Task: %s",
                id, payed, amount, task.toString(), freelancer.toString(), execTask.toString());
    }

    /**
     * Compares if the payment transaction passed by parameter is the same as
     * the current
     *
     * @param outroObjeto
     * @return if it is the same
     */
    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) {
            return true;
        }
        if (outroObjeto == null || this.getClass() != outroObjeto.getClass()) {
            return false;
        }
        PaymentTransaction outroPay = (PaymentTransaction) outroObjeto;

        return id.equalsIgnoreCase(outroPay.id) && payed == outroPay.payed && amount == outroPay.amount
                && task.equals(outroPay.task) && freelancer.equals(outroPay.freelancer) && execTask.equals(outroPay.execTask);
    }
}
