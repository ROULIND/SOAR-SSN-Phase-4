/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client.models;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author dimitriroulin
 */
public class Posts {
    private Integer postId;
    private String text;
    private Date datePublished;
    //private Collection<Users> usersCollection;
    private Collection<Comments> commentsCollection;
    //private Users users;
    
    public Posts() {
    }
    
    public Posts(Integer postId, String text, Date datePublished, Collection<Comments> commentsCollection) {
        this.postId = postId;
        this.text = text;
        this.datePublished = datePublished;
        
        this.commentsCollection = commentsCollection;
    }
  
    /*
    public Posts(Integer postId, String text, Date datePublished, Collection<Users> usersCollection, Collection<Comments> commentsCollection, Users users) {
        this.postId = postId;
        this.text = text;
        this.datePublished = datePublished;
        this.usersCollection = usersCollection;
        this.commentsCollection = commentsCollection;
        this.users = users;
     
    }*/
    

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    /*
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }*/

    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }
    

    /*
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }*/
}
