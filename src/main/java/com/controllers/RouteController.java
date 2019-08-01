package com.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.models.factory.TileBoardFactory;

@Controller
@RequestMapping("/")
public class RouteController {
    @Autowired
    private TileBoardFactory tileBoardFactory;

    @GetMapping("/servehtml")
    public String serveHtml(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "example";
    }
    
    @GetMapping("/serveboard")
    public String serveBoard(Model model) {
        model.addAttribute("board", tileBoardFactory.generateBasicBoard().toString());
        model.addAttribute("boardSize", 20);
        return "board";
    }
}
