package com.example.API_manager.controller;

import com.example.API_manager.model.ApiConfiguration;
import com.example.API_manager.service.ApiConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/admin")
public class ApiConfigurationController {

    private final ApiConfigurationService apiConfigurationService;

    public ApiConfigurationController(ApiConfigurationService apiConfigurationService) {
        this.apiConfigurationService = apiConfigurationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiConfiguration> createApi(@RequestBody ApiConfiguration api) {
        return ResponseEntity.ok(apiConfigurationService.createApi(api));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiConfiguration> updateApi(@PathVariable Long id, @RequestBody ApiConfiguration api) {
        try {
            ApiConfiguration updatedApi = apiConfigurationService.updateApi(id, api);
            return ResponseEntity.ok(updatedApi);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteApi(@PathVariable Long id) {
        // Delegate to service for deletion
        apiConfigurationService.deleteApi(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<ApiConfiguration>> getAllApis() {
        // Delegate to service for fetching all APIs
        return ResponseEntity.ok(apiConfigurationService.getAllApis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiConfiguration> getApiById(@PathVariable Long id) {
        // Delegate to service for fetching a single API by ID
        return ResponseEntity.ok(apiConfigurationService.getApiById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ApiConfiguration>> searchApisByName(@RequestParam String name) {
        // Delegate to service for searching APIs by name
        return ResponseEntity.ok(apiConfigurationService.searchApisByName(name));
    }

    @PostMapping("/{id}/add-collaborator")
    public ResponseEntity<ApiConfiguration> addCollaborator(@PathVariable Long id, @RequestParam String email) {
        try {
            ApiConfiguration updatedApi = apiConfigurationService.addCollaborator(id, email);
            return ResponseEntity.ok(updatedApi);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/update-collaborator")
    public ResponseEntity<ApiConfiguration> updateCollaborator(@PathVariable Long id, @RequestParam String oldEmail, @RequestParam String newEmail) {
        try {
            ApiConfiguration updatedApi = apiConfigurationService.updateCollaborator(id, oldEmail, newEmail);
            return ResponseEntity.ok(updatedApi);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/collaborators")
    public ResponseEntity<List<String>> getCollaborators(@PathVariable Long id) {
        try {
            List<String> collaborators = apiConfigurationService.getCollaborators(id);
            return ResponseEntity.ok(collaborators);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/delete-collaborator")
    public ResponseEntity<ApiConfiguration> deleteCollaborator(@PathVariable Long id, @RequestParam String email) {
        try {
            ApiConfiguration updatedApi = apiConfigurationService.deleteCollaborator(id, email);
            return ResponseEntity.ok(updatedApi);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}