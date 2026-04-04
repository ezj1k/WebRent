package com.usm.WebRent.controller;

import com.usm.WebRent.entity.Car;
import com.usm.WebRent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


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






}
