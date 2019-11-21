
/**
 * @author Goncalo Virginia N-56773
 * Interacts and receives inputs from the Main class.
 * Manages the scooter renting system and all the interactions concerning the Client and Trot (Scooter) classes.
 */

public class Manager {

	/* Constants */
	private static final int FEE = 100, DEFAULT_SIZE = 50;
	
	/* Instance variables */
	private Client[] clients;
	private Trot[] trots;
	private int totalRents, totalSpent, totalMinutesLate, minutesLate, tripMinutes, tripCost, clientCounter, trotCounter;
	private boolean usedPromotion;

	/* Constructor */
	public Manager() {
		
		clients = new Client[DEFAULT_SIZE];
		trots = new Trot[DEFAULT_SIZE];
		totalRents = 0;
		totalSpent = 0;
		totalMinutesLate = 0;
		usedPromotion = false;
		clientCounter = 0;
		trotCounter = 0;
		
	}
	
	/**
	 * Searches the list of clients for a client with a certain NIF.
	 * @param nif - The clients' NIF (Identification number).
	 * @return Position of the client on the client list.
	 */
	public int findClient(String nif) {
		int position = -1;
		boolean found = false;
		
		for (int i = 0; i < clientCounter && !found; i++) {
			if (clients[i].getNif().equalsIgnoreCase(nif)) {
				position = i;
				found = true;
			}
		}
		return position;
	}
	
	/**
	 * Searches the list of scooters for a scooter with a certain ID.
	 * @param id - The scooters' ID.
	 * @return Position of the scooter on the scooter list. (-1 if it doesn't exist).
	 */
	public int findTrot(String id) {
		int position = -1;
		boolean found = false;
		
		for (int i = 0; i < trotCounter && !found; i++) {
			if (trots[i].getId().equalsIgnoreCase(id)) {
				position = i;
				found = true;
			}
		}
		return position;
	}
	
	/**
	 * Creates a new client using the following parameters:
	 * @param nif - The new clients' NIF (Identification number).
	 * @param email - The new clients' Email.
	 * @param phone - The new clients' phone number.
	 * @param name - The new clients' name (can be more than 1 word).
	 * @pre nif != null && email != null && phone != null && name != null
	 */
	public void addClient(String nif, String email, String phone, String name) {
		clients[clientCounter++] = new Client(nif, email, phone, name);
	}

	/**
	 * Removes the client.
	 * @param position - Position of the client on the list of clients.
	 */
	public void removeClient(int position) {
		for (int i = position; i < clientCounter-1; i++) {
			clients[i] = clients[i+1];
		}
		clientCounter--;
	}

	/**
	 * Creates a new Scooter using the following parameters:
	 * @param idTrot - The new scooters' ID.
	 * @param licensePlate - The new scooters' license plate.
	 * @pre idTrot != null && licensePlate != null
	 */
	public void addTrot(String idTrot, String licensePlate) {
		trots[trotCounter++] = new Trot(idTrot, licensePlate);
	}

	/**
	 * Loads the clients' balance with a certain amount.
	 * @param amount - Money (cents) to add to the clients' balance.
	 * @param position - Position of the client on the list of clients.
	 * @pre amount > 0
	 */
	public void loadBalance(int position, int amount) {
		clients[position].loadBalance(amount);
	}

	/**
	 * Rents a scooter (using the inserted client).
	 * @param nif - The clients' Identification number.
	 * @param idTrot - The scooters' ID.
	 * @pre nif != null && idTrot != null
	 */
	public void rentTrot(int positionClient, int positionTrot) {
		clients[positionClient].rent(trots[positionTrot]);
		trots[positionTrot].rent(clients[positionClient]);
	}
	
	/**
	 * Releases the scooter and unloads the clients' balance depending on the amount of minutes.
	 * @param minutes - Time (minutes) the client spent using the Scooter.
	 * @pre minutes > 0
	 */
	public void releaseTrot(int positionTrot, int minutes) {
		int times = 0;
		int positionClient = findClient(trots[positionTrot].getClient().getNif());
		
		clients[positionClient].release(minutes);
		trots[positionTrot].release(minutes);
		
		minutesLate = (minutes - 60);
		totalRents++;
		tripMinutes = minutes;
		usedPromotion = false;
		
		if (minutesLate > 0) {
			if (minutesLate % 30 == 0) {
				times = minutesLate/30;
			}
			else {
				times = (minutesLate/30) + 1;
			}
		}
		
		tripCost = FEE * (times + 1);
		clients[positionClient].unloadBalance(tripCost);
		totalSpent += tripCost;
		totalMinutesLate += minutesLate;
	}
	
	/**
	 * Resets the systems' information to how it was before the last rent.
	 */
	public void applyPromotion(int position) {
		usedPromotion = true;
		clients[position].loadBalance(tripCost);
		clients[position].promotion(tripMinutes, tripCost);
		trots[position].promotion(tripMinutes);
		totalSpent -= tripCost;
		totalRents--;
		
		if (minutesLate > 0) {
			totalMinutesLate -= minutesLate;
		}
	}
	
	public String getClientNif(int position) {
		return clients[position].getNif();
	}
	
	public String getClientName(int position) {
		return clients[position].getName();
	}
	
	public String getClientEmail(int position) {
		return clients[position].getEmail();
	}
	
	public String getClientPhone(int position) {
		return clients[position].getPhone();
	}
	
	public int getClientBalance(int position) {
		return clients[position].getBalance();
	}
	
	public int getClientTotalMinutes(int position) {
		return clients[position].getTotalMinutes();
	}
	
	public int getClientRents(int position) {
		return clients[position].getRents();
	}
	
	public int getClientMaxMinutes(int position) {
		return clients[position].getMaxMinutes();
	}
	
	public int getClientAvgMinutes(int position) {
		return clients[position].getAvgMinutes();
	}
	
	public int getClientTotalSpent(int position) {
		return clients[position].getTotalSpent();
	}
	
	public boolean clientHasTrot(int position) {
		return (clients[position].getTrot() != null);
	}
	
	public boolean trotHasClient(int position) {
		return (trots[position].getClient() != null);
	}
	
	public String getClientIdTrot(int position) {
		String idTrot = "";
		
		if (clients[position].getTrot() != null) {
			idTrot = clients[position].getTrot().getId();
		}
		
		return idTrot;
	}
	
	public String getClientLicensePlate(int position) {
		return clients[position].getTrot().getLicensePlate();
	}
	
	public String getTrotNif(int position) {
		String nif = "";
		
		if (trots[position].getClient() != null) {
			nif = trots[position].getClient().getNif();
		}
		
		return nif;
	}
	
	public String getTrotId(int position) {
		return trots[position].getId();
	}
	
	public String getTrotLicensePlate(int position) {
		return trots[position].getLicensePlate();
	}
	
	public String getTrotStatus(int position) {
		return trots[position].status();
	}
	
	public int getTrotRents(int position) {
		return trots[position].getRents();
	}
	
	public int getTrotTotalMinutes(int position) {
		return trots[position].getTotalMinutes();
	}
	
	public int getTotalRents() {
		return totalRents;
	}
	
	public int getTotalSpent() {
		return totalSpent;
	}
	
	public int getTotalMinutesLate() {
		return totalMinutesLate;
	}
	
	public boolean usedPromotion() {
		return usedPromotion;
	}
	
	public void deactivateTrot(int position) {
		trots[position].deactivate();
	}
	
	public void activateTrot(int position) {
		trots[position].activate();
	}
	
	public boolean trotIsActivated(int position) {
		return trots[position].isActivated();
	}
	
}
