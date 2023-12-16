package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.repositories.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
    // Methods to handle CRUD operations
    User save(User user);

    User findByUsername(String username);
    // ... other methods

}
