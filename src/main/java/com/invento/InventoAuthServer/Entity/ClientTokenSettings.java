package com.invento.InventoAuthServer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@Document(collection = "token_settings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientTokenSettings {
    @Id
    private ObjectId id;

    private Integer accessTokenTTL;

    private String type;

    public void setType(String type) {
        this.type = "reference";
    }

    public void setAccessTokenTTL(Integer accessTokenTTL) {
        this.accessTokenTTL = 5;
    }

    private Client client;
}
