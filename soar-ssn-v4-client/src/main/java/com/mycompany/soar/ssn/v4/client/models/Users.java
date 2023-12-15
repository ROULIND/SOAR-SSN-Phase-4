/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client.models;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author dimitriroulin
 */

public class Users {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String pictureProfile;
    private Integer password;
    private Collection<Integer> postIds;
    private Collection<Comments> commentsCollection;

    // Constructeur par défaut
    public Users() {
    }

    // Constructeur avec tous les attributs
    public Users(Integer userId, String firstName, String lastName, 
                 String username, String email, String pictureProfile, 
                 Integer password, Collection<Integer> postIds, 
                 List<Comments> commentsCollection) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.pictureProfile = pictureProfile;
        this.password = password;
        this.postIds = postIds;
        this.commentsCollection = commentsCollection;
    }

    // Getters et Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureProfile() {
        return pictureProfile;
    }

    public void setPictureProfile(String pictureProfile) {
        this.pictureProfile = pictureProfile;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Collection<Integer> getPostsCollection() {
        return postIds;
    }

    public void setPostsCollection(Collection<Integer> postIds) {
        this.postIds = postIds;
    }

    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }
}
