/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.service.resources;

import com.mycompany.soar.ssn.v4.models.Comments;
import com.mycompany.soar.ssn.v4.models.Followers;
import com.mycompany.soar.ssn.v4.models.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
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
@Path("follower")
public class FollowerRessource {
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
    
    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void create(Followers entity) {
        em.persist(entity);
    }
    
    @DELETE
    @Path("/remove/{id}")
    @Transactional
    public void delete(@PathParam("id") Integer id) {
        em.remove(em.merge(em.find(Followers.class, id)));
    }
    
    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Followers> findAll() {
        Query query = em.createNamedQuery("Followers.findAll");
        return query.getResultList();
    }
    
    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Followers find(@PathParam("id") Integer id) {
        return em.find(Followers.class, id);
    }
    
    //This request return a list of all the user a person follow --> list of followed people
    // Ex: With the user_id(2) we have this in return : [{"followedId":98,"followerId":2,"id":3},{"followedId":97,"followerId":2,"id":4}]
    @GET
    @Path("/findByFollowerId/{followerId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Followers> findByFollowerId(@PathParam("followerId") Integer followerId) {
        Query query = em.createNamedQuery("Followers.findByFollowerId");
        query.setParameter("followerId", followerId);
        return query.getResultList();
    }
    
    //This request return a list of all the user that follow a person --> list of followers
    // Ex: With the user_id(2) we have this in return : [{"followedId":2,"followerId":50,"id":99},{"followedId":2,"followerId":100,"id":199}]
    @GET
    @Path("/findByFollowedId/{followedId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Followers> findByFollowedId(@PathParam("followedId") Integer followedId) {
        Query query = em.createNamedQuery("Followers.findByFollowedId");
        query.setParameter("followedId", followedId);
        return query.getResultList();
    }
    
    
}
