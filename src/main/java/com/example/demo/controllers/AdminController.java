package com.example.demo.controllers;

import com.example.demo.models.Operations;
import com.example.demo.models.Person;
import com.example.demo.service.OperationService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonService personService;
    private final OperationService operationService;
    @Autowired
    public AdminController(PersonService personService, OperationService operationService) {
        this.personService = personService;
        this.operationService = operationService;
    }

    @GetMapping()
    public String vse(Model model){
        model.addAttribute("people",personService.all());
        return "auth/admin/logi";
    }
    @GetMapping("/{id}")
    public String index(@PathVariable("id") int id,Model model){
        model.addAttribute("pers",personService.findById(id).get());
        return "auth/admin/index";
    }
    @GetMapping("/{id}/change")
    public String patch(@PathVariable("id") int id,Model model){
        model.addAttribute("person",personService.findById(id).get());
        return "auth/admin/change";
    }
    @PostMapping("/{id}")
    public String index(@PathVariable("id") int id,@ModelAttribute("pers") Person person){
        personService.update(id,person);
        return "redirect:/admin";
    }

    @PostMapping("/newday")
    public String day(@ModelAttribute("operations")Operations operations){
        operationService.save(operations);
        return "auth/admin/newday";
    }
}
