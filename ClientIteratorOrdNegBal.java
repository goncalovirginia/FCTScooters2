/**
 * 
 * Goncalo Virginia N-56773 e Afonso Batista N-57796
 *
 */
public class ClientIteratorOrdNegBal {

	private Client[] clients;
	private int counter, nextClient;

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

	public boolean hasNext() {
		return nextClient < counter;
	}

	public Client next() {
		return clients[nextClient++];
	}

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

	private void insertAt(Client client, int pos) {
		for (int i = counter - 1; i >= pos; i--) {
			clients[i + 1] = clients[i];
		}

		clients[pos] = client;
		counter++;
	}

}
