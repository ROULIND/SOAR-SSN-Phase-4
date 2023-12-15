/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client.models;

import java.util.Date;

/**
 *
 * @author dimitriroulin
 */
public class Comments {
   private Integer commentId;
    private String text;
    private Date datePublished;
    //private Posts postId;
    //private Users users; 
    
    public Comments() {
    }

    public Comments(Integer commentId) {
        this.commentId = commentId;
    }

    public Comments(Integer commentId, String text) {
        this.commentId = commentId;
        this.text = text;
    }
    
    

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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
    public Posts getPostId() {
        return postId;
    }

    public void setPostId(Posts postId) {
        this.postId = postId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }*/

}
