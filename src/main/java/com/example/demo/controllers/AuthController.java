package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.service.RegService;
import com.example.demo.util.PersonValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegService regService;
    @Autowired
    public AuthController(PersonValidator personValidator, RegService regService) {
        this.personValidator = personValidator;
        this.regService = regService;
    }

    @GetMapping("/reg")
    public String regPage(@ModelAttribute("person") Person person){
        return "auth/def/reg";
    }
    @PostMapping("/reg")
    public String performReg(@ModelAttribute("person") @Valid  Person person , BindingResult bindingResult){
            personValidator.validate(person,bindingResult);
            if(bindingResult.hasErrors()){
                return "redirect:/auth/reg";
            }
            person.setConfines(-1);
            person.setUser_role("USER_DEF");
            regService.reg(person);
            return "redirect:/auth/login";
    }

}
