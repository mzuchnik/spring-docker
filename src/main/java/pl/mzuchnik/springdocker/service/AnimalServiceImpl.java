package pl.mzuchnik.springdocker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mzuchnik.springdocker.entity.Animal;
import pl.mzuchnik.springdocker.entity.User;
import pl.mzuchnik.springdocker.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public List<Animal> findAllByUser(User user) {
        return animalRepository.findAllByUser(user);
    }

    @Override
    public void save(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public void delete(Animal animal) {
        animalRepository.delete(animal);
    }

    @Override
    public void deleteById(long id) {
        System.out.println("Usuwam animal id: " + id);
        animalRepository.deleteById(id);
    }

    @Override
    public Optional<Animal> findById(long id) {
        return animalRepository.findById(id);
    }
}
