package ru.spring.RestaurantWebApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.RestaurantWebApp.models.TableRest;
import ru.spring.RestaurantWebApp.services.TableService;

import javax.validation.Valid;


@Controller
@RequestMapping("/table")
public class TableController {
    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("table", tableService.findAll());
        return "table/index";
    }
    @GetMapping("index_worker")
    public String index_worker(Model model) {
        model.addAttribute("table", tableService.findAll());
        return "table/index_worker";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("table", tableService.findOne(id));
        return "table/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("table") TableRest table) {
        return "table/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("table") @Valid TableRest table,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "table/new";

        tableService.save(table);
        return "redirect:/table";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("table", tableService.findOne(id));
        return "table/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("table") @Valid TableRest table, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "table/edit";

        tableService.update(id, table);
        return "redirect:/table";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        tableService.delete(id);
        return "redirect:/table";
    }
}
