/**
 * 
 * Goncalo Virginia N-56773 e Afonso Batista N-57796
 *
 */
public class ClientIterator {

	private Client[] clients;
	private int counter, nextClient;
	
	/**
	 * 
	 * @param clients -
	 * @param counter -
	 */
	public ClientIterator(Client[] clients, int counter) {
		this.clients = clients;
		this.counter = counter;
		nextClient = 0;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasNext() {
		return nextClient < counter;
	}
	
	/**
	 * 
	 * @return
	 */
	public Client next() {
		return clients[nextClient++];
	}
	
}
