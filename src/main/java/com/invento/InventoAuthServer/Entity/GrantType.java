package com.invento.InventoAuthServer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Document(collection = "grant_types")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GrantType {

    @Id
    private ObjectId id;

    private String grantType;

    private Client client;

    public static GrantType from(AuthorizationGrantType authorizationGrantType, Client client){
        GrantType g = new GrantType();
        g.setGrantType(authorizationGrantType.getValue());
        g.setClient(client);
        return g;
    }

    public void setGrantType(String grantType) {
        this.grantType = AuthorizationGrantType.CLIENT_CREDENTIALS.getValue();
    }
}
