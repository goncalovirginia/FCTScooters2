/**
 * 
 * Goncalo Virginia N-56773 e Afonso Batista N-57796
 *
 */
public class ClientIteratorOrdNegBal {

	private Client[] clients;
	private int counter, nextClient;

	/**
	 * Constructor - Filters clients by negative balance in increasing order.
	 * 
	 * @param clients - List of clients to iterate.
	 * @param counter - Number of clients present in the client list.
	 */
	public ClientIteratorOrdNegBal(Client[] clients, int counter) {
		this.clients = new Client[counter];
		this.counter = 0;
		nextClient = 0;

		for (int i = 0; i < counter; i++) {
			if (clients[i].getBalance() < 0) {
				this.insertSort(clients[i]);
			}
		}
	}

	/**
	 * Checks if a client exists in the next posiiton on the client list.
	 * 
	 * @return True if a client exists in the next position.
	 */
	public boolean hasNext() {
		return nextClient < counter;
	}

	/**
	 * Iterates to the next client on the list.
	 * 
	 * @return Next client object on the list.
	 */
	public Client next() {
		return clients[nextClient++];
	}

	/**
	 * Inserts a client to the list, sorting it by its' balance in increasing order.
	 * 
	 * @param client - Client object to insert into the list.
	 */
	private void insertSort(Client client) {
		int pos = -1, i = 0;

		while (i < counter && pos == -1) {
			if (clients[i].balanceGreaterThan(client)) {
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
	 * @param client - Client object to insert into the list.
	 * @param pos - Position 
	 */
	private void insertAt(Client client, int pos) {
		for (int i = counter - 1; i >= pos; i--) {
			clients[i + 1] = clients[i];
		}

		clients[pos] = client;
		counter++;
	}

}
