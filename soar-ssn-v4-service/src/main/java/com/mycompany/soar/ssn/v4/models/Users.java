/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.models;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Basic;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author dimitriroulin
 */
@Entity
@Table(name = "Users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPictureProfile", query = "SELECT u FROM Users u WHERE u.pictureProfile = :pictureProfile"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "pictureProfile")
    private String pictureProfile;
    @Basic(optional = false)
    @Column(name = "password")
    private int password;
    @JoinTable(name = "Followers", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "followed_user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "followed_user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "followed_user_id", referencedColumnName = "user_id")})
    @ManyToMany
    @JsonbTransient
    private Collection<Users> usersCollection;
    @ManyToMany(mappedBy = "usersCollection")
    @JsonbTransient
    private Collection<Users> usersCollection1;
    
    // Changes in the model : Now we get a list of postId insted of Posts object in order to avoid infinite cycling in Json creation
    @ElementCollection
    @CollectionTable(name = "Posts", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "post_id")
    private Collection<Integer> postIds;
    
    /*
    @ManyToMany(mappedBy = "usersCollection")
    @JsonbTransient
    private Collection<Posts> postsCollection;
    */
    
    @OneToMany(mappedBy = "users")
    @JsonbTransient
    private Collection<Comments> commentsCollection;
    @OneToMany(mappedBy = "users")
    @JsonbTransient
    private Collection<Posts> postsCollection1;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String firstName, String lastName, String username, String email, String pictureProfile, int password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.pictureProfile = pictureProfile;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureProfile() {
        return pictureProfile;
    }

    public void setPictureProfile(String pictureProfile) {
        this.pictureProfile = pictureProfile;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    public Collection<Users> getUsersCollection1() {
        return usersCollection1;
    }

    public void setUsersCollection1(Collection<Users> usersCollection1) {
        this.usersCollection1 = usersCollection1;
    }

    
     public Collection<Integer> getPostIds() {
        return postIds;
    }

    public void setPostIds(Collection<Integer> postIds) {
        this.postIds = postIds;
    }
    
    
    /*
    public Collection<Posts> getPostsCollection() {
        return postsCollection;
    }

    public void setPostsCollection(Collection<Posts> postsCollection) {
        this.postsCollection = postsCollection;
    }*/

    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    public Collection<Posts> getPostsCollection1() {
        return postsCollection1;
    }

    public void setPostsCollection1(Collection<Posts> postsCollection1) {
        this.postsCollection1 = postsCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.soar.ssn.v4.models.Users[ userId=" + userId + " ]";
    }
    
}
