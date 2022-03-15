package com.maxtrain.bootcamp.prs.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 80, nullable = false)
    private String description;
    @Column(length = 80, nullable = false)
    private String justification;
    @Column(length = 80)
    private String rejectionReason;
    @Column(length = 20, nullable = false)
    private String deliveryMode = "Pickup";
    @Column(length = 10, nullable = false)
    @ReadOnlyProperty
    private String status = "NEW";
    @Column(nullable = false, columnDefinition = "decimal(11,2) not null default 0.0")
    @ReadOnlyProperty
    private double total = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "request")
    @JsonManagedReference
    private List<Requestline> requestlines = new ArrayList<>();

    public Request() {}
    public Request(int id, String description, String justification, String deliveryMode, User user) {
        this.id = id;
        this.description = description;
        this.justification = justification;
        this.deliveryMode = deliveryMode;
        this.user = user;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getJustification() {
        return justification;
    }
    public void setJustification(String justification) {
        this.justification = justification;
    }
    public String getRejectionReason() {
        return rejectionReason;
    }
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
    public String getDeliveryMode() {
        return deliveryMode;
    }
    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Requestline> getRequestlines() {
        return requestlines;
    }
    public void setRequestlines(List<Requestline> requestlines) {
        this.requestlines = requestlines;
    }
}
