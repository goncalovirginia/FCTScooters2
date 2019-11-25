
public class ClientList {

	private Client[] clients;
	private static final int DEFAULT_SIZE = 100, ARRAY_GROWTH = 2;
	private int counter;
	
	public ClientList() {
		clients = new Client[DEFAULT_SIZE];
		counter = 0;
	}
	
	private boolean isFull() {
		return counter == clients.length;
	}
	
	private void resize() {
		Client[] bigClients = new Client[ARRAY_GROWTH * clients.length];
		
		for (int i = 0; i < counter; i++) {
			bigClients[i] = clients[i];
		}
		
		clients = bigClients;
	}
	
	public int findClient(String nif) {
		int i = 0, position = -1;
		boolean found = false;
		
		while (i < counter && !found) {
			if (clients[i].getNif().equalsIgnoreCase(nif)) {
				position = i;
				found = true;
			}
			else {
				i++;
			}
		}
		return position;
	}
	
	public void addClient(String nif, String email, String phone, String name) {
		if (isFull()) {
			resize();
		}
		
		clients[counter++] = new Client(nif, email, phone, name);
	}
	
}