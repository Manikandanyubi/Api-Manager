package com.example.apimanager.controller;

import com.example.apimanager.model.Api;
import com.example.apimanager.model.Endpoint;
import com.example.apimanager.repository.ApiRepository;
import com.example.apimanager.repository.EndpointRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiRepository apiRepository;
    private final EndpointRepository endpointRepository;

    public ApiController(ApiRepository apiRepository, EndpointRepository endpointRepository) {
        this.apiRepository = apiRepository;
        this.endpointRepository = endpointRepository;
    }

    @PostMapping("/create")
    public Api createApi(@RequestBody Api api) {
        return apiRepository.save(api);
    }

    @PostMapping("/{apiId}/add-endpoint")
    public Endpoint addEndpoint(@PathVariable Long apiId, @RequestBody Endpoint endpoint) {
        Api api = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("API not found"));
        endpoint.setApi(api);
        return endpointRepository.save(endpoint);
    }

    @GetMapping("/{apiId}/endpoints")
    public List<Endpoint> getEndpoints(@PathVariable Long apiId) {
        return endpointRepository.findAllByApiId(apiId);  // Correct method name
    }
}
