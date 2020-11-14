package pl.mzuchnik.springdocker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mzuchnik.springdocker.entity.Animal;
import pl.mzuchnik.springdocker.entity.User;
import pl.mzuchnik.springdocker.service.AnimalService;
import pl.mzuchnik.springdocker.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/animals")
public class AnimalController {

    private final UserService userService;
    private final AnimalService animalService;

    @Autowired
    public AnimalController(UserService userService, AnimalService animalService) {
        this.userService = userService;
        this.animalService = animalService;
    }

    @GetMapping
    public String showAnimalsForUser(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user for username: " + username));

        List<Animal> animals = animalService.findAllByUser(user);

        model.addAttribute("newAnimal", new Animal());
        model.addAttribute("user", user);
        model.addAttribute("animals", animals);

        return "animals";
    }

    @PostMapping
    public String addNewAnimal(@ModelAttribute(name = "newAnimal") Animal animal, Principal principal) {
        String username = principal.getName();
        User user = userService.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user for username: " + username));
        animal.setUser(user);
        animalService.save(animal);
        return "redirect:/animals";
    }

    @DeleteMapping("/{id}")
    public String removeAnimalWithId(@PathVariable(name = "id") long id){
        animalService.deleteById(id);
        return "redirect:/animals";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable(name = "id") long id,
                               Model model){
        Animal animal = animalService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find animal for id: " + id));
        model.addAttribute("animal",animal);
        return "edit-animal";
    }

    @PostMapping("/{id}/edit")
    public String processEditAnimal(@ModelAttribute(name = "animal") Animal animal,
                                    Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user for username: " + username));
        animal.setUser(user);

        animalService.save(animal);

        return "redirect:/animals";
    }

}
