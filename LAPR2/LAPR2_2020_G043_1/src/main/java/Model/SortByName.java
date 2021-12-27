/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Comparator;

/**
 *
 * @author sandr
 */
public class SortByName implements Comparator<Freelancer> {
    
    /**
     * Sorts two freelancers by alphabetical order
     * 
     * @param name
     * @param anotherName
     * @return the diference between the names
     */
    public int compare(Freelancer name, Freelancer anotherName) {
        return name.getName().compareTo(anotherName.getName());
    }
};
