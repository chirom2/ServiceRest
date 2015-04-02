package mri.jersey.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import mri.jersey.message.Message;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
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

    // Exemple cr�ation d'un message :
    Message m = new Message();
    m.setContent("Client test");
    
    GenericType<List<Message>> gType = new GenericType<List<Message>>() {
	};
	
    
    m = (Message) service.path("messages").accept(MediaType.APPLICATION_JSON)
        .post(Message.class, m);//Envoie d'un message
    
  //test de la requete http://urlserver:8080/mri.jersey/rest/messages
    System.out.println("Affichage de la liste des messages.");
    List<Message> messages = service.path("messages")
			.accept(MediaType.APPLICATION_JSON).get(gType);
    for(Message messa : messages){
    	System.out.println(messa.toString());
    }
    
  //test de la requete http://urlserver:8080/mri.jersey/rest/after/{id}
    
    System.out.println("Test requete GET /after/{id} ");
    messages = service.path("messages/after/3")//On affiche les messages a partir de l'id 3 comrpis
			.accept(MediaType.APPLICATION_JSON).get(gType);
    for(Message messa : messages){
    	System.out.println(messa.toString());
    }
    
    
    //test de la requete http://urlserver:8080/mri.jersey/rest/messages/{id}
    System.out.println("Test requete GET /messages/{id} ");
    
    System.out.println(service.path("messages/1")
    		 .accept(MediaType.APPLICATION_JSON).get(Message.class));
     
    //test de la requete http://urlserver:8080/mri.jersey/rest/messages/{id}
    service.path("messages/3").delete();
  }
}