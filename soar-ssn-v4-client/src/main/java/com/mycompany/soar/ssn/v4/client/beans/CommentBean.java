package com.mycompany.soar.ssn.v4.client.beans;

import com.mycompany.soar.ssn.v4.client.PersistenceClient;
import com.mycompany.soar.ssn.v4.client.models.Comments;
import com.mycompany.soar.ssn.v4.client.models.Users;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jonathanstefanov
 */
@Named(value = "commentBean")
@SessionScoped
public class CommentBean implements Serializable {
    private String currentCommentText = "";

    /**
     * Sets the current comment text.
     *
     * @param currentCommentText The new comment text to be set.
     */


    public void setCurrentCommentText(String currentCommentText) {
        this.currentCommentText = currentCommentText;
        //Line break after 180 characters
        this.currentCommentText = currentCommentText.replaceAll("(.{180})", "$1\n");
    }
    /**
     * Gets the current comment text.
     *
     * @return String The current comment text.
     */

    public String getCurrentCommentText() {
        return currentCommentText;
    }
    /**
     * Creates and adds a new comment to the specified post by the given user.
     *
     * @param postId The user making the comment.
     * @param userId The post to which the comment is added.
     * @throws IllegalArgumentException If the user, post, or comment text is invalid.
     */

    @Transactional
    public void makeComment(Integer userId, Integer postId) throws IllegalArgumentException {
        Users user = PersistenceClient.getInstance().getUsersById(userId);

        // Verify if the user exists
        if (user == null) {
            throw new IllegalArgumentException("Invalid user.");
        }

        // Verify if the post exists
        if (postId == null) {
            throw new IllegalArgumentException("Invalid post.");
        }

        // Validate the comment text
        if (this.currentCommentText == null) {
            throw new IllegalArgumentException("Comment text cannot be empty.");
        }
        //Verify the lengh of the comment text
        if (this.currentCommentText == null || this.currentCommentText.length() > 256 || this.currentCommentText.length() == 0) {
            // Faces launch an error message
            FacesContext facesContext = FacesContext.getCurrentInstance();

            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Comment must be between 1 and 256 characters long", null));
        }
        else{


            Comments comment = new Comments();
            comment.setText(this.currentCommentText);
            comment.setUsersId(userId);
            comment.setPostId(postId);

            // Set the current date as the date published
            comment.setDatePublished(new Date());

            // Add the comment to the database
            PersistenceClient.getInstance().createComment(comment);



            this.currentCommentText = ""; // reset the comment handler
        }

    }


}