/**
 * 
 * Goncalo Virginia N-56773 e Afonso Batista N-57796
 *
 */
public class TrotIterator {

	private Trot[] trots;
	private int counter, nextTrot;

	/**
	 * Constructor - Lists all the scooters.
	 * 
	 * @param trots - List of scooters to iterate.
	 * @param counter - Number of scooters present in the scooter list.
	 */
	public TrotIterator(Trot[] trots, int counter) {
		this.trots = trots;
		this.counter = counter;
		nextTrot = 0;
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

}
