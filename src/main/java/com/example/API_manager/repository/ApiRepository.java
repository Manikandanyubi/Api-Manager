package com.example.apimanager.repository;

import com.example.apimanager.model.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRepository extends JpaRepository<Api, Long> {
    
    // Custom query to find API by name
    List<Api> findByName(String name);

    // Custom query using JPQL to search API by name or description
    @Query("SELECT a FROM Api a WHERE a.name LIKE %:keyword% OR a.description LIKE %:keyword%")
    List<Api> searchApisByKeyword(String keyword);

    // Custom query using native SQL (if you prefer direct SQL queries)
    @Query(value = "SELECT * FROM api WHERE name LIKE %:keyword% OR description LIKE %:keyword%", nativeQuery = true)
    List<Api> searchApisByKeywordNative(String keyword);
}
