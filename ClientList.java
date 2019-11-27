
public class ClientList {

	private Client[] clients;
	private static final int DEFAULT_SIZE = 100, ARRAY_GROWTH = 2;
	private int counter;

	/* Constructor */
	public ClientList() {
		clients = new Client[DEFAULT_SIZE];
		counter = 0;
	}

	/**
	 * Verifies if the client list is full.
	 * 
	 * @return True if the client list is full.
	 */
	private boolean isFull() {
		return counter == clients.length;
	}

	/**
	 * Resizes the client list to twice the size.
	 */
	private void resize() {
		Client[] bigClients = new Client[ARRAY_GROWTH * clients.length];

		for (int i = 0; i < counter; i++) {
			bigClients[i] = clients[i];
		}

		clients = bigClients;
	}

	/**
	 * Searches the client list for a client with the specified NIF.
	 * 
	 * @param nif - The clients' NIF (Identification number).
	 * @return The position of the client on the client list (-1 if not found).
	 * @pre nif != null
	 */
	public int findClient(String nif) {
		boolean found = false;
		int low = 0, high = counter - 1, position = -1;

		while (low <= high && !found) {
			int mid = (low + high) / 2;
			if (clients[mid].getNif().compareToIgnoreCase(nif) == 0) {
				found = true;
				position = mid;
			} else if (clients[mid].getNif().compareToIgnoreCase(nif) > 0) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return position;
	}

	/**
	 * Searches the client list and verifies if a client with the inserted NIF
	 * exists.
	 * 
	 * @param nif - The clients' NIF (Identification number).
	 * @return True if a client with the inserted NIF exists.
	 * @pre nif != null
	 */
	public boolean clientExists(String nif) {
		return findClient(nif) != -1;
	}

	/**
	 * Searches the client list for a client with the inserted NIF and returns it.
	 * 
	 * @param nif - The clients' NIF (Identification number).
	 * @return The client object with the specified NIF.
	 * @pre nif != null
	 */
	public Client getClient(String nif) {
		return clients[findClient(nif)];
	}

	/**
	 * Adds a new client to the list using the following parameters:
	 * 
	 * @param nif   - The new clients' NIF (Identification number).
	 * @param email - The new clients' Email.
	 * @param phone - The new clients' phone number.
	 * @param name  - The new clients' name (can be more than 1 word).
	 * @pre nif != null && email != null && phone != null && name != null
	 */
	public void addClient(String nif, String email, String phone, String name) {
		if (isFull()) {
			resize();
		}

		insertSort(new Client(nif, email, phone, name));
	}

	/**
	 * Attaches a scooter object to the the client with the specified NIF.
	 * 
	 * @param nif  - The clients' NIF (Identification number).
	 * @param trot - The Scooter object.
	 * @pre nif != null
	 */

	public void rent(String nif, Trot trot) {
		clients[findClient(nif)].rent(trot);
	}

	/**
	 * Removes the clients' connection with the scooter it was using, unloads its'
	 * balance and adds the minutes spent on the last rent.
	 * 
	 * @param nif     - The clients' NIF (Identification number).
	 * @param minutes - Time (minutes) spent on the last rent.
	 * @param amount  - Money (cents) spent on the last rent.
	 * @pre nif != null && minutes > 0 && amount > 0
	 */
	public void release(String nif, int minutes, int amount) {
		clients[findClient(nif)].release(minutes, amount);
	}

	/**
	 * Removes the specified client from the client list.
	 * 
	 * @param nif - The clients' NIF (Identification number).
	 * @pre nif != null
	 */
	public void removeClient(String nif) {
		for (int i = findClient(nif); i < counter - 1; i++) {
			clients[i] = clients[i + 1];
		}
		counter--;
	}

	/**
	 * Loads the specified clients' balance by the specified amount (cents).
	 * 
	 * @param nif    - The clients' NIF (Identification number).
	 * @param amount - Money (cents) spent on the last rent.
	 * @pre nif != null && amount > 0
	 */
	public void loadBalance(String nif, int amount) {
		clients[findClient(nif)].loadBalance(amount);
	}

	/**
	 * Inserts a client to the list, sorting it by its' NIF in lexicographic order.
	 * 
	 * @param client - Client object to insert into the list.
	 */
	private void insertSort(Client client) {
		int pos = -1, i = 0;

		while (i < counter && pos == -1) {
			if (clients[i].nifGreaterThan(client)) {
				pos = i;
			} else {
				i++;
			}
		}
		if (pos == -1) {
			pos = counter;
		}

		insertAt(client, pos);
	}

	/**
	 * Inserts a client into a certain position on the client list.
	 * 
	 * @param client
	 * @param pos
	 */
	private void insertAt(Client client, int pos) {
		for (int i = counter - 1; i >= pos; i--) {
			clients[i + 1] = clients[i];
		}

		clients[pos] = client;
		counter++;
	}

	/**
	 * @return The list of clients.
	 */
	public Client[] getClientList() {
		return clients;
	}

	/**
	 * @return The number of clients' in the list.
	 */
	public int getCounter() {
		return counter;
	}

}
