/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel
 */
public class Freelancer implements Serializable {

    /**
     * The id of the freelancer
     */
    private String id;

    /**
     * The name of the freelancer
     */
    private String name;

    /**
     * The level of expertise of the freelancer
     */
    private String levelOfExpertise;

    /**
     * The email of the freelancer
     */
    private String email;

    /**
     * The NIF of the freelancer
     */
    private String NIF;

    /**
     * The IBAN of the freelancer
     */
    private String IBAN;

    /**
     * The address of the Freelancer
     */
    private String address;

    /**
     * The country of the freelancer
     */
    private String country;

    /**
     * The list of tasks of the freelancer
     */
    private List<Task> taskList;

    /**
     * Creates an instance of Freelancer receiving the id, name, level of
     * expertise, email, NIF, IBAN, adress and country by parameter
     *
     * @param id
     * @param name
     * @param levelOfExpertise
     * @param email
     * @param NIF
     * @param IBAN
     * @param address
     * @param country
     */
    public Freelancer(String id, String name, String levelOfExpertise, String email, String NIF, String IBAN, String address, String country) {
        this.id = id;
        this.name = name;
        this.levelOfExpertise = levelOfExpertise;
        this.email = email;
        this.NIF = NIF;
        this.IBAN = IBAN;
        this.address = address;
        this.country = country;
        this.id = id;
        taskList = new ArrayList<>();
    }

    /**
     * Returns the list of task of the freelancer
     *
     * @return the list
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the email of the freelancer
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the country of the freelancer
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the id of the freelancer
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the IBAN of the freelancer
     *
     * @return the IBAN
     */
    public String getIban() {
        return IBAN;
    }

    /**
     * Returns the level of expertise of the freelancer
     *
     * @return the levelOfExpertise
     */
    public String getLevelOfExpertise() {
        return levelOfExpertise;
    }

    /**
     * Returns the name of the freelancer
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the id of the freelancer to the id passe by parameter
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Adds a task to the task list
     *
     * @param task
     * @return if the task was added
     */
    public boolean addTask(Task task) {
        return taskList.add(task);
    }

    /**
     * Returns a textual description of the freelancer data with th format:
     * name, level of expertise, email, NIF, IBAN, adress, country
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("ID:%s, Name:%s, LevelOfExpertise:%s, Email:%s, NIF:%s, IBAN:%s, Adress:%s, Country:%s",
                id, getName(), getLevelOfExpertise(), email, NIF, IBAN, address, country);
    }

    /**
     * Compares if the freelancer passed by parameter is the same as the current
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
        Freelancer outroFree = (Freelancer) outroObjeto;

        return id.equalsIgnoreCase(outroFree.id) && getName().equalsIgnoreCase(outroFree.getName()) && getLevelOfExpertise().equalsIgnoreCase(outroFree.getLevelOfExpertise()) && email.equalsIgnoreCase(outroFree.email)
                && NIF.equals(outroFree.NIF) && IBAN.equals(outroFree.IBAN) && address.equals(outroFree.address) && country.equals(outroFree.country);
    }
}

