package com.invento.InventoAuthServer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "redirect_urls")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RedirectUrl {
    @Id
    private ObjectId id;

    private String url;

    private Client client;

    public static RedirectUrl from(String url, Client c) {
        RedirectUrl redirectUrl = new RedirectUrl();

        redirectUrl.setUrl(url);
        redirectUrl.setClient(c);

        return redirectUrl;
    }
}