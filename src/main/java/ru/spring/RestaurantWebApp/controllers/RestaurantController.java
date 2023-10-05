package ru.spring.RestaurantWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.RestaurantWebApp.models.Person;
import ru.spring.RestaurantWebApp.models.TableRest;
import ru.spring.RestaurantWebApp.services.PeopleService;
import ru.spring.RestaurantWebApp.services.TableService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    private final PeopleService peopleService;
    private final TableService tableService;



    @Autowired
    public RestaurantController(PeopleService peopleService, TableService tableService) {
        this.peopleService = peopleService;
        this.tableService = tableService;

    }

    @GetMapping()
    public String home() {
        return "restaurant/home";
    }

    @GetMapping("/book")
    public String book(Model model) {
        model.addAttribute("time", Arrays.asList("15:00-17:00", "17:00-19:00", "19:00-21:00"));
        model.addAttribute("guests", Arrays.asList(2, 3, 4));
        model.addAttribute("name", "");
        model.addAttribute("phone", "");
        model.addAttribute("person", new Person());
        model.addAttribute("table", new TableRest());
        return "restaurant/book";
    }

    @PostMapping("/select")
    public String selectTable(@ModelAttribute("person") @Valid Person person,
                              BindingResult bindingResult,
                              @ModelAttribute("table") TableRest table,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "restaurant/book";
        }

        String selectedTime = table.getTime();
        int numberOfGuests = table.getCapacity();

        List<TableRest> suitableTables = tableService.getAvailableTables(numberOfGuests, selectedTime);

        if (suitableTables.isEmpty()) {
            model.addAttribute("confirmationMessage", "Извините, подходящих столов не найдено.");
            return "restaurant/select";
        }

        model.addAttribute("suitableTables", suitableTables);

        return "restaurant/select";
    }

    @PostMapping("/confirmation")
    public String confirmBooking(@ModelAttribute("person") @Valid Person person,

                                 @RequestParam("tableId") int tableId,
                                 @ModelAttribute("table") TableRest table,
                                 Model model)

    {
        person.setTableId(tableId);

       peopleService.save(person);
       tableService.updateTableAvailability(tableId, false);
        return "restaurant/confirmation";
    }


    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam("personId") int personId,
                                @RequestParam("tableId") int tableId,
                                @ModelAttribute("table") TableRest table,
                                Model model) {
        System.out.println(tableId);
        tableService.updateTableAvailability(tableId, true);
        peopleService.delete(personId);



        return "restaurant/cancel";
    }
}
