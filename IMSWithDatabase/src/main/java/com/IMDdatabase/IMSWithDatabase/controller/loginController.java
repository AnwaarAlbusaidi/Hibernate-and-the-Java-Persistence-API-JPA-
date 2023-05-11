package com.IMDdatabase.IMSWithDatabase.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/login")
public class loginController {

    @PostMapping
    public void login(){
    }
}
