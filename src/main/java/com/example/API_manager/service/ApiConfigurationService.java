package com.example.API_manager.service;

import com.example.API_manager.model.ApiConfiguration;
import com.example.API_manager.repository.ApiConfigurationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiConfigurationService {

    private final ApiConfigurationRepository repository;

    public ApiConfigurationService(ApiConfigurationRepository repository) {
        this.repository = repository;
    }

    // Create a new API configuration
    public ApiConfiguration createApi(ApiConfiguration api) {
        return repository.save(api);
    }

    // Update an existing API configuration by ID
    public ApiConfiguration updateApi(Long id, ApiConfiguration updatedApi) {
        Optional<ApiConfiguration> existingApi = repository.findById(id);
        if (existingApi.isPresent()) {
            ApiConfiguration api = existingApi.get();
            api.setName(updatedApi.getName());
            api.setBaseUri(updatedApi.getBaseUri());
            api.setEndUri(updatedApi.getEndUri());
            api.setMethod(updatedApi.getMethod());
            api.setHeaders(updatedApi.getHeaders());
            api.setBodyType(updatedApi.getBodyType());
            api.setContentType(updatedApi.getContentType());
            return repository.save(api);
        } else {
            throw new RuntimeException("API not found with ID: " + id);
        }
    }

    // Delete an API configuration by ID
    public void deleteApi(Long id) {
        repository.deleteById(id);
    }

    // Get a list of all API configurations
    public List<ApiConfiguration> getAllApis() {
        return repository.findAll();
    }
}
