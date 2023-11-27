package com.invento.InventoAuthServer.Repository;

import com.invento.InventoAuthServer.Entity.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, ObjectId> {
    Optional<Client> findById(Integer id);

    Optional<Client> findByClientId(String ClientId);
}
