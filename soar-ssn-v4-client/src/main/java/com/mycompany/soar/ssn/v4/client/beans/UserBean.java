/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client.beans;

import com.mycompany.soar.ssn.v4.client.PersistenceClient;
import com.mycompany.soar.ssn.v4.client.exceptions.AlreadyExistsException;
import com.mycompany.soar.ssn.v4.client.models.Users;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author dimitriroulin
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    // Fields representing user attributes and uploaded profile picture

    private String username ="";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String password = "";
    private String pictureProfile = "Default-PP.jpeg";
    private Users selectedUser;
    
    
/**
     * Navigates to the user's profile page.
     *
     * @param user The user whose profile page is being accessed.
     * @return String The navigation outcome.
     */
    public String goToProfilePage(Users user) {
        this.selectedUser = user;
        return "/ProfilePage/ProfilePage.xhtml?faces-redirect=true";
    }
/**
     * Navigates to the user's information page.
     *
     * @param user The user whose information page is being accessed.
     * @return String The navigation outcome.
     */    
    public String goToInfoPage(Users user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.pictureProfile = user.getPictureProfile();
        return "/UserPage/UserInfoPage.xhtml?faces-redirect=true";
    }
    
    /**
     * Creates a new user and adds it to the Database.
     * Displays appropriate messages based on success or failure.
     */  
    public void createAUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            boolean a = !PersistenceClient.getInstance().emailExists(email);
            boolean b = PersistenceClient.getInstance().getUsersByName(username) == null;
            if (a && b) {
                Users newUser = new Users();
                newUser.setUsername(username);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setPictureProfile(pictureProfile);
                newUser.setPassword(password.hashCode());
                PersistenceClient.getInstance().createUser(newUser);
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User created successfully!" + username + firstName + lastName + email + password, null));
            }
        } catch (AlreadyExistsException ex) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            System.out.println(ex.getMessage());
        } 
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        // empty values
        /*this.email = "";
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";*/
    }
    
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
  
     
    public Users getSelectedUser() {
        return selectedUser;
    }
    
    public String getPictureProfile() {
        return pictureProfile;
    }
    

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setSelectedUser(Users user) {
        this.selectedUser = user;
    }
    
    public void setPictureProfile(String pictureProfile) {
        this.pictureProfile = pictureProfile;
    }


    
}
