/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autorization;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Miguel
 */
public class UserRegister implements Serializable {

    /**
     * List of users
     */
    private Set<User> userList = new HashSet<User>();

    /**
     * Returns an instance of user receiving by parameter the name, email, password, role
     * 
     * @param strName
     * @param strEmail
     * @param strPassword
     * @param role
     * @return  the user
     */
    public User newUser(String strName, String strEmail, String strPassword, String role) {
        return new User(strName, strEmail, strPassword, role);
    }

    /**
     * Adds the user to the user registry
     * 
     * @param user
     * @return if the user was added
     */
    public boolean addUser(User user) {
        if (user != null) {
            return this.userList.add(user);
        }
        return false;
    }

    /**
     * Returns if any user contains the email received by parameter 
     * 
     * @param strId
     * @return the user
     */
    public User searchUser(String strId) {
        for (User user : this.userList) {
            if (user.hasId(strId)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Sees if the email received by parameter is in any user registered in the platform
     * 
     * @param strId
     * @return if the email exists in the platform
     */
    public boolean hasUtilizador(String strId) {
        User user = searchUser(strId);
        if (user != null) {
            return this.userList.contains(user);
        }
        return false;
    }
}
