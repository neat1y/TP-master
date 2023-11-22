package com.example.demo.controllers;

import com.example.demo.models.Operations;
import com.example.demo.models.Person;
import com.example.demo.service.OperationService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/operations")
public class DefaultController {
    private final OperationService operationService;
    private final PersonService personService;
    @Autowired
    public DefaultController(OperationService operationService, PersonService personService) {
        this.operationService = operationService;
        this.personService = personService;
    }
    @GetMapping("/kasir")
    public String cashier(Model model){
        model.addAttribute("operation",operationService.all());
        model.addAttribute("people",personService.all());
         return "auth/kassir/vse";
    }
    @GetMapping()
    public String hello(Model model){
        model.addAttribute("last_oper",operationService.findByLast());
        return "auth/def/trade";
    }
    @PostMapping()
    public String trade(Operations operations,Model model){
        operationService.save(operations);
        model.addAttribute("name",personService.findById(operations.getPerson().getId()));
        return "auth/def/receipt";
    }
}
