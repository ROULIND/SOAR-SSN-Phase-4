/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.soar.ssn.v4.client.exceptions.AlreadyExistsException;
import com.mycompany.soar.ssn.v4.client.exceptions.DoesNotExistException;

import com.mycompany.soar.ssn.v4.client.models.Users;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author dimitriroulin
 */
public class PersistenceClient {

    private static final String USERS_URL = "http://localhost:8080/soar-ssn-v4-service/resources/user";

    private static Client client;
    private static PersistenceClient instance;
    
    
    private PersistenceClient() {
        PersistenceClient.client = ClientBuilder.newClient();
    }

    public static PersistenceClient getInstance() {
        if (instance == null) {
            instance = new PersistenceClient();
        }
        return instance;
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
    
}
