package pl.mzuchnik.springdocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mzuchnik.springdocker.entity.Animal;
import pl.mzuchnik.springdocker.entity.User;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findAllByUser(User user);
}
