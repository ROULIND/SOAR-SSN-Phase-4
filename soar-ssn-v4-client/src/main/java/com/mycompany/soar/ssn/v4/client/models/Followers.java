/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.soar.ssn.v4.client.models;

/**
 *
 * @author dimitriroulin
 */
public class Followers {
    private Integer id;   
    private Integer followedId;
    private Integer followerId;
  

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

    public Integer getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Integer followedId) {
        this.followedId = followedId;
    }
    
    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    
}
