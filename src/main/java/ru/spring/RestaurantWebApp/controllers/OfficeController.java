package ru.spring.RestaurantWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/office")
public class OfficeController {

    @GetMapping("/admin")
    public String adminpage() {

        return "office/admin";
    }

    @GetMapping("/worker")
    public String workerpage() {
        return "office/worker";
    }
}
