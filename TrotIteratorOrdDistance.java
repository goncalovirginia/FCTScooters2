/**
 * 
 * Goncalo Virginia N-56773 e Afonso Batista N-57796
 *
 */
public class TrotIteratorOrdDistance {

	private Trot[] trots;
	private int counter, nextTrot;
	private double xClient, yClient;

	/**
	 * Constructor - Filters scooters by distance in relation to thespecified client coordinates.
	 * 
	 * @param trots - List of scooters to iterate.
	 * @param counter - Number of scooters present in the scooter list.
	 * @param yClient - Client latitude.
	 * @param xClient - Client longitude.
	 */
	public TrotIteratorOrdDistance(Trot[] trots, int counter, double yClient, double xClient) {
		this.trots = new Trot[counter];
		this.counter = 0;
		nextTrot = 0;

		this.xClient = xClient;
		this.yClient = yClient;

		for (int i = 0; i < counter; i++) {
			if (trots[i].getX() != 0 || trots[i].getY() != 0)
				this.insertSort(trots[i]);
		}
	}

	/**
	 * Checks if a scooter exists in the next posiiton on the scooter list.
	 * 
	 * @return True if a scooter exists in the next position.
	 */
	public boolean hasNext() {
		return nextTrot < counter;
	}

	/**
	 * Iterates to the next scooter on the list.
	 * 
	 * @return Next scooter object on the list.
	 */
	public Trot next() {
		return trots[nextTrot++];
	}

	/**
	 * Inserts a scooter to the list, sorting it by its' distance to the client in increasing order.
	 * In the case of identical distances, the scooter is sorted by the original insertion order.
	 * 
	 * @param client - Client object to insert into the list.
	 */
	private void insertSort(Trot trot) {
		int pos = -1, i = 0;

		while (i < counter && pos == -1) {
			if (trot.distance(yClient, xClient) < trots[i].distance(yClient, xClient)) {
				pos = i;
			} else {
				i++;
			}
		}
		if (pos == -1) {
			pos = counter;
		}

		insertAt(trot, pos);
	}

	/**
	 * Inserts a scooter into a certain position on the client list.
	 * 
	 * @param trot - Scooter to insert into the list.
	 * @param pos - Position to insert the scooter at.
	 */
	private void insertAt(Trot trot, int pos) {
		for (int i = counter - 1; i >= pos; i--) {
			trots[i + 1] = trots[i];
		}

		trots[pos] = trot;
		counter++;
	}
	
}
