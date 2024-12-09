package com.example.API_manager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ApiConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "base_uri")
    private String baseUri;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "api_configuration_id")
    private List<Endpoint> endUris;

    private String userEmail;  // Added user email field

    @ElementCollection
    @CollectionTable(name = "collaborators", joinColumns = @JoinColumn(name = "api_configuration_id"))
    @Column(name = "email")
    private List<String> collaborators;  // Added collaborators field

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public List<Endpoint> getEndUris() {
        return endUris;
    }

    public void setEndUris(List<Endpoint> endUris) {
        this.endUris = endUris;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<String> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<String> collaborators) {
        this.collaborators = collaborators;
    }
}