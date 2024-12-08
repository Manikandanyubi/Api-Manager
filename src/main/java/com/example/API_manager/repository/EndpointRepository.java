package com.example.API_manager.repository;

import com.example.API_manager.model.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
    // Custom query to find endpoints by endUri if needed
    List<Endpoint> findByEndUriContaining(String endUri);
}
