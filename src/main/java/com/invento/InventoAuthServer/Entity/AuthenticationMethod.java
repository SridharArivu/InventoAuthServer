package com.invento.InventoAuthServer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationMethod {

    private String authentication_method;


    public void setAuthentication_method(String authentication_method) {
        this.authentication_method = authentication_method;
    }

    public static AuthenticationMethod from (ClientAuthenticationMethod authenticationMethod){
        AuthenticationMethod a = new AuthenticationMethod();
        a.setAuthentication_method(authenticationMethod.getValue());
        return a;
    }

}
