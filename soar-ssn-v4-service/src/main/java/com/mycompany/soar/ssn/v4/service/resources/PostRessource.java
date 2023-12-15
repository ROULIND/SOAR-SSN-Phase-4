/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.service.resources;

import com.mycompany.soar.ssn.v4.models.Posts;
import com.mycompany.soar.ssn.v4.models.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author dimitriroulin
 */
@Path("post")
public class PostRessource {
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
    
    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void create(Posts entity) {
        em.persist(entity);
    }
    
    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Posts> findAll() {
        Query query = em.createNamedQuery("Posts.findAll");
        return query.getResultList();
    }
    
    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Posts find(@PathParam("id") Integer id) {
        return em.find(Posts.class, id);
    }
    
    @GET
    @Path("/findByUserId/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Posts> findByUserId(@PathParam("userId") Integer userId) {
        Query query = em.createNamedQuery("Posts.findByUserId", Posts.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @POST
    @Path("/like/{postId}/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Transactional
    public void toggleLike(@PathParam("postId") Integer postId, @PathParam("userId") Integer userId) {
        Posts post = em.find(Posts.class, postId);
        Users user = em.find(Users.class, userId);
        if (post.getUsersCollection().contains(user)) {
            post.getUsersCollection().remove(user);
        } else {
            post.getUsersCollection().add(user);
        }
    }

    @GET
    @Path("/isLiked/{postId}/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public boolean isLiked(@PathParam("postId") Integer postId, @PathParam("userId") Integer userId) {
        Posts post = em.find(Posts.class, postId);
        Users user = em.find(Users.class, userId);
        return post.getUsersCollection().contains(user);
    }
    
   
}
