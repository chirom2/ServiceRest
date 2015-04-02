package mri.jersey.chat;

import java.net.URI;
import java.util.Scanner;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import mri.jersey.message.Message;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ClientChat {

	// urlServeur = "http://localhost:8080/mri.jersey/rest";

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Lit au clavier. La saisie de l'utilisateur. 
	 * @return String
	 */
	public static String lireClavier() {
		String s = scanner.nextLine();
		return s;
	}

	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		URI serviceURI = UriBuilder.fromUri(
				"http://localhost:8080/mri.jersey/rest").build();
		WebResource service = client.resource(serviceURI);

		System.out.println("Client Chat - Welcome");
		Afficheur afficheur = new Afficheur();
		afficheur.start();

		String message;
		Message messageToPost = new Message();
		System.out.println("Saisir un message: ");
		while (true) {
			message = lireClavier();
			messageToPost.setContent(message);
			//ClientResponse response = service.path.... si on l'on souhaite connaitre le code de retour
			service.path("messages")
					.accept(MediaType.APPLICATION_XML)
					.post(ClientResponse.class, messageToPost);// Envoie du
																// message
		}

	}

}
