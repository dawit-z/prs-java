package com.maxtrain.bootcamp.prs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "requestlines")
public class Requestline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "request_id")
    @JsonBackReference
    private Request request;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity = 1;

    public Requestline() {

    }
    public Requestline(Request request, Product product, int quantity) {
        this.request = request;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Request getRequest() {
        return request;
    }
    public void setRequest(Request request) {
        this.request = request;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
