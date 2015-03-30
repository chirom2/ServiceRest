package mri.jersey.chat;

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

public class Afficheur extends Thread {
	protected Afficheur() {
		super();
	}

	@Override
	public void run() {
		long idLastMessage = 0;

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		URI serviceURI = UriBuilder.fromUri(
				"http://localhost:8080/mri.jersey/rest").build();
		WebResource service = client.resource(serviceURI);

		GenericType<List<Message>> gType = new GenericType<List<Message>>() {
		};
		List<Message> messages = service.path("messages")
				.accept(MediaType.APPLICATION_JSON).get(gType);
		// Affiche l'ensemble des messages, lors du lancement de l'application
		for (Message m : messages) {
			System.out.println(m.toString());
			idLastMessage = m.getId();
		}

		while (true) {
			try {
				Thread.sleep(1000);// On raffraichit toutes les secondes
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			messages.clear();
			messages = service.path("messages/after/" + idLastMessage)
					.accept(MediaType.APPLICATION_JSON).get(gType);
			if (!messages.isEmpty()) {
				for (Message m : messages) {
					if (m.getId() > idLastMessage) {// on n'affiche que les
													// derniers messages.
						System.out.println(m.toString());
						idLastMessage = m.getId();
					}
				}
			}

		}
	}

}