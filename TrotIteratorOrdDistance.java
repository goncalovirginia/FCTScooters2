/**
 * 
 * Goncalo Virginia N-56773 e Afonso Batista N-57796
 *
 */
public class TrotIteratorOrdDistance {

	private Trot[] trots;
	private int counter, nextTrot;
	private double xClient, yClient;

	public TrotIteratorOrdDistance(Trot[] trots, int counter, double yClient, double xClient) {
		this.trots = new Trot[counter];
		this.counter = 0;
		nextTrot = 0;

		this.xClient = xClient;
		this.yClient = yClient;

		for (int i = 0; i < counter; i++) {
			this.insertSort(trots[i]);
		}
	}

	public boolean hasNext() {
		return nextTrot < counter;
	}

	public Trot next() {
		return trots[nextTrot++];
	}

	private void insertSort(Trot trot) {
		int pos = -1, i = 0;

		while (i < counter && pos == -1) {
			if (trots[i].distance(yClient, xClient) > trot.distance(yClient, xClient)) {
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

	private void insertAt(Trot trot, int pos) {
		for (int i = counter - 1; i >= pos; i--) {
			trots[i + 1] = trots[i];
		}

		trots[pos] = trot;
		counter++;
	}

}
