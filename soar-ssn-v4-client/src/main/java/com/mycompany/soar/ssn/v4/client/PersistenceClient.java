/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.soar.ssn.v4.client.exceptions.AlreadyExistsException;
import com.mycompany.soar.ssn.v4.client.exceptions.DoesNotExistException;
import com.mycompany.soar.ssn.v4.client.models.Comments;
import com.mycompany.soar.ssn.v4.client.models.Followers;
import com.mycompany.soar.ssn.v4.client.models.Posts;

import com.mycompany.soar.ssn.v4.client.models.Users;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author dimitriroulin
 */
public class PersistenceClient {

    private static final String USERS_URL = "http://localhost:8080/soar-ssn-v4-service/resources/user";
    private static final String POSTS_URL = "http://localhost:8080/soar-ssn-v4-service/resources/post";
    private static final String COMMENTS_URL = "http://localhost:8080/soar-ssn-v4-service/resources/comment";

    private static final String FOLLOWERS_URL = "http://localhost:8080/soar-ssn-v4-service/resources/follower";

    private static Client client;
    private static PersistenceClient instance;
    public List<Followers> get;
    
    
    private PersistenceClient() {
        PersistenceClient.client = ClientBuilder.newClient();
    }

    public static PersistenceClient getInstance() {
        if (instance == null) {
            instance = new PersistenceClient();
        }
        return instance;
    }
    
    public void updateUser(Users user) {
        client.target(USERS_URL + "/edit/" + user.getUserId()).request().put(Entity.entity(user, "application/json"));
    }
    
    public Users checkPassword(String username, int password) throws DoesNotExistException {
    Users u = getUsersByName(username);
    if (u != null && u.getUsername().equals(username) && u.getPassword() == password) {
        return u;
    }
    throw new DoesNotExistException("User " + username + " does not exist or incorrect password.");
}
    
    
    public Users getUsersByName(String username) {
        Users u = parseUser(client.target(USERS_URL + "/findByName/" + username).request().get(String.class));
        return u;
    }
    
    
     public Users getUsersById(Integer userId) {
        Users u = parseUser(client.target(USERS_URL + "/find/" + userId).request().get(String.class));
        return u;
    }
    
    private List<Users> parseUserList(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<List<Users>>() {
        }.getType());
    }
    

    private Users parseUser(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, Users.class);
    }
    
    
    
    public void createUser(Users user) {
        WebTarget target = client.target(USERS_URL + "/create");
        Entity theEntity = Entity.entity(user, "application/json");
        Response response = target.request().post(theEntity);
    }
    
    public boolean emailExists(String email) throws AlreadyExistsException {
        return client.target(USERS_URL + "/emailExists/" + email).request().get().readEntity(Boolean.class);
    }
    
   
    
    private Posts parsePost(String result) {
        Gson gson1 = new Gson();
        return gson1.fromJson(result, Posts.class);
    }
    
    
    private List<Posts> parsePostsList(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<List<Posts>>() {
        }.getType());
    }
    
    public List<Posts> getAllPosts() {
        return parsePostsList(client.target(POSTS_URL + "/findAll").request().get(String.class));
    }
    
    
    public Posts getPostById(Integer postId){
        Posts p = parsePost(client.target(POSTS_URL + "/find/" + postId).request().get(String.class));
        return p;
    }
    
    public List<Posts> getPostsByUserId(Integer userId) {
        return parsePostsList(client.target(POSTS_URL + "/findByUserId/"+ userId).request().get(String.class));
    }

    public void toggleLikePost(Integer postId, Integer userId) {
        WebTarget target = client.target(POSTS_URL + "/like/" + postId + "/" + userId);
        target.request().post(null); // No entity is required for this POST request

    }

    public boolean isPostLikedByUser(Integer postId, Integer userId) {
        WebTarget target = client.target(POSTS_URL + "/isLiked/" + postId + "/" + userId);
        return target.request().get(Boolean.class);
    }

    public void createComment(Comments comment) {
        WebTarget target = client.target(COMMENTS_URL + "/create");
        Entity theEntity = Entity.entity(comment, MediaType.APPLICATION_JSON);
        Response response = target.request().post(theEntity);
        if(response.getStatus() != Response.Status.OK.getStatusCode() && response.getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new RuntimeException("Failed to create comment: " + response);
        }
    }







    
    
    
    
    /* ------------- FOLLOWERS REQUESTS ---------------- */
    
    private List<Followers> parseFollowersList(String result) {
        Gson gson = new Gson();
        return gson.fromJson(result, new TypeToken<List<Followers>>() {
        }.getType());
    }
    
    
    public List<Followers> findByFollowerId(Integer followerId) {
        return parseFollowersList(client.target(FOLLOWERS_URL + "/findByFollowerId/"+ followerId).request().get(String.class));
    }
    
    public List<Followers> findByFollowedId(Integer followerId) {
        return parseFollowersList(client.target(FOLLOWERS_URL + "/findByFollowedId/"+ followerId).request().get(String.class));
    }
    
    
    public void toggleFollowUser(Integer userId, Integer userToFollowId) {
        WebTarget target;
        if (isFollowedBy(userToFollowId, userId)) {
            // User is already followed, so remove the follower
            int followId = findFollowersEntryId(userToFollowId, userId);
            target = client.target(FOLLOWERS_URL + "/remove/" + followId);
            target.request().delete();
        } else {
            // User is not followed, so add as a new follower
            Followers follower = new Followers();
            follower.setFollowerId(userId);
            follower.setFollowedId(userToFollowId);

            target = client.target(FOLLOWERS_URL + "/create");
            Entity<Followers> theEntity = Entity.entity(follower, MediaType.APPLICATION_JSON);
            target.request().post(theEntity);
        }
    }
    
    public boolean isFollowedBy(Integer userFollowedId, Integer userFollowingId){
        List<Followers> followers = findByFollowedId(userFollowedId);
        boolean checkFollowedBy = false;
        for (Followers follower : followers) {
            if (follower.getFollowerId().equals(userFollowingId)) {
                checkFollowedBy = true;
                break; // Break the loop once a match is found
            }
        }
        return checkFollowedBy;
    }
    
    public int findFollowersEntryId(Integer userFollowedId, Integer userFollowingId){
        List<Followers> followers = findByFollowedId(userFollowedId);
        boolean checkFollowedBy = false;
        for (Followers follower : followers) {
            if (follower.getFollowerId().equals(userFollowingId)) {
                return follower.getId();
            }
        }
        return 0;
    }


    public void createPost(Posts post) {
        WebTarget target = client.target(POSTS_URL + "/create");
        Entity<Posts> theEntity = Entity.entity(post, MediaType.APPLICATION_JSON);
        Response response = target.request().post(theEntity);
        if(response.getStatus() != Response.Status.OK.getStatusCode() && response.getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new RuntimeException("Failed to create post: " + response);
        }
    }

}
    
   
    
    