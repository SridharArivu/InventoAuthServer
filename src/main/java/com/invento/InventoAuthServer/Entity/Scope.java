package com.invento.InventoAuthServer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scopes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Scope {
    @Id
    private ObjectId id;

    private String scope;

    private Client client;

    public static Scope from(String scope, Client client){
        Scope s = new Scope();
        s.setScope(scope);
        s.setClient(client);
        return s;
    }
}
