package com.usm.WebRent.controller;

import org.springframework.boot.Banner;
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

    @GetMapping("/test")
    public String test(){
        return "test";
    }


}
