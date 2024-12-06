package com.example.apimanager.model;

import jakarta.persistence.*;

@Entity
public class Endpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url; // The URL of the endpoint
    private String method; // HTTP method (GET, POST, etc.)
    private String description; // A description of what the endpoint does

    @ManyToOne
    @JoinColumn(name = "api_id") // Join with the `api` table on the `api_id` column
    private Api api;

    // Default constructor
    public Endpoint() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    @Override
    public String toString() {
        return "Endpoint{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", description='" + description + '\'' +
                ", api=" + api +
                '}';
    }
}
