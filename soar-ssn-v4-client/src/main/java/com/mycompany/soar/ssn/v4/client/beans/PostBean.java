/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client.beans;

import com.mycompany.soar.ssn.v4.client.PersistenceClient;
import com.mycompany.soar.ssn.v4.client.models.Posts;
import com.mycompany.soar.ssn.v4.client.models.Users;
import jakarta.enterprise.context.SessionScoped;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author dimitriroulin
 */
@Named(value = "postBean")
@SessionScoped
public class PostBean implements Serializable {
    private Posts selectedPost;

    private String translatedText = "";
    private String currentPostText;
    // List for storing publications
    private String errorMessage;

    public List<Posts> getAllPosts() {
        List<Posts> posts = PersistenceClient.getInstance().getAllPosts();
        Collections.reverse(posts);
        return posts;
    }

    public Posts getPostById(Integer postId) {
        return PersistenceClient.getInstance().getPostById(postId);
    }

    public List<Posts> getPostsByUserId(Integer userId) {
        return PersistenceClient.getInstance().getPostsByUserId(userId);
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }


    public Posts getSelectedPost() {
        return selectedPost;
    }


    public String setSelectedPost(Posts selectedPost) {
        this.selectedPost = selectedPost;
        this.translatedText = "";
        return "/PostPage/PostPage.xhtml?faces-redirect=true";
    }

    public String getCurrentPostText() {
        return currentPostText;
    }

    public void setCurrentPostText(String currentPostText) {
        this.currentPostText = currentPostText;
    }


    public String getErrorMessage() {
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

    public void createPost(Users user) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Posts post = new Posts();
        post.setText(currentPostText);
        post.setUsers(user);
        post.setDatePublished(new Date());
        PersistenceClient.getInstance().createPost(post);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Post created successfully", null));

    }


    public void translatePost(String text) throws IOException {
        String targetLang = "FR";

        // Add faces message
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Post translated successfully", null));



        // Prepare data for the POST request
        String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
        String postData = "text=" + encodedText + "&target_lang=" + targetLang;

        // Create and configure the connection
        URL url = new URL("https://api-free.deepl.com/v2/translate");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "DeepL-Auth-Key " + "25cdd63f-c987-e083-f460-41d23773b524:fx");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connection.setDoOutput(true);

        // Send the request
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = postData.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Read the response
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        // Extract and return the translated text
        // Note: This is a simple extraction for demonstration. For production, consider using a JSON parsing library like Jackson or Gson.
        String responseText = response.toString();
        String translatedText = responseText.substring(responseText.indexOf("\"text\":\"") + 8, responseText.indexOf("\"}", responseText.indexOf("\"text\":\"")));



        this.translatedText = translatedText;
    }
    
    
    
}
