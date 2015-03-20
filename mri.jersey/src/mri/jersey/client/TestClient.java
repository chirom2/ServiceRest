package mri.jersey.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import mri.jersey.message.Message;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestClient {

  public static void main(String[] args) {
    ClientConfig config = new DefaultClientConfig();
    Client client = Client.create(config);

    URI serviceURI = UriBuilder
        .fromUri("http://localhost:8080/mri.jersey/rest").build();
    WebResource service = client.resource(serviceURI);

    // Exemple création d'un message :
    Message m = new Message();
    m.setContent("Client test");

    m = (Message) service.path("messages").accept(MediaType.APPLICATION_JSON)
        .post(Message.class, m);

    // A vous de continuer le code ...

  }
}