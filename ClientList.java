
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
	
	private int findClient(String nif) {
		int position = -1;
		
		for(int i=0; i < counter; i++) {
			if (clients[i].getNif().equalsIgnoreCase(nif)) {
				position = i;
				break;
			}
		}
		
		return position;
	}
	
	public boolean clientExists(String nif) {
		return findClient(nif) != -1;
	}
	
	public Client getClient(String nif) {
		return clients[findClient(nif)];
	}
	
	public void addClient(String nif, String email, String phone, String name) {
		if (isFull()) {
			resize();
		}
		
		insertSort(new Client(nif, email, phone, name));
	}
	
	public void rent(String nif, Trot trot) {
		clients[findClient(nif)].rent(trot);
	}
	
	public void release(String nif, int minutes, int amount) {
		clients[findClient(nif)].release(minutes, amount);
	}
	
	public void removeClient(String nif) {
		for (int i = findClient(nif); i < counter-1; i++) {
			clients[i] = clients[i+1];
		}
		counter--;
	}
	
	public void loadBalance(String nif, int amount) {
		clients[findClient(nif)].loadBalance(amount);
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
	
	public Client[] getClientList() {
		return clients;
	}
	
	public int getCounter() {
		return counter;
	}
	
}
