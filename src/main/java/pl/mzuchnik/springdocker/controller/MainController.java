package pl.mzuchnik.springdocker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String redirectToUserAnimals(){
        return "redirect:/animals";
    }
}
