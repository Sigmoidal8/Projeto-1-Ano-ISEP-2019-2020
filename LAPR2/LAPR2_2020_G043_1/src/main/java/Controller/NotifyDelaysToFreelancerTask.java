/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Freelancer;
import Model.FreelancerRegister;
import Model.Platform;
import Model.Task;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Miguel
 */
public class NotifyDelaysToFreelancerTask extends TimerTask implements Serializable {

    /**
     * The Platform
     */
    private Platform plat;

    /**
     * The registry of the freelancers
     */
    private FreelancerRegister regFre;

    /**
     * The list of tasks of the freelancer
     */
    private List<Task> tList;

    /**
     * The initial number of tasks
     */
    private int numberTasks = 0;

    /**
     * The initial number of delays
     */
    private int numberDelays = 0;

    /**
     * Creates an instance of NotifyDelaysToFreelancerTask
     */
    public NotifyDelaysToFreelancerTask() {
        this.plat = AplicationPOT.getInstance().getPlatform();
    }

    /**
     * Sets the platform to the one received by parameter
     *
     * @param plat the plat to set
     */
    public void setPlatform(Platform plat) {
        this.plat = plat;
    }

    /**
     * Sees which freelancers have high delays and sends them an email
     *
     * @throws IOException
     */
    private void notifyFreelancers() throws IOException {
        regFre = plat.getFreelancerRegister();

        List<Freelancer> freeList = regFre.getFreelancersList();

        for (Freelancer free : freeList) {
            tList = free.getTaskList();
            for (Task tsk : tList) {

                String[] arr = new String[3];
                arr = tsk.getPaymentTransaction().getExecutionTask().getEndDate().split("-");
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                if (Integer.parseInt(arr[2]) == ano) {
                    numberTasks++;
                    float dl = tsk.getPaymentTransaction().getExecutionTask().getDelay();
                    if (dl > 0) {
                        numberDelays++;
                    }
                }
            }
        }
        float percentGeneral = calculatePercentGeneral(numberTasks, numberDelays);

        for (Freelancer free : freeList) {
            tList = free.getTaskList();
            int nTsk = 0;
            float sumDl = 0;
            int nDl = 0;
            for (Task tsk : tList) {
                String[] arr = new String[3];
                arr = tsk.getPaymentTransaction().getExecutionTask().getEndDate().split("-");
                Calendar cal = Calendar.getInstance();
                int ano = cal.get(Calendar.YEAR);
                if (Integer.parseInt(arr[2]) == ano) {
                    float dl = tsk.getPaymentTransaction().getExecutionTask().getDelay();
                    nTsk++;
                    sumDl += dl;
                    if (dl > 0) {
                        nDl++;
                    }
                }
            }
            float meanDelay = calculateMeanDelay(nTsk, sumDl);
            float percent = calculatePercent(nTsk, nDl);
            if (percent > percentGeneral && meanDelay > 3) {
                sendEmail(nDl, percent);
            }
        }

    }

    /**
     * Calculates the percentage of general delays
     *
     * @param numberTasks
     * @param numberDelays
     * @return the percentage
     */
    private float calculatePercentGeneral(int numberTasks, int numberDelays) {
        return (((float) numberDelays) / ((float) numberTasks) * 100);
    }

    /**
     * Calculates the percentage of delay
     *
     * @param nTsk
     * @param nDl
     * @return the percentage
     */
    private float calculatePercent(int nTsk, int nDl) {
        return (((float) nDl) / ((float) nTsk) * 100);
    }

    /**
     * Calculates the mean of the delays
     *
     * @param nTsk
     * @param sumDl
     * @return the mean of delays
     */
    private float calculateMeanDelay(int nTsk, float sumDl) {
        return (sumDl / (float) nTsk);
    }

    /**
     * Sends the email to the freelancer
     *
     * @param nDl
     * @param percent
     * @throws IOException
     */
    private void sendEmail(int nDl, float percent) throws IOException {
        try (PrintWriter pr = new PrintWriter(new FileWriter("e-mail.txt", true))) {
            pr.println();
            pr.println("\n\n###Email###");

            pr.println("\n    ##Delays##\n");
            String ptrn = "dd-MM-yyy";
            SimpleDateFormat sDT = new SimpleDateFormat(ptrn);
            String date = sDT.format(new Date());

            pr.println("Date: " + date + "\n");

            pr.println("Hello dear user. During the current year you had a delay in " + nDl + " tasks. Also your percentage of delays is " + percent + "% . You should try to improve that!\n   Best Regards, T4J");

            try {
            } finally {
                pr.close();
            }
        }
    }

    /**
     * Notifies the freelancers
     */
    @Override
    public void run() {
        try {
            notifyFreelancers();
        } catch (IOException ex) {
            Logger.getLogger(NotifyDelaysToFreelancerTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
