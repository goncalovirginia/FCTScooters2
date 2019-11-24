
public class ClientIteratorOrdNif {

	private Client[] clients;
	private int counter, nextClient;
	
	public ClientIteratorOrdNif(Client[] clients, int counter) {
		this.clients = new Client[counter];
		this.counter = 0;
		nextClient = 0;
		
		for (int i = 0; i < counter; i++) {
			this.insertSort(clients[i]);
		}
	}
	
	public boolean hasNext() {
		return nextClient < counter;
	}
	
	public Client next() {
		return clients[nextClient++];
	}
	
	private void insertSort(Client client) {
		int pos = -1, i = 0;
		
		while (i < counter && pos == -1) {
			if (clients[i].nifGreaterThan(client)) {
				pos = i;
			}
			else {
				i++;
			}
		}
		if (pos == -1) {
			pos = counter;
		}
		
		insertAt(client, pos);
	}
	
	private void insertAt(Client client, int pos) {
		for (int i = counter - 1; i >= pos; i--) {
			clients[i+1] = clients[i];
		}
			
		clients[pos] = client;
		counter++;
	}
	
}
