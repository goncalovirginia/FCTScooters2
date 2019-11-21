
public class TrotIterator {

	private Trot[] trots;
	private int counter, nextTrot;
	
	public TrotIterator(Trot[] trots, int counter) {
		this.trots = trots;
		this.counter = counter;
		nextTrot = 0;
	}
	
	public boolean hasNext() {
		return nextTrot < counter;
	}
	
	public Trot next() {
		return trots[nextTrot++];
	}
}
