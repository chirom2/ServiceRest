package mri.jersey.message;

import java.util.List;

import javax.xml.bind.JAXBElement;

/* annotation */
public class MessageResource {

  /* annotations */
  public List<Message> getMessages() {
    System.out.println("retourne la liste des messages");
    return null;
  }

  /* annotations */
  public List<Message> getMessagesAfter(/* annotation */Long id) {
    System.out.println("retourne la liste des messages après " + id);
    // TODO : A COMPLETER
    return null;
  }

  /* annotations */
  public Message create(JAXBElement<Message> message) {
    System.out.println("ajoute le message et retourne le message complet");
    // TODO : A COMPLETER
    return null;
  }

  /* annotations */
  public Message getMessage(/* annotation */Long id) {
    System.out.println("retourne le message d'id = " + id);
    // TODO : A COMPLETER
    return null;
  }

  /* annotations */
  public void removeMessage(/* annotation */Long id) {
    System.out.println("efface le message d'id = " + id);
    // TODO : A COMPLETER
  }

}
