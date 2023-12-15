/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client.beans;

import com.mycompany.soar.ssn.v4.client.PersistenceClient;
import com.mycompany.soar.ssn.v4.client.models.Posts;
import jakarta.enterprise.context.SessionScoped;
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
    // List for storing publications
    private String errorMessage;
    
    public List<Posts> getAllPosts() {
        return PersistenceClient.getInstance().getAllPosts();
    }
    
    public Posts getPostById(){
        return PersistenceClient.getInstance().getPostById();
    }
    
    
    public Posts getSelectedPost(){
        return selectedPost;
    }
    
    
    public void setSelectedPost(Posts selectedPost){
        this.selectedPost = selectedPost;
    }
    
}
