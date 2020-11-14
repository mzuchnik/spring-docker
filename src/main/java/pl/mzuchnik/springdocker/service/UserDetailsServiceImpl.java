package pl.mzuchnik.springdocker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mzuchnik.springdocker.entity.Animal;
import pl.mzuchnik.springdocker.entity.User;

import java.util.Collections;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;
    private AnimalService animalService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, AnimalService animalService) {
        this.userService = userService;
        this.animalService = animalService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("Cannot find user with username: " + username)
        );

        List<Animal> animals = animalService.findAllByUser(user);
        for (Animal animal : animals) {
            System.out.println(animal);
        }

        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        return userDetails;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseWithSomeUser() {
        User admin = new User("admin", "admin");
        User user = new User("user", "user");
        User mateusz = new User("mateusz", "mateusz");
        userService.save(admin);
        userService.save(user);
        userService.save(mateusz);

        Animal squire = new Animal("Wiewiorka", 2, true, admin);
        Animal dog = new Animal("Pies", 3, false, admin);
        Animal cat = new Animal("Pies", 3, false, user);

        animalService.save(squire);
        animalService.save(dog);
        animalService.save(cat);
    }
}
