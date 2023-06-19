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
@RequestMapping("/admin")
public class AdminC {
    @Autowired
    ItemR itemR;
    @Autowired
    ClientR clientR;

    @GetMapping
    public String adminPage(){
        return "admin";
    }
    @GetMapping("/addItem")
    public String addPage(){return "add";}
    @PostMapping("/addItem")
    public RedirectView saveItem(@RequestParam String title,
                                 @RequestParam String disc,
                                 @RequestParam String photoUrl){
        ItemM itemM = new ItemM();
        itemM.setTitle(title);
        itemM.setDisc(disc);
        itemM.setUrl(photoUrl);
        itemR.save(itemM);
        return new RedirectView("/admin");
    }
    @GetMapping("/checkClients")
    public String clientsPage(Model model){
        List<ClientM> clientsList = clientR.findClientMSByActual(true);
        model.addAttribute("clientsList", clientsList);
        return "clients";
    }
    @GetMapping("/checkClients/notActual/{id}")
    public RedirectView notActual(@PathVariable(value = "id") long id){
        Optional<ClientM> optional = clientR.findById(id);
        List<ClientM> clientMs = new ArrayList<>();
        optional.ifPresent(clientMs::add);
        ClientM clientM = clientMs.get(0);
        clientM.setActual(false);
        clientR.save(clientM);
        return new RedirectView("/admin/checkClients");
    }
    @GetMapping("/archive")
    public String archivePage(Model model){
        List<ClientM> clientsList = clientR.findClientMSByActual(false);
        model.addAttribute("clientsList", clientsList);
        return "archive";
    }
    @GetMapping("/archive/{id}")
    public RedirectView deleteClient (@PathVariable(value = "id") long id){
        clientR.deleteById(id);
        return new RedirectView("/admin/archive");
    }
    @GetMapping("/allItems")
    public String allIteemPage(Model model){
        List<ItemM> list = itemR.findAll();
        model.addAttribute("allItems", list);
        return "allItems";
    }
    @GetMapping("/allItems/delete/{id}")
    public RedirectView deleteItem(@PathVariable(value = "id") long id){
        itemR.deleteById(id);
        return new RedirectView("/admin/allItems");
    }
    @GetMapping("/allItems/edit/{id}")
    public String editItem(@PathVariable(value = "id") long id,
                           Model model){
        Optional<ItemM> optional = itemR.findById(id);
        List<ItemM> list = new ArrayList<>();
        optional.ifPresent(list::add);
        model.addAttribute("item", list);
        return "edit";
    }
    @PostMapping("/allItems/edit/{id}")
    public RedirectView editItemComplite (@PathVariable(value = "id") long id,
                                          @RequestParam String title,
                                          @RequestParam String disc,
                                          @RequestParam String url){
        Optional<ItemM> optional = itemR.findById(id);
        List<ItemM> list = new ArrayList<>();
        optional.ifPresent(list::add);
        ItemM itemM = list.get(0);
        itemM.setTitle(title);
        itemM.setDisc(disc);
        if(!url.equals("")&&url!=null) itemM.setUrl(url);
        itemR.save(itemM);
        return new RedirectView("/admin/allItems");
    }
}
