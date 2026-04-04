package com.usm.WebRent.controller;

import com.usm.WebRent.entity.*;
import com.usm.WebRent.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UsersService usersService;
    private final CarService carService;
    private final ReviewService reviewService;
    private final LocationService locationService;
    private final RentalService rentalService;


    // Rulează automat înainte de orice metodă — adaugă userul logat în model
    @ModelAttribute
    public void addLoggedUser(Model model, Authentication authentication) {
        if (authentication != null) {
            Users user = usersService.findByEmail(authentication.getName());
            model.addAttribute("loggedUser", user);
        }
    }

    @GetMapping({"", "/"})
    public String adminRoot() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "admin/dashboard";
    }

//  ==========  USERS CRUD  =================

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", usersService.findAll());
        return "admin/users";
    }

    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new Users());
        return "admin/user-form";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", usersService.findById(id));
        return "admin/user-form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute Users user) {
        usersService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        usersService.deleteById(id);
        return "redirect:/admin/users";
    }

    //  ==========  REVIEW CRUD  =================

    @GetMapping("/reviews")
    public String listReviews(Model model){
        model.addAttribute("reviews", reviewService.findAll());
        return "admin/reviews";
    }

    @GetMapping("/reviews/new")
    public String newReviewForm(Model model) {
        model.addAttribute("review", new Review());
        return "admin/review-form";
    }

    @GetMapping("/reviews/edit/{id}")
    public String editReview(@PathVariable Long id, Model model) {
        model.addAttribute("car", reviewService.findById(id));
        return "admin/review-form";
    }

    @GetMapping("/reviews/delete/{id}")
    public String deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteById(id);
        return "redirect:/admin/reviews";
    }

    //  ==========  CAR CRUD  =================

    @GetMapping("/cars")
    public String listCars(Model model){
        model.addAttribute("cars", carService.findAll());
        return "admin/cars";
    }

    @PostMapping("/cars/save")
    public String saveCar(@ModelAttribute("car") Car car,
                          @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        carService.save(car, imageFile);
        return "redirect:/admin/cars";
    }

    @GetMapping("/cars/new")
    public String newCarForm(Model model) {
        model.addAttribute("car", new Car());
        return "admin/car-form";
    }

    @GetMapping("/cars/edit/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.findById(id));
        return "admin/car-form";
    }

    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable("id") Long id) {
        carService.deleteById(id);
        return "redirect:/admin/cars";
    }

    //  ==========  LOCATION CRUD  =================

    @GetMapping("/locations")
    public String listLocations(Model model){
        model.addAttribute("locations", locationService.findAll());
        return "admin/locations";
    }

    @PostMapping("/locations/save")
    public String saveLocation(@ModelAttribute Location location) {
        locationService.save(location);
        return "redirect:/admin/locations";
    }

    @GetMapping("/locations/new")
    public String newLocationForm(Model model) {
        model.addAttribute("location", new Location());
        return "admin/location-form";
    }

    @GetMapping("/locations/edit/{id}")
    public String editLocation(@PathVariable Long id, Model model) {
        model.addAttribute("location", locationService.findById(id));
        return "admin/location-form";
    }

    @GetMapping("/locations/delete/{id}")
    public String deleteLocation(@PathVariable("id") Long id) {
        locationService.deleteById(id);
        return "redirect:/admin/locations";
    }

    //  ==========  RENTAL CRUD  =================

    @GetMapping("/rentals")
    public String listRentals(Model model){
        model.addAttribute("rentals", rentalService.findAll());
        return "admin/rentals";
    }

    @PostMapping("/rentals/save")
    public String saveRental(@ModelAttribute Rental rental) {
        rentalService.save(rental);
        return "redirect:/admin/rentals";
    }

    @GetMapping("/rentals/new")
    public String newRentalForm(Model model) {
        model.addAttribute("rental", new Rental());

        model.addAttribute("usersList", usersService.findAll());
        model.addAttribute("carsList", carService.findAll());
        model.addAttribute("locationsList", locationService.findAll());
        return "admin/rental-form";
    }

    @GetMapping("/rentals/edit/{id}")
    public String editRental(@PathVariable Long id, Model model) {
        model.addAttribute("rental", rentalService.findById(id));

        model.addAttribute("usersList", usersService.findAll());
        model.addAttribute("carsList", carService.findAll());
        model.addAttribute("locationsList", locationService.findAll());
        return "admin/rental-form";
    }

    @GetMapping("/rentals/delete/{id}")
    public String deleteRental(@PathVariable("id") Long id) {
        rentalService.deleteById(id);
        return "redirect:/admin/rentals";
    }
}