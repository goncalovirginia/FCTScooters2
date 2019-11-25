
public class TrotList {

	private Trot[] trots;
	private static final int DEFAULT_SIZE = 100, ARRAY_GROWTH = 2;
	private int counter;
	
	public TrotList() {
		trots = new Trot[DEFAULT_SIZE];
	}
	
	/**
	 * Searches the list of scooters for a scooter with a certain ID.
	 * @param id - The scooters' ID.
	 * @return Position of the scooter on the scooter list. (-1 if it doesn't exist).
	 */
	public int findTrot(String id) {
		int i = 0, position = -1;
		boolean found = false;
		
		while (i < counter && !found) {
			if (trots[i].getId().equalsIgnoreCase(id)) {
				position = i;
				found = true;
			}
			else {
				i++;
			}
		}
		return position;
	}
	
	public void addTrot(String idTrot, String licensePlate) {
		if(trotListIsFull()) {
			resizeTrotList();
		}
		
		trots[counter++] = new Trot(idTrot, licensePlate);
	}
	
	
	public Trot rentTrot(String idTrot) {
		int positionTrot = findTrot(idTrot);
		
		return trots[positionTrot];
	}
}
