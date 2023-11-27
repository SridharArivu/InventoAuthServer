package com.invento.InventoAuthServer.Service;

import com.invento.InventoAuthServer.Entity.*;
import com.invento.InventoAuthServer.Repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public void save(RegisteredClient registeredClient) {

        Client c = new Client();

        c.setClientId(registeredClient.getClientId());
        c.setSecret(registeredClient.getClientSecret());
        c.setAuthenticationMethods(
                registeredClient.getClientAuthenticationMethods()
                        .stream().map(AuthenticationMethod::from)
                        .collect(Collectors.toList())
        );
        c.setGrantTypes(
                registeredClient.getAuthorizationGrantTypes()
                        .stream().map(g -> GrantType.from(g , c))
                        .collect(Collectors.toList())
        );
        c.setRedirectUrls(
                registeredClient.getRedirectUris()
                        .stream().map(u -> RedirectUrl.from(u , c))
                        .collect(Collectors.toList())
        );
        c.setScopes(
                registeredClient.getScopes()
                        .stream().map(s -> Scope.from(s, c))
                        .collect(Collectors.toList())
        );
        clientRepository.save(c);
    }

    @Override
    public RegisteredClient findById(String id) {
        var client = clientRepository.findById(Integer.parseInt(id));
        return client.map(Client::fromClient)
                .orElseThrow(() -> new RuntimeException("cannot fine client"));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {

        var client = clientRepository.findByClientId(clientId);
        return client.map(Client::fromClient)
                .orElseThrow(() -> new RuntimeException("cannot fine client"));
    }
}
