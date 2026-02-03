package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;
    private String email;
    private String password;
    private String role;

    private Long famliyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getFamliyId() {
        return famliyId;
    }

    public void setFamliyId(Long famliyId) {
        this.famliyId = famliyId;
    }

    @Override
    public String toString() {
        return "Member [id=" + id + ", user=" + user + ", email=" + email + ", password=" + password + ", role=" + role
                + ", famliyId=" + famliyId + "]";
    }
}
