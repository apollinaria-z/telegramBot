package by.zolotaya.telegrambot.controllers;

import by.zolotaya.telegrambot.dao.CityDAO;
import by.zolotaya.telegrambot.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/cities")
public class CityController {

    private CityDAO cityDAO;

    @Autowired
    public CityController(CityDAO cityDAO){
       this.cityDAO = cityDAO;
    }
    @GetMapping()
    public String getAllClients(Model model){
        model.addAttribute("cities", cityDAO.getAllCities());
        return "cities/getAllCities";
    }

    @GetMapping("/{id}")
    public String getCityById(@PathVariable("id") int id, Model model){
        model.addAttribute("city", cityDAO.getCityById(id));
        return "cities/getCityById";
    }

    @GetMapping("/new")
    public String newCity(@ModelAttribute("city") City city){

        return "cities/newCity";
    }

    @PostMapping()
    public String addCity(@ModelAttribute("city") @Valid City city,
                            BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "cities/newCity";

        cityDAO.addCity(city);
        return "redirect:/cities";
    }

    @GetMapping("/{id}/edit")
    public String editCity(Model model, @PathVariable("id") int id){
        model.addAttribute("city", cityDAO.getCityById(id));
        return "cities/editCity";
    }

    @PatchMapping("/{id}")
    public String updateCity(@ModelAttribute("city") @Valid City city,
                               BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "cities/editCity";
        cityDAO.updateCity(id, city);
        return"redirect:/cities";
    }

    @DeleteMapping("/{id}")
    public String deleteCity(@PathVariable("id") int id){
        cityDAO.deleteCity(id);
        return "redirect:/cities";
    }
}
