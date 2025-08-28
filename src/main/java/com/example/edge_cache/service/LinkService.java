package com.example.edge_cache.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edge_cache.entity.Link;
import com.example.edge_cache.repository.LinkRepository;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    public Link createShortLink(String originalUrl) {
        String shortUrl = generateShortUrl();
        Link link = new Link();
        link.setOriginalUrl(originalUrl);
        link.setShortUrl(shortUrl);
        return linkRepository.save(link);
    }

    public Link getOriginalUrl(String shortUrl) {
        return linkRepository.findByShortUrl(shortUrl);
    }

    private String generateShortUrl() {
        int leftLimit = 48; // '0'
        int rightLimit = 122; // 'z'
        int length = 6;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}