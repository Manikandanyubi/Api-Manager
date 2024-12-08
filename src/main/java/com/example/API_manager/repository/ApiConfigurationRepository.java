package com.example.API_manager.repository;
import org.springframework.stereotype.Repository;
import com.example.API_manager.model.ApiConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface ApiConfigurationRepository extends JpaRepository<ApiConfiguration, Long> {
    List<ApiConfiguration> findByNameContaining(String name);
}
