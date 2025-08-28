package com.example.edge_cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.edge_cache.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Link findByShortUrl(String shortUrl);
}