package com.example.API_manager.service;

import com.example.API_manager.model.ApiConfiguration;
import com.example.API_manager.model.Endpoint;
import com.example.API_manager.repository.ApiConfigurationRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiConfigurationService {

    private final ApiConfigurationRepository repository;

    public ApiConfigurationService(ApiConfigurationRepository repository) {
        this.repository = repository;
    }

    // Create a new API configuration
     public ApiConfiguration createApi(ApiConfiguration api) {
        // Get the current user's email from the security context
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Set the email in the API configuration
        api.setUserEmail(userEmail);
        return repository.save(api);
    }

    // Update an existing API configuration and its endpoints by ID
    public ApiConfiguration updateApi(Long id, ApiConfiguration updatedApi) {
        return repository.findById(id)
                .map(existingApi -> {
                    // Get the current user's email from the security context
                    String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

                    // Update the main API configuration fields
                    existingApi.setName(updatedApi.getName());
                    existingApi.setBaseUri(updatedApi.getBaseUri());

                    // Set the updated user email (this is for tracking purposes)
                    existingApi.setUserEmail(userEmail);

                    // Handle updating or adding endpoints
                    List<Endpoint> updatedEndpoints = updatedApi.getEndUris();
                    for (int i = 0; i < updatedEndpoints.size(); i++) {
                        Endpoint updatedEndpoint = updatedEndpoints.get(i);

                        // Update or add new endpoint in the list
                        if (i < existingApi.getEndUris().size()) {
                            Endpoint existingEndpoint = existingApi.getEndUris().get(i);
                            existingEndpoint.setEndUri(updatedEndpoint.getEndUri());
                            existingEndpoint.setMethod(updatedEndpoint.getMethod());
                            existingEndpoint.setHeaders(updatedEndpoint.getHeaders());
                            existingEndpoint.setBodyType(updatedEndpoint.getBodyType());
                            existingEndpoint.setContentType(updatedEndpoint.getContentType());
                            existingEndpoint.setBodyContent(updatedEndpoint.getBodyContent());
                        } else {
                            existingApi.getEndUris().add(updatedEndpoint);
                        }
                    }

                    // Remove extra endpoints if any
                    if (updatedEndpoints.size() < existingApi.getEndUris().size()) {
                        existingApi.getEndUris().subList(updatedEndpoints.size(), existingApi.getEndUris().size()).clear();
                    }

                    // Save the updated API configuration
                    return repository.save(existingApi);
                })
                .orElseThrow(() -> new RuntimeException("API not found with ID: " + id));
    }

    // Delete an API configuration by ID
    public void deleteApi(Long id) {
        repository.deleteById(id);
    }

    // Get a list of all API configurations
    public List<ApiConfiguration> getAllApis() {
        return repository.findAll();
    }

    // Get an API configuration by ID
    public ApiConfiguration getApiById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("API not found with ID: " + id));
    }

    // Search APIs by name
    public List<ApiConfiguration> searchApisByName(String name) {
        return repository.findByNameContaining(name);
    }
}
