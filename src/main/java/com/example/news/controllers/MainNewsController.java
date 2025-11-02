package com.example.news.controllers;

import com.example.news.exceptions.NewNotFoundException;
import com.example.news.services.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String newView(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("newItem", newsService.getNewById(id));
            return "new";
        } catch (NewNotFoundException e) {
            return "new-not-found";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteNewPost(@PathVariable Long id) {
        newsService.deleteNewById(id);
        return "redirect:/news";
    }
}