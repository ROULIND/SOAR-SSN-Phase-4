/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client.beans;

import com.mycompany.soar.ssn.v4.client.PersistenceClient;
import com.mycompany.soar.ssn.v4.client.exceptions.DoesNotExistException;
import com.mycompany.soar.ssn.v4.client.models.Users;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author dimitriroulin
 *
 * The LoginBean class serves as a managed bean for handling user login and logout functionality.
 * It is session-scoped and is responsible for managing user authentication and the current user session.
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private String username = "";
    private String password = "";
    private Users currentUser;
    private String messageError = "";
    /**
     * Attempts to log in the user based on the provided username and password.
     *
     * @return String The navigation outcome based on the login success or failure.
     */     
    public String userLogsIn() {
        try {
            Users u = PersistenceClient.getInstance().checkPassword(username, password.hashCode());
            if (u != null) {
                currentUser = u;
                return "/MainPage/CreateUserPage.xhtml?faces-redirect=true";
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        messageError = "Username or Passeword incorrect";
        return "/MainPage/MainPage.xhtml?faces-redirect=true";
    }
    
    
    /**
     * Logs out the current user.
     *
     * @return String The navigation outcome to the main page after logout.
     */
    public String userLogsout() {
        currentUser = null;
        return "/MainPage/MainPage.xhtml?faces-redirect=true";
    }
    
    /**
     * Gets the user currently logged in.
     *
     * @return User The user currently logged in.
     */
    public Users getUserLoggedIn() {
        return currentUser;
    }
/**
     * Gets the current user's password.
     *
     * @return String The current user's password.
     */
    public String getPassword() {
        return password;
    }
/**
     * Gets the current user's username.
     *
     * @return String The current user's username.
     */
    public String getUsername() {
        return username;
    }
/**
     * Sets the current user.
     *
     * @param currentUser The user to set as the current user.
     */
    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }
/**
     * Gets the current user.
     *
     * @return User The current user.
     */
    public Users getCurrentUser() {
        return currentUser; //dd
    }
/**
     * Sets the current user's password.
     *
     * @param password The new password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
/**
     * Sets the current user's username.
     *
     * @param username The new username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
/**
     * Gets the error message related to login failures.
     *
     * @return String The error message.
     */
    public String getMessageError() {
        return messageError;
    }
/**
     * Sets a new error message.
     *
     * @param newMessageError The new error message to be set.
     */
    public void setMessageError(String newMessageError) {
        this.messageError = newMessageError;
    }
}

