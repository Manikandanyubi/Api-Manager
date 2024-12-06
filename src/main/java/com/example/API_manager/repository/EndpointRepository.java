package com.example.apimanager.repository;

import com.example.apimanager.model.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
    List<Endpoint> findAllByApiId(Long apiId); 
}
