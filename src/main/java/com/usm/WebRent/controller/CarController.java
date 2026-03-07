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

    @GetMapping("/new")
    public String showNewCarsForm(Model model){
        Car car = new Car();
        model.addAttribute("car", car);
        return "test_car_form";
    }

    @PostMapping("/save")
    public String saveCar(@ModelAttribute("car") Car car,
                          @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadDir = "src/main/resources/static/media/cars/";

            Path dirPath = Paths.get(uploadDir);
            Files.createDirectories(dirPath); // creează automat dacă nu există, fără warning

            String originalFilename = imageFile.getOriginalFilename();
            String extension = (originalFilename != null && originalFilename.contains("."))
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : ".jpg"; // fallback dacă numele e null

            String fileName = UUID.randomUUID() + extension; // fără toString()

            Files.write(dirPath.resolve(fileName), imageFile.getBytes());

            car.setImageUrl("/media/cars/" + fileName);
        }

        if (imageFile == null || imageFile.isEmpty()) {
            if (car.getId() != null) {
                Car existing = carService.findById(car.getId());
                car.setImageUrl(existing.getImageUrl());
            }
        }

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
