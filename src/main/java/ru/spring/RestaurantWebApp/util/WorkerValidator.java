package ru.spring.RestaurantWebApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.spring.RestaurantWebApp.models.WorkerRest;
import ru.spring.RestaurantWebApp.services.WorkerDetailsService;


@Component
public class WorkerValidator implements Validator {

    private final WorkerDetailsService workerDetailsService;

    @Autowired
    public WorkerValidator(WorkerDetailsService workerDetailsService) {
        this.workerDetailsService = workerDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return WorkerRest.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        WorkerRest workerRest = (WorkerRest) o;

        try {
            workerDetailsService.loadUserByUsername(workerRest.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return; // все ок, пользователь не найден
        }

        errors.rejectValue("username", "", "Человек с таким именем пользователя уже существует");
    }
}
