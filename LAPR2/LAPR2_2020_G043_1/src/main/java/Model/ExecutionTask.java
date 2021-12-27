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
public class ExecutionTask implements Serializable {

    /**
     * The end date
     */
    private String endDate;

    /**
     * The delay
     */
    private float delay;

    /**
     * The decription of quality
     */
    private String briefDescriptionQuality;

    /**
     * Creates an instance of ExecutionTask receiving the end date, delay and decription of quality by parameter
     * 
     * @param endDate
     * @param delay
     * @param briefDescriptionQuality
     */
    public ExecutionTask(String endDate, float delay, String briefDescriptionQuality) {
        this.endDate = endDate;
        this.delay = delay;
        this.briefDescriptionQuality = briefDescriptionQuality;

    }

    /**
     * Returns the end date of the task
     * 
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Returns the delay of the task
     * 
     * @return the delay
     */
    public float getDelay() {
        return delay;
    }

    /**
     * Returns the description of quality of the task
     * 
     * @return the briefDescriptionQuality
     */
    public String getBriefDescriptionQuality() {
        return briefDescriptionQuality;
    }
    
    /**
     * Compares if the execution task passed by parameter is the same as the current
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
        ExecutionTask outroMana = (ExecutionTask) outroObjeto;

        return endDate.equalsIgnoreCase(outroMana.endDate) && delay == outroMana.delay && 
                briefDescriptionQuality.equalsIgnoreCase(outroMana.briefDescriptionQuality);
    }
}

