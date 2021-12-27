/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autorization;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Miguel
 */
public class User implements Serializable {

    /**
     * Name of the user
     */
    private String name;
    
    /**
     * Email of the user
     */
    private String email;
    
    /**
     * Password of the user
     */
    private String password; 
    
    /**
     * Role of the user
     */
    private String role;

    /**
     * Creates an instance of User receiving the name, email, password and role by parameter
     * 
     * @param name
     * @param email
     * @param password
     * @param role 
     */
    public User(String name, String email, String password, String role) {
        if ((name == null) || (email == null) || (password == null) || (role == null) || (name.isEmpty()) || (email.isEmpty()) || (password.isEmpty()) || (role.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos não pode ser nulo ou vazio.");
        }

        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;

    }

    /**
     * Returns the user name
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the user email
     * 
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the user role
     * 
     * @return the role
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Verifies if the email received by parameter is equal to the user email
     * 
     * @param strId
     * @return if the emails are the same
     */
    public boolean hasId(String strId) {
        return this.email.equals(strId);
    }

    /**
     * Verifies if the password received by parameter is equal to the user password
     * 
     * @param strPwd
     * @return if the passwords are the same
     */
    public boolean hasPassword(String strPwd) {
        return this.password.equals(strPwd);
    }

    /**
     * Verifies if the user received by parameter is the same as the user
     * 
     * @param o
     * @return if the users are the same
     */
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        User obj = (User) o;
        return Objects.equals(email, obj.email);
    }

    /**
     * Returns a textual description of the user using the format: name, email
     * 
     * @return the user data
     */
    @Override
    public String toString() {
        return String.format("%s - %s", this.name, this.email);
    }

}
