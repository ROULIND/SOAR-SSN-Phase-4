/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.models;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 *
 * @author dimitriroulin
 */
@Entity
@Table(name = "Followers")
@NamedQueries({
    @NamedQuery(name = "Followers.findAll", query = "SELECT f FROM Followers f"),
    @NamedQuery(name = "Followers.findByFollowerId", query = "SELECT f FROM Followers f WHERE f.followerId = :followerId"),
    @NamedQuery(name = "Followers.findByFollowedId", query = "SELECT f FROM Followers f WHERE f.followedId = :followedId"),
})
public class Followers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "follower_id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer followerId;

    @Basic(optional = false)
    @Column(name = "followed_user_id")
    private Integer followedId;

    public Followers() {
    }

    public Followers(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public Integer getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Integer followedId) {
        this.followedId = followedId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Followers)) {
            return false;
        }
        Followers other = (Followers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.soar.ssn.v4.models.Followers[ id=" + id + " ]";
    }
}
