package ru.spring.RestaurantWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spring.RestaurantWebApp.models.WorkerRest;
import ru.spring.RestaurantWebApp.services.RegistrationService;
import ru.spring.RestaurantWebApp.util.WorkerValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final WorkerValidator workerValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, WorkerValidator workerValidator) {
        this.registrationService = registrationService;
        this.workerValidator = workerValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("workerRest") WorkerRest workerRest) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("workerRest") @Valid WorkerRest workerRest,
                                      BindingResult bindingResult) {
        workerValidator.validate(workerRest, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(workerRest);

        return "redirect:/auth/login";
    }
}
