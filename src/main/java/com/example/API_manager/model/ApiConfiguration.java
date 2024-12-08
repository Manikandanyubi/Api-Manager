package com.example.API_manager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Data
@Entity
public class ApiConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "base_uri")
    private String baseUri;

    @Column(name = "end_uri")
    private String endUri;

    private String method;

    @ElementCollection
    private Map<String, String> headers;

    @Column(name = "body_type")
    private String bodyType; // Raw, Form

    @Column(name = "content_type")
    private String contentType; // application/json, application/x-www-form-urlencoded, multipart/form-data

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBaseUri() { return baseUri; }
    public void setBaseUri(String baseUri) { this.baseUri = baseUri; }

    public String getEndUri() { return endUri; }
    public void setEndUri(String endUri) { this.endUri = endUri; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public Map<String, String> getHeaders() { return headers; }
    public void setHeaders(Map<String, String> headers) { this.headers = headers; }

    public String getBodyType() { return bodyType; }
    public void setBodyType(String bodyType) { this.bodyType = bodyType; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
}
