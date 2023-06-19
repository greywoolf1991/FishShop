package com.example.fishshop.controllers;

import com.example.fishshop.models.ItemM;
import com.example.fishshop.repositories.ItemR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainC {
    @Autowired
    ItemR itemR;
    @GetMapping
    public String getMainPage(Model model){
        List<ItemM> list = itemR.findAll();
        model.addAttribute("items", list);
        return "index";
    }
}
