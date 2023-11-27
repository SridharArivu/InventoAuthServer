package com.invento.InventoAuthServer.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Document(collection = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {

    @Id
    private ObjectId id;

    private String clientId;

    private String secret;

    public List<AuthenticationMethod> authenticationMethods;

    private List<GrantType> grantTypes;

    private List<RedirectUrl> redirectUrls;

    private List<Scope> scopes;

    private ClientTokenSettings clientTokenSettings;






    public static RegisteredClient fromClient(Client client){
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .clientAuthenticationMethods(clientAuthentizationMetjods((AuthenticationMethod) client.getAuthenticationMethods()))
                .authorizationGrantTypes(authorizationGrantTypes(client.getGrantTypes()))
                .scopes(scopes(client.getScopes()))
                .redirectUris(redirectUris(client.getRedirectUrls()))
                .build();

    }

    private static Consumer<Set<AuthorizationGrantType>> authorizationGrantTypes(List<GrantType> grantTypes){

        Set<AuthorizationGrantType> authorizationGrantTypes = new HashSet<>();
        authorizationGrantTypes.add(AuthorizationGrantType.AUTHORIZATION_CODE);
        authorizationGrantTypes.add(AuthorizationGrantType.CLIENT_CREDENTIALS);
        authorizationGrantTypes.add(AuthorizationGrantType.REFRESH_TOKEN);
        return s -> {
            for(AuthorizationGrantType g : authorizationGrantTypes){
                s.add(new AuthorizationGrantType(g.getValue()));
            }
        };
    }

    private static Consumer<Set<ClientAuthenticationMethod>> clientAuthentizationMetjods(AuthenticationMethod authenticationMethods){
        Set<ClientAuthenticationMethod> authMethod = new HashSet<>();
        authMethod.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
        authMethod.add(ClientAuthenticationMethod.CLIENT_SECRET_POST);
        authMethod.add(ClientAuthenticationMethod.NONE);
        return a -> {
            a.addAll(authMethod);
        };
    }


    private static Consumer<Set<String>> scopes(List<Scope> scopes){

        Set<String> sco = new HashSet<>();
        sco.add(OidcScopes.OPENID);
        return s -> {
            s.addAll(sco);
        };
    }

    private static Consumer<Set<String>> redirectUris(List<RedirectUrl> urls){
        Set<String> url = new HashSet<>();
        url.add("http://127.0.0.1:8080/login/oauth2/code/oidc-client");
        return r -> {
            r.addAll(url);
        };
    }

}
