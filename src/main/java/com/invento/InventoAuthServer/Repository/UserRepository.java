package com.invento.InventoAuthServer.Repository;

import com.invento.InventoAuthServer.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findUserByUsername(String username);
}
