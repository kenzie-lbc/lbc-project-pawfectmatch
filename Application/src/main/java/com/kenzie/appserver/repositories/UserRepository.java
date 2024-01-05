package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.Pet;
import com.kenzie.appserver.repositories.model.User;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import org.socialsignin.spring.data.dynamodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@EnableScan
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    // Methods to handle CRUD operations
    User save(User user);

//    @Query
    User findByUsername(String username);
    // ... other methods

}
