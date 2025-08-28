package com.example.edge_cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.edge_cache.entity.Link;
import com.example.edge_cache.service.LinkService;

@Controller
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/shorten")
    public String shorten(@RequestParam String url, Model model) {
        Link link = linkService.createShortLink(url);
        model.addAttribute("shortUrl", link.getShortUrl());
        return "index";
    }

    @GetMapping("/{shortUrl}")
    public String redirect(@PathVariable String shortUrl) {
        Link link = linkService.getOriginalUrl(shortUrl);
        if (link != null) {
            return "redirect:" + link.getOriginalUrl();
        }
        return "index";
    }
}