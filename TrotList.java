
public class TrotList {

	private Trot[] trots;
	private static final int DEFAULT_SIZE = 100, ARRAY_GROWTH = 2;
	private int counter;

	/* Constructor */
	public TrotList() {
		trots = new Trot[DEFAULT_SIZE];
		counter = 0;
	}

	/**
	 * Searches the scooter list for a scooter with the specified ID.
	 * 
	 * @param id - The scooters' ID.
	 * @return The position of the scooter on the scooter list (-1 if not found).
	 * @pre id != null
	 */
	private int findTrot(String id) {
		int i = 0, position = -1;
		boolean found = false;
		while (i < counter && !found) {
			if (trots[i].getId().equalsIgnoreCase(id)) {
				position = i;
				found = true;
			}
			i++;
		}

		return position;
	}

	/**
	 * Searches the scooter list and verifies if a scooter with the inserted ID
	 * exists.
	 * 
	 * @param id - The scooters' ID.
	 * @return True if a scooter with the inserted ID exists.
	 * @pre id != null
	 */
	public boolean trotExists(String idTrot) {
		return findTrot(idTrot) != -1;
	}

	/**
	 * Searches the scooter list for a scooter with the inserted ID and returns it.
	 * 
	 * @param id - The scooters' ID.
	 * @return The scooter object with the specified ID.
	 * @pre id != null
	 */
	public Trot getTrot(String idTrot) {
		return trots[findTrot(idTrot)];
	}

	/**
	 * Removes the scooters' connection with the client that was using it 
	 * and adds the minutes spent on the last rent.
	 * 
	 * @param id - The scooters' ID.
	 * @param minutes - Time (minutes) spent on the last rent.
	 * @pre id != null && minutes > 0
	 */
	public void release(String idTrot, int minutes) {
		trots[findTrot(idTrot)].release(minutes);
	}

	/**
	 * Verifies if the scooter list is full.
	 * 
	 * @return True if the scooter list is full.
	 */
	private boolean isFull() {
		return counter == trots.length;
	}

	/**
	 * Resizes the scooter list to twice the size.
	 */
	private void resize() {
		Trot[] bigTrots = new Trot[ARRAY_GROWTH * trots.length];

		for (int i = 0; i < counter; i++) {
			bigTrots[i] = trots[i];
		}

		trots = bigTrots;
	}

	/**
	 * Adds a new scooter to the list using the following parameters:
	 * 
	 * @param idTrot - The new scooters' ID.
	 * @param licensePlate - The new scooters' license plate.
	 * @pre idTrot != null && licensePlate != null
	 */
	public void addTrot(String idTrot, String licensePlate) {
		if (isFull()) {
			resize();
		}

		trots[counter++] = new Trot(idTrot, licensePlate);
	}

	/**
	 * Attaches a scooter object to the the client with the specified NIF.
	 * 
	 * @param idTrot - The scooters' ID.
	 * @param client - The client object.
	 * @pre idTrot != null
	 */
	public void rent(String idTrot, Client client) {
		trots[findTrot(idTrot)].rent(client);
	}

	/**
	 * @return The list of scooters.
	 */
	public Trot[] getTrotList() {
		return trots;
	}
	
	/**
	 * @return The number of scooters' in the list.
	 */
	public int getCounter() {
		return counter;
	}

}
