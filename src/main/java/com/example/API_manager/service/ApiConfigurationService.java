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

    public ApiConfiguration createApi(ApiConfiguration api) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        api.setUserEmail(userEmail);
        return repository.save(api);
    }

    public ApiConfiguration updateApi(Long id, ApiConfiguration updatedApi) {
        return repository.findById(id)
                .map(existingApi -> {
                    String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
                    existingApi.setName(updatedApi.getName());
                    existingApi.setBaseUri(updatedApi.getBaseUri());
                    existingApi.setUserEmail(userEmail);
                    List<Endpoint> updatedEndpoints = updatedApi.getEndUris();
                    for (int i = 0; i < updatedEndpoints.size(); i++) {
                        Endpoint updatedEndpoint = updatedEndpoints.get(i);
                        if (i < existingApi.getEndUris().size()) {
                            Endpoint existingEndpoint = existingApi.getEndUris().get(i);
                            existingEndpoint.setEndUri(updatedEndpoint.getEndUri());
                            existingEndpoint.setMethod(updatedEndpoint.getMethod());
                            existingEndpoint.setHeaders(updatedEndpoint.getHeaders());
                            existingEndpoint.setBodyType(updatedEndpoint.getBodyType());
                            existingEndpoint.setContentType(updatedEndpoint.getContentType());
                            existingEndpoint.setBodyContent(updatedEndpoint.getBodyContent());
                            existingEndpoint.setParams(updatedEndpoint.getParams());
                        } else {
                            existingApi.getEndUris().add(updatedEndpoint);
                        }
                    }
                    if (updatedEndpoints.size() < existingApi.getEndUris().size()) {
                        existingApi.getEndUris().subList(updatedEndpoints.size(), existingApi.getEndUris().size()).clear();
                    }
                    return repository.save(existingApi);
                })
                .orElseThrow(() -> new RuntimeException("API not found with ID: " + id));
    }

    public void deleteApi(Long id) {
        repository.deleteById(id);
    }

    public List<ApiConfiguration> getAllApis() {
        return repository.findAll();
    }

    public ApiConfiguration getApiById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("API not found with ID: " + id));
    }

    public List<ApiConfiguration> searchApisByName(String name) {
        return repository.findByNameContaining(name);
    }

    public ApiConfiguration addCollaborator(Long id, String email) {
        return repository.findById(id)
                .map(existingApi -> {
                    List<String> collaborators = existingApi.getCollaborators();
                    if (!collaborators.contains(email)) {
                        collaborators.add(email);
                    }
                    return repository.save(existingApi);
                })
                .orElseThrow(() -> new RuntimeException("API not found with ID: " + id));
    }

    public ApiConfiguration updateCollaborator(Long id, String oldEmail, String newEmail) {
        return repository.findById(id)
                .map(existingApi -> {
                    List<String> collaborators = existingApi.getCollaborators();
                    int index = collaborators.indexOf(oldEmail);
                    if (index != -1) {
                        collaborators.set(index, newEmail);
                    } else {
                        throw new RuntimeException("Collaborator not found with email: " + oldEmail);
                    }
                    return repository.save(existingApi);
                })
                .orElseThrow(() -> new RuntimeException("API not found with ID: " + id));
    }

    public List<String> getCollaborators(Long id) {
        return repository.findById(id)
                .map(ApiConfiguration::getCollaborators)
                .orElseThrow(() -> new RuntimeException("API not found with ID: " + id));
    }

    public ApiConfiguration deleteCollaborator(Long id, String email) {
        return repository.findById(id)
                .map(existingApi -> {
                    List<String> collaborators = existingApi.getCollaborators();
                    if (collaborators.contains(email)) {
                        collaborators.remove(email);
                    } else {
                        throw new RuntimeException("Collaborator not found with email: " + email);
                    }
                    return repository.save(existingApi);
                })
                .orElseThrow(() -> new RuntimeException("API not found with ID: " + id));
    }
}