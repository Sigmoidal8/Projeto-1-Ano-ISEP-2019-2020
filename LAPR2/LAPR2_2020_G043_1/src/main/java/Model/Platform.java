/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Adapter.ExternalAlgorithmConvertCurrenciesAdapter1;
import Adapter.ExternalAlgorithmGeneratePwdAdapter1;
import Autorization.AutorizationFacade;
import Controller.NotifyDelaysToFreelancerTask;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Miguel
 */
public class Platform implements Serializable{

    /**
     * The registry of freelancers
     */
    private FreelancerRegister rfree;

    /**
     * The registry of the organizations
     */
    private OrganizationRegister rorg;

    /**
     * The Autorization
     */
    private  AutorizationFacade autorization;
    
    /**
     * The external generator of passwords
     */
    private ExternalAlgorithmGeneratePwd passwordGenerator;
    
    /**
     * The external currencies converter
     */
    private ExternalAlgorithmConvertCurrencies currenciesConverter;
    
    /**
     * The Notify Delays To Freelancer Task
     */
    private NotifyDelaysToFreelancerTask nDtask;
    
    /**
     * The timer
     */
    private Timer timer;
    
    /**
     * The transactions file name
     */
    private static final String FILE_TRANSACTIONS = "transactionsFile.txt";
    
    /**
     * Creates an isntance of Platform
     */
    public Platform() {
        this.autorization = new AutorizationFacade();
        this.rorg=new OrganizationRegister();
        this.rfree=new FreelancerRegister();
        passwordGenerator=new ExternalAlgorithmGeneratePwdAdapter1();
        currenciesConverter = new ExternalAlgorithmConvertCurrenciesAdapter1();
    }
    
    /**
     * Returns the autorizationfacade
     * 
     * @return the autorization
     */
   public AutorizationFacade getAutorizationFacade() {
        return this.autorization;
    }
    
   /**
    * Returns the freelancer registry
    * 
    * @return the freelancer registry
    */
    public FreelancerRegister getFreelancerRegister() {
        return rfree;
    }

    /**
     * Returns the organization registry
     * 
     * @return the organization registry
     */
    public OrganizationRegister getOrganizationRegister() {
        return getRorg();
    }
    
    /**
     * Returns the actual date
     * 
     * @return the date
     */
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    /**
     * Returns the password generator
     * 
     * @return the password generator
     */
    public ExternalAlgorithmGeneratePwd getPwdGenerator(){
        return this.passwordGenerator;
    }
    
    /**
     * Returns the currencies converter
     * 
     * @return the currencies converter
     */
    public ExternalAlgorithmConvertCurrencies getCurrenciesConvert(){
        return currenciesConverter;
    }
    
    /**
     * Returns the organization registry
     * 
     * @return the rorg
     */
    public OrganizationRegister getRorg() {
        return rorg;
    }

    /**
     * Registers a payment in the platform file
     * 
     * @param task
     * @param free
     * @param amount
     * @param amountCnv 
     */
    public void enterNewPayment(Task task, Freelancer free, float amount, float amountCnv) {
        try {
            PrintWriter print = new PrintWriter(new FileWriter(FILE_TRANSACTIONS,true));
            try {
                String date = getDateTime();
                String line = String.format("Date:%s , TaskID:%s , TaskDescription:%s , FreelancerID:%s , FreelancerName:%s , BankAccount:%s , Amount:%.2f € , AmountCountry:%.2f\n",
                        date, task.getId(), task.getBriefDescription(), free.getId(), free.getName(), free.getIban(), amount, amountCnv);
                print.append(line);
            } finally {
                print.close();
            }
        } catch (FileNotFoundException fnf) {
            
        }catch(IOException io){
            
        }
    }
    
    /**
     * Schedules the emails to the freelancers with high delays
     */
    public void scheduleEmails() {
        nDtask = new NotifyDelaysToFreelancerTask();
        timer = new Timer();
        nDtask.setPlatform(this);
        Calendar cal= Calendar.getInstance();
        Calendar cal1=new GregorianCalendar(cal.get(Calendar.YEAR),12,31);
        long delay= (cal1.getTime().getTime())-(cal.getTime().getTime());
        long period=365*24*60*60*1000;
        timer.schedule(nDtask, delay,period);
    }
}
