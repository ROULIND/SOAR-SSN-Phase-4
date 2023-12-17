/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client.beans;

import com.mycompany.soar.ssn.v4.client.PersistenceClient;
import com.mycompany.soar.ssn.v4.client.models.Posts;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author dimitriroulin
 */
@Named(value = "postBean")
@SessionScoped
public class PostBean implements Serializable {
    private Posts selectedPost;
    private String currentPostText;
    // List for storing publications
    private String errorMessage;
    
    public List<Posts> getAllPosts() {
        return PersistenceClient.getInstance().getAllPosts();
    }
    
    public Posts getPostById(Integer postId){
        return PersistenceClient.getInstance().getPostById(postId);
    }
    
    public List<Posts> getPostsByUserId(Integer userId) {
        return PersistenceClient.getInstance().getPostsByUserId(userId);
    }
    
    
    public Posts getSelectedPost(){
        return selectedPost;
    }
    
    
    public String setSelectedPost(Posts selectedPost){
        this.selectedPost = selectedPost;
        return "/PostPage/PostPage.xhtml?faces-redirect=true";
    }
    
    public String getCurrentPostText(){
        return currentPostText;
    }
    
    public void setCurrentPostText(String currentPostText) {
        this.currentPostText = currentPostText;
    }
    
    
    public String getErrorMessage(){
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void toggleLikePost(Integer postId, Integer userId) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        PersistenceClient.getInstance().toggleLikePost(postId, userId);

        setSelectedPost(getPostById(postId)); // + JS Force reload

    }

    public boolean isPostLikedByUser(Integer postId, Integer userId) {
        return PersistenceClient.getInstance().isPostLikedByUser(postId, userId);

    }
    
}
