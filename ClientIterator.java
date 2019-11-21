
public class ClientIterator {

	private Client[] clients;
	private int counter, nextClient;
	
	public ClientIterator(Client[] clients, int counter) {
		this.clients = clients;
		this.counter = counter;
		nextClient = 0;
	}
	
	public boolean hasNext() {
		return nextClient < counter;
	}
	
	public Client next() {
		return clients[nextClient++];
	}
	
}
