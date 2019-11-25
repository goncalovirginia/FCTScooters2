
public class TrotList {

	private Trot[] trots;
	private static final int DEFAULT_SIZE = 100, ARRAY_GROWTH = 2;
	private int counter;
	
	public TrotList() {
		trots = new Trot[DEFAULT_SIZE];
	}
	
	private int findTrot(String id) {
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
	public boolean trotExist(String idTrot) {
		return findTrot(idTrot) != -1;
	}
	
	public TrotIterator newTrotIterator() {
		return new TrotIterator(trots, counter);
	}
	
	public Trot getTrot(String idTrot) {
		return trots[findTrot(idTrot)];
	}
	
	public void release(String idTrot, int minutes) {
		trots[findTrot(idTrot)].release(minutes);
	}
	
	private boolean isFull() {
		return counter == trots.length;
	}
	
	private void resize() {
		Trot[] bigTrots = new Trot[ARRAY_GROWTH * trots.length];
		
		for (int i = 0; i < counter; i++) {
			bigTrots[i] = trots[i];
		}
		
		trots = bigTrots;
	}
	
	public void addTrot(String idTrot, String licensePlate) {
		if(isFull()) {
			resize();
		}
		
		trots[counter++] = new Trot(idTrot, licensePlate);
	}
	
	
	public Trot rentTrot(String idTrot) {
		int positionTrot = findTrot(idTrot);
		
		return trots[positionTrot];
	}
	
	public TrotIteratorOrdDistance newTrotIteratorOrdDistance(double xClient, double yClient) {
		return new TrotIteratorOrdDistance(trots, counter, xClient, yClient);
	}
	
}
