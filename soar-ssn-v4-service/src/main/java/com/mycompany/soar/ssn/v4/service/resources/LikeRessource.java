/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.service.resources;

import com.mycompany.soar.ssn.v4.models.Likes;
import com.mycompany.soar.ssn.v4.models.Posts;
import com.mycompany.soar.ssn.v4.models.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author dimitriroulin
 */
@Path("like")
public class LikeRessource {
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
      
    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Likes> findAll() {
        Query query = em.createNamedQuery("Likes.findAll");
        return query.getResultList();
    }
    
    @GET
    @Path("/findByUserId/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Likes> findByUserId(@PathParam("userId") Integer userId) {
        Query query = em.createNamedQuery("Likes.findByUserId", Likes.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @DELETE
    @Path("/remove/{id}")
    @Transactional
    public void delete(@PathParam("id") Integer id) {
        em.remove(em.merge(em.find(Likes.class, id)));
    }
}
