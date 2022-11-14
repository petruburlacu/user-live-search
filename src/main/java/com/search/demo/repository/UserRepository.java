package com.search.demo.repository;

import com.search.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Integer> {

    /**
     * Search for user(s) based on the provided string input
     * Follows specific pattern and uses lower case
     * @param login string user input
     * @return list of matched users
     */
    @Query(value = "{ 'login' :  { $regex: /?0/, $options: 'i'}}")
    List<User> findByLogin (String login);
}
