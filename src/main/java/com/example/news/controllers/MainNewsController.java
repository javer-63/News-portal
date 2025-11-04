package com.example.news.controllers;

import com.example.news.dto.NewDto;
import com.example.news.exceptions.NewNotFoundException;
import com.example.news.mappers.NewMapper;
import com.example.news.models.New;
import com.example.news.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news")
public class MainNewsController {

    private final NewsService newsService;

    private final NewMapper newMapper;

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

    @GetMapping("/create")
    public String showCreateForm() {
        return "create-new";
    }

    @PostMapping("/create")
    public String createNew(@ModelAttribute NewDto newDto) {
        newsService.createNew(newDto);
        return "redirect:/news";
    }

    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("newDto", newMapper.newToDto(newsService.getNewById(id)));
            model.addAttribute("id", id);
            return "update-new";
        } catch (NewNotFoundException e) {
            return "redirect:/news";
        }
    }

    @PostMapping("/{id}/update")
    public String updateNew(@PathVariable Long id, @ModelAttribute NewDto newDto) {
        newsService.updateNew(id, newDto);
        return "redirect:/news";
    }

    @PostMapping("/{id}/delete")
    public String deleteNewPost(@PathVariable Long id) {
        newsService.deleteNewById(id);
        return "redirect:/news";
    }
}