/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.AplicationPOT;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.NormalDistributionImpl;

/**
 *
 * @author Miguel
 */
public class CalculateStatistics implements Serializable {

    /**
     * Calculates the mean delay of a freelancer
     * 
     * @param freelancer
     * @return the mean
     */
    public float calculateMeanDelayFreelancer(Freelancer freelancer) {
        List<Task> tasks = freelancer.getTaskList();
        float nTasks = 0;
        float sumDelay = 0;
        for (Task tsk : tasks) {
            float dl = tsk.getPaymentTransaction().getExecutionTask().getDelay();
            nTasks++;
            sumDelay += dl;
        }
        float finalDelay = sumDelay / nTasks;
        return finalDelay;
    }

    /**
     * Calculates the standart deviation delay of a freelancer
     * 
     * @param freelancer
     * @param freelancerMeanDelay
     * @return the standart deviation
     */
    public float calculateStandartDeviationDelayFreelancer(Freelancer freelancer, float freelancerMeanDelay) {
        List<Task> tasks = freelancer.getTaskList();
        float i = 0;
        float standartDeviationDelay = 0;

        for (Task tsk : tasks) {
            float delay = tsk.getPaymentTransaction().getExecutionTask().getDelay();

            float aux = delay - freelancerMeanDelay;
            standartDeviationDelay += aux * aux;
            i++;
        }
        return (float) Math.sqrt(standartDeviationDelay / i);
    }

    /**
     * Calculates the mean payment of a freelancer
     * 
     * @param freelancer
     * @return the mean
     */
    public float calculateMeanPaymentFreelancer(Freelancer freelancer) {
        List<Task> tasks = freelancer.getTaskList();
        float nPay = 0;
        float sumPay = 0;
        for (Task tsk : tasks) {
            nPay += 1;
            float payment = tsk.getPaymentTransaction().getAmount();
            sumPay += payment;
        }
        float meanPayment = sumPay / nPay;
        return meanPayment;
    }

    /**
     * Calculates the standart deviation payment of a freelancer
     * 
     * @param freelancer
     * @param meanPay
     * @return the standart deviation
     */
    public float calculateStandartDeviationPaymentFreelancer(Freelancer freelancer, float meanPay) {
        List<Task> tasks = freelancer.getTaskList();
        float i = 0;
        float standartDeviationPay = 0;
        for (Task tsk : tasks) {
            float payment = tsk.getPaymentTransaction().getAmount();
            float aux = payment - meanPay;
            standartDeviationPay += aux * aux;
            i++;
        }
        return (float) Math.sqrt(standartDeviationPay / i);
    }

    /**
     * Calculates the mean delay of all freelancers
     * 
     * @param listFree
     * @return the mean
     */
    public float calculateMeanDelayAll(List<Freelancer> listFree) {
        float totalMeanDelay = 0;
        float nFree = 0;
        for (Freelancer freelancer : listFree) {
            nFree += 1;
            totalMeanDelay += calculateMeanDelayFreelancer(freelancer);
        }
        float meanDelayAll = totalMeanDelay / nFree;
        return meanDelayAll;
    }

    /**
     * Calculates the standart deviation delay of all freelancers
     * 
     * @param listFre
     * @param meanDelayAll
     * @return the standart deviation
     */
    public float calculateStandartDeviationDelayAll(List<Freelancer> listFre, float meanDelayAll) {
        float i = 0;
        float standartDeviationAll = 0;
        for (Freelancer freelancer : listFre) {
            float delay = calculateMeanDelayFreelancer(freelancer);
            float aux = delay - meanDelayAll;
            standartDeviationAll += aux * aux;
            i++;
        }
        return (float) Math.sqrt(standartDeviationAll / i);
    }

    /**
     * Calculates the mean payment of all freelancers
     * 
     * @param listFre
     * @return 
     */
    public float calculateMeanPaymentAll(List<Freelancer> listFre) {
        float totalMeanPay = 0;
        float nFree = 0;
        for (Freelancer freelancer : listFre) {
            nFree += 1;
            totalMeanPay += calculateMeanPaymentFreelancer(freelancer);
        }

        float meanPayAll = totalMeanPay / nFree;
        return meanPayAll;

    }

    /**
     * Calculates the standart deviation payment of all freelancers
     * 
     * @param listFre
     * @param meanPaymentAll
     * @return 
     */
    public float calculateStandartDeviationPaymentAll(List<Freelancer> listFre, float meanPaymentAll) {
        float i = 0;
        float standartDeviationPaymentAll = 0;
        for (Freelancer freelancer : listFre) {
            float delay = calculateMeanPaymentFreelancer(freelancer);
            float aux = delay - meanPaymentAll;
            standartDeviationPaymentAll += aux * aux;
            i++;
        }
        return (float) Math.sqrt(standartDeviationPaymentAll / i);
    }

    /**
     * Calculates a the probability that the sample mean is higher than 3 hours
     * 
     * @return the probability
     * @throws MathException 
     */
    public float calculatePrevision() throws MathException {
        Platform plat;
        double standardDeviation = 1;
        plat = AplicationPOT.getInstance().getPlatform();
        int size = plat.getFreelancerRegister().getFreelancersList().size();
        if (size > 0) {
            standardDeviation = Math.pow(1.5, 2) / size;
        }
        NormalDistributionImpl distribution = new NormalDistributionImpl(2, standardDeviation);
        return (float) ((1 - distribution.cumulativeProbability(3)) * 100);

    }
}
