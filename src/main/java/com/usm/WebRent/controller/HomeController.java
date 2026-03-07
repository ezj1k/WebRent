package com.usm.WebRent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index(@RequestParam(value = "name", defaultValue = "Radu") String name, Model model){
        model.addAttribute("name", name);
        return "index";
    }

//    @GetMapping("/cars")
//    public String test(){
//        return "test_cars_index";
//    }


}
