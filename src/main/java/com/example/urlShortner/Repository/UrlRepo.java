package com.example.urlShortner.Repository;

import com.example.urlShortner.Models.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepo extends JpaRepository<UrlEntity,Long> {
    List<UrlEntity>findByFullUrl(String fullUrl);
}
