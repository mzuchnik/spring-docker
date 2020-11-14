package pl.mzuchnik.springdocker.service;

import pl.mzuchnik.springdocker.entity.Animal;
import pl.mzuchnik.springdocker.entity.User;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    List<Animal> findAllByUser(User user);

    void save(Animal animal);

    void delete(Animal animal);

    void deleteById(long id);

    Optional<Animal> findById(long id);
}
