/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.service.resources;

import com.mycompany.soar.ssn.v4.models.Comments;
import com.mycompany.soar.ssn.v4.models.Posts;
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
@Path("comment")
public class CommentRessource {
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
    
    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Transactional
    public void create(Comments comment) {
        // Find the post associated with the comment
        Posts post = em.find(Posts.class, comment.getPostId());
        if (post == null) {
            // Handle the case where the post is not found
            throw new IllegalArgumentException("Post with ID " + comment.getPostId() + " not found.");
        }

        // Persist the comment first
        em.persist(comment);
        em.flush(); // Ensure the comment ID is generated

        // Add the comment to the post
        post.getCommentsCollection().add(comment);
        // Optionally, you can also set the post in the comment if you have a bidirectional relationship
        // comment.setPost(post);

        // Merge the post to update it with the new comment
        em.merge(post);
    }

    
    @DELETE
    @Path("/remove/{id}")
    @Transactional
    public void delete(@PathParam("id") Integer id) {
        em.remove(em.merge(em.find(Comments.class, id)));
    }
    
    @GET
    @Path("/findByUserId/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Comments> findByUserId(@PathParam("userId") Integer userId) {
        Query query = em.createNamedQuery("Comments.findByUserId", Comments.class);
        query.setParameter("userId", userId);
        return query.getResultList();
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
