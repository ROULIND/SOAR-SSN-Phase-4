/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.service.resources;

import com.mycompany.soar.ssn.v4.models.Comments;
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
@Path("comment")
public class CommentRessource {
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
    
    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void create(Comments entity) {
        em.persist(entity);
    }
    
    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Comments> findAll() {
        Query query = em.createNamedQuery("Comments.findAll");
        return query.getResultList();
    }
    
    @GET
    @Path("/find/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Comments find(@PathParam("id") Integer id) {
        return em.find(Comments.class, id);
    }
}
