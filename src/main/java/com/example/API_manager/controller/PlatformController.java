package com.example.API_manager.controller;

import com.example.API_manager.model.ApiConfiguration;
import com.example.API_manager.service.ApiConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platform")
public class PlatformController {

    private final ApiConfigurationService service;

    public PlatformController(ApiConfigurationService service) {
        this.service = service;
    }

    @GetMapping("/apis")
    public ResponseEntity<List<ApiConfiguration>> getApis() {
        return ResponseEntity.ok(service.getAllApis());
    }
}
