package pl.mzuchnik.springdocker.service;

import pl.mzuchnik.springdocker.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUserName(String username);

    void save(User user);

    void delete(User user);

}
