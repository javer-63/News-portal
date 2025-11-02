package com.example.news.controllers;

import com.example.news.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news")
public class MainNewsController {
    private final NewsService newsService;
    @GetMapping
    public String main(Model model) {
        model.addAttribute("news", newsService.getAllNews());
        return "main";
    }
}