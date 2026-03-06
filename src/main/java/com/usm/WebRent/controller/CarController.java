package com.usm.WebRent.controller;

import com.usm.WebRent.entity.Car;
import com.usm.WebRent.service.CarService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {


    private final CarService carService;

    @GetMapping
    public String viewCarsPage(Model model){
        model.addAttribute("listCars", carService.findAll());
        return "test_cars_index";
    }

    @GetMapping("/new")
    public String showNewCarsForm(Model model){
        Car car = new Car();
        model.addAttribute("car", car);
        return "test_car_form";
    }

    @PostMapping    ("/save")
    public  String saveCar(@ModelAttribute("car") Car car){
        carService.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/edit/{id}")
    public  String showEditForm(@PathVariable Long id, Model model){
        Car car = carService.findById(id);
        model.addAttribute("car", car);
        return "test_car_form";
    }

    @GetMapping("/delete/{id}")
    public String eleteCar(@PathVariable Long id){
        carService.deleteById(id);
        return "redirect:/cars";
    }




}
