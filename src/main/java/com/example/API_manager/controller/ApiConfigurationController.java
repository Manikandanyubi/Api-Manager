package com.example.API_manager.controller;

import com.example.API_manager.model.ApiConfiguration;
import com.example.API_manager.service.ApiConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
        return ResponseEntity.ok(apiConfigurationService.updateApi(id, api));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteApi(@PathVariable Long id) {
        apiConfigurationService.deleteApi(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<ApiConfiguration>> getAllApis() {
        return ResponseEntity.ok(apiConfigurationService.getAllApis());
    }
}
