package com.maxtrain.bootcamp.prs.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, length = 30)
    private String username;
    @Column(length = 30, nullable = false)
    private String password;
    @Column(length = 30, nullable = false)
    private String firstName;
    @Column(length = 30, nullable = false)
    private String lastName;
    @Column(length = 12)
    private String phone;
    private String email;
    @Column(nullable = false)
    private boolean reviewer;
    @Column(nullable = false)
    private boolean admin;

    public User() {}

    public User(int id, String username, String password, String firstName, String lastName, boolean reviewer, boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.reviewer = reviewer;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isReviewer() {
        return reviewer;
    }
    public void setReviewer(boolean reviewer) {
        this.reviewer = reviewer;
    }
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
