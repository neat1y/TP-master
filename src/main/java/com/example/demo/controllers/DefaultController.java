package com.example.demo.controllers;

import com.example.demo.models.Currency;
import com.example.demo.models.Operations;
import com.example.demo.models.Person;
import com.example.demo.service.CurrencyService;
import com.example.demo.service.OperationService;
import com.example.demo.service.PersonService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/operations")
public class DefaultController {
    private final OperationService operationService;
    private final PersonService personService;
    private final CurrencyService currencyService;
    @Autowired
    public DefaultController(OperationService operationService, PersonService personService, CurrencyService currencyService) {
        this.operationService = operationService;
        this.personService = personService;
        this.currencyService = currencyService;
    }
    @GetMapping("/kasir")
    public String cashier(Model model){
        model.addAttribute("operation",operationService.all());
        model.addAttribute("people",personService.all());
         return "auth/kassir/vse";
    }
    @GetMapping()
    public String hello(Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Person person =personService.findbyName(userDetails.getUsername()).get();
        Operations operations=operationService.findByLast().orElse(null);
        Currency currency=currencyService.findLast();
        model.addAttribute("last_oper",operations);
        model.addAttribute("value",currency);
        model.addAttribute("person",person);
        return "auth/def/trade";
    }
    @PostMapping()
    public String trade(Operations operations, Model model){
        operationService.save(operations);
        model.addAttribute("operations",operations);
        model.addAttribute("kurs",currencyService.findLast());
        model.addAttribute("person",personService.findById(operations.getPerson().getId()));
        return "auth/def/receipt";
    }
}
