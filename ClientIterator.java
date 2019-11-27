/**
 * 
 * Goncalo Virginia N-56773 e Afonso Batista N-57796
 *
 */
public class ClientIterator {

	private Client[] clients;
	private int counter, nextClient;

	/**
	 * Constructor - Lists all the clients.
	 * 
	 * @param clients - List of clients to iterate.
	 * @param counter - Number of clients present in the client list.
	 */
	public ClientIterator(Client[] clients, int counter) {
		this.clients = clients;
		this.counter = counter;
		nextClient = 0;
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

}
