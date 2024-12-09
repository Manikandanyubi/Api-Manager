package com.example.API_manager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Data
@Entity
public class Endpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endUri;

    private String method;

    @ElementCollection
    private Map<String, String> headers;

    private String bodyType; // e.g., "Raw" or "Form"

    private String contentType; // e.g., "application/json"

    private String params;

    @Lob
    private String bodyContent; // Example data for raw or form data

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndUri() {
        return endUri;
    }

    public void setEndUri(String endUri) {
        this.endUri = endUri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
