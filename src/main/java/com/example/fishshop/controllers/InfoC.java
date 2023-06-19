package com.example.fishshop.controllers;

import com.example.fishshop.models.ClientM;
import com.example.fishshop.models.ItemM;
import com.example.fishshop.repositories.ClientR;
import com.example.fishshop.repositories.ItemR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/info")
public class InfoC {
    @Autowired
    ItemR itemR;
    @Autowired
    ClientR clientR;
    @GetMapping("/{id}")
    public String getInfoPage(@PathVariable(value = "id") long id, Model model){
        Optional<ItemM> optional = itemR.findById(id);
        List<ItemM> list = new ArrayList<>();
        optional.ifPresent(list::add);
        model.addAttribute("item", list);
        return "info";
    }
    @PostMapping("/{id}")
    public RedirectView saveData(@PathVariable(value = "id") long id,
                                 @RequestParam String name,
                                 @RequestParam String contact){
        ClientM clientM = new ClientM();
        clientM.setItem(id);
        clientM.setName(name);
        clientM.setContact(contact);
        clientM.setActual(true);
        clientR.save(clientM);
        return new RedirectView("/");
    }
}
