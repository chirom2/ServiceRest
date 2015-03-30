package mri.jersey.message;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

@Path("/messages")
public class MessageResource {

  @GET
  public List<Message> getMessages() {
    System.out.println("retourne la liste des messages");
    return MessageList.getInstance().getMessages();
  }

  @GET
  @Path("after/{id}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<Message> getMessagesAfter(@PathParam("id")Long id) {
    System.out.println("retourne la liste des messages apres " + id);
    return MessageList.getInstance().getMessagesAfter(id);
  }

  @POST
  public Message create(JAXBElement<Message> message) {
    System.out.println("ajoute le message et retourne le message complet");
    return MessageList.getInstance().createMessage(message.getValue());
  }

  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Message getMessage(@PathParam("id")Long id) {
    System.out.println("retourne le message d'id = " + id);
    return MessageList.getInstance().getMessage(id);
  }

  @DELETE
  @Path("{id}")
  public void removeMessage(@PathParam("id")Long id) {
    System.out.println("efface le message d'id = " + id);
    MessageList.getInstance().delMessage(id);
  }

}
