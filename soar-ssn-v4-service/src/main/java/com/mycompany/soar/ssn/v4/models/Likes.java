/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.models;

/**
 *
 * @author dimitriroulin
 */

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Likes")
@NamedQueries({
    @NamedQuery(name = "Likes.findAll", query = "SELECT l FROM Likes l"),
    @NamedQuery(name = "Likes.findByLikeId", query = "SELECT l FROM Likes l WHERE l.likeId = :likeId"),
    @NamedQuery(name = "Likes.findByUserId", query = "SELECT l FROM Likes l WHERE l.userId = :userId"),
    @NamedQuery(name = "Likes.findByPostId", query = "SELECT l FROM Likes l WHERE l.postId = :postId")
})
public class Likes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "like_id")
    private Integer likeId;

    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;

    @Basic(optional = false)
    @Column(name = "post_id")
    private Integer postId;

    public Likes() {
    }

    public Likes(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (likeId != null ? likeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Likes)) {
            return false;
        }
        Likes other = (Likes) object;
        if ((this.likeId == null && other.likeId != null) || (this.likeId != null && !this.likeId.equals(other.likeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.soar.ssn.v4.models.Likes[ likeId=" + likeId + " ]";
    }
}