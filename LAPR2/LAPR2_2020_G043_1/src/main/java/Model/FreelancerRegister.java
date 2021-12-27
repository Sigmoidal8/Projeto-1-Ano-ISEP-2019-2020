package Model;

import Controller.AplicationPOT;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FreelancerRegister implements Serializable {

    /**
     * A list of freelancers
     */
    private List<Freelancer> freelancerList;
    
    /**
     * The initial number of tasks
     */
    private int numberTasks = 0;
    
    /**
     * The initial number of delays
     */
    private int numberDelays = 0;

    /**
     * Creates an instance of FreelancerRegister
     */
    public FreelancerRegister() {
        freelancerList = new ArrayList<>();
    }
    
    /**
     * Returns the list of freelancers
     * 
     * @return the list
     */
    public List<Freelancer> getFreelancersList() {
        return freelancerList;
    }
    
    /**
     * Returns a freelancer with the id passed by parameter
     * 
     * @param freelancerId
     * @return the freelancer
     */
    public Freelancer getFreelancerById(String freelancerId) {
        for (Freelancer obj : getFreelancersList()) {
            if (obj.getId().equalsIgnoreCase(freelancerId)) {
                return obj;
            }
        }
        return null;
    }

    /**
     * Creates a freelancer with the name, level of expertise, email, NIF, IBAN, adress, country by parameter
     * 
     * @param name
     * @param levelOfExpertise
     * @param email
     * @param NIF
     * @param IBAN
     * @param address
     * @param country
     * @return the freelancer
     */
    public Freelancer newFreelancer(String name, String levelOfExpertise, String email, String NIF, String IBAN, String address, String country) {
        Freelancer freelancer = new Freelancer(generateId(name), name, levelOfExpertise, email, NIF, IBAN, address, country);
        return freelancer;
    }

    /**
     * Creates a freelancer with the id, name, level of expertise, email, NIF, IBAN, adress, country by parameter
     * 
     * @param id
     * @param name
     * @param levelOfExpertise
     * @param email
     * @param NIF
     * @param IBAN
     * @param address
     * @param country
     * @return the freelancer
     */
    public Freelancer newFreelancer(String id, String name, String levelOfExpertise, String email, String NIF, String IBAN, String address, String country) {
                Freelancer freelancer = new Freelancer(id, name, levelOfExpertise, email, NIF, IBAN, address, country);
        return freelancer;
    }

    /**
     * Validates the freelancer using the email
     * 
     * @param email
     * @return if the freelancers exists
     */
    public boolean validaFreelancerByEmail(String email) {
        for (Freelancer free : freelancerList) {
            if (free.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates if the freelancer is in the list
     * 
     * @param freelancer
     * @return if the freelancer is in the list
     */
    public boolean validateFreelancer(Freelancer freelancer) {
        return getFreelancersList().contains(freelancer);
    }

    /**
     * Adds a freelancer to the freelancers list
     * 
     * @param freelancer
     * @return if it was added
     */
    public boolean addFreelancer(Freelancer freelancer) {
        return freelancerList.add(freelancer);
    }

    /**
     * Registers a freelancer and adds it to the list
     * 
     * @param freelancer
     * @return if it was added
     */
    public boolean registerFreelancer(Freelancer freelancer) {
        if (!validateFreelancer(freelancer)) {
            return addFreelancer(freelancer);
        }
        return false;
    }

    /**
     * Generates a id to the freelancer
     * 
     * @param name
     * @return the id
     */
    public String generateId(String name) {
        String[] names = name.split(" ");
        String space = "";
        space += names[0].charAt(0);
        if (names.length != 1) {
            space += names[names.length - 1].charAt(0);
        }
        int i = 1;
        Platform plat = AplicationPOT.getInstance().getPlatform();
        for (Freelancer fr : plat.getFreelancerRegister().getFreelancersList()) {
            String c1 = "";
            String id = fr.getId();
            c1 += id.charAt(0);
            if(Character.isLetter(id.charAt(1))){
                c1 += id.charAt(1);
            }
            if(c1.equalsIgnoreCase(space)){
                i++;
            }
        } 
        return space.toUpperCase()+i;
    }
}
