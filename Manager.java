
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
	private int totalRents, totalSpent, totalMinutesLate, tripMinutes, tripCost, clientCounter, trotCounter;
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
	 * Searches the list of clients
	 * @param nif - The new clients' NIF (Identification number).
	 * @return Position of the client on the client list.
	 */
	public int clientExists(String nif) {
		for (int i = 0; i < clientCounter; i++) {
			if (clients[i].getNif().equalsIgnoreCase(nif)) {
				return i;
			}
		}
		return -1;
	}
	
	public int trotExists(String id) {
		for (int i = 0; i < trotCounter; i++) {
			if (trots[i].getId().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
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
	public void rentTrot(String nif, String idTrot) {
		client.setTrot(trot);
		trot.rent(client);
	}
	
	/**
	 * Releases the scooter and unloads the clients' balance depending on the amount of minutes.
	 * @param minutes - Time (minutes) the client spent using the Scooter.
	 * @pre minutes > 0
	 */
	public void releaseTrot(String idTrot, int minutes) {
		int times = 0, minutesLate;
		
		client.release(minutes);
		trot.release(minutes);
		
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
		client.unloadBalance(tripCost);
		totalSpent += tripCost;
		totalMinutesLate += minutesLate;
	}
	
	/**
	 * Resets the systems' information to how it was before the last rent.
	 */
	public void applyPromotion(String nif) {
		usedPromotion = true;
		client.loadBalance(tripCost);
		client.promotion(tripMinutes, tripCost);
		trot.promotion(tripMinutes);
		totalSpent -= tripCost;
		totalRents--;
		
		if (minutesLate > 0) {
			totalMinutesLate -= minutesLate;
		}
	}
	
	public String getClientNif() {
		return client.getNif();
	}
	
	public String getClientName() {
		return client.getName();
	}
	
	public String getClientEmail() {
		return client.getEmail();
	}
	
	public String getClientPhone() {
		return client.getPhone();
	}
	
	public int getClientBalance() {
		return client.getBalance();
	}
	
	public int getClientTotalMinutes() {
		return client.getTotalMinutes();
	}
	
	public int getClientRents() {
		return client.getRents();
	}
	
	public int getClientMaxMinutes() {
		return client.getMaxMinutes();
	}
	
	public int getClientAvgMinutes() {
		return client.getAvgMinutes();
	}
	
	public int getClientTotalSpent() {
		return client.getTotalSpent();
	}
	
	public boolean clientHasTrot() {
		return (client.getTrot() != null);
	}
	
	public String getClientIdTrot() {
		if (client.getTrot() == null) {
			return "";
		}
		else {
			return client.getTrot().getId();
		}
	}
	
	public boolean trotExists() {
		return (trot != null);
	}
	
	public String getTrotId() {
		return trot.getId();
	}
	
	public String getTrotLicensePlate() {
		return trot.getLicensePlate();
	}
	
	public String getTrotStatus() {
		return trot.status();
	}
	
	public int getTrotRents() {
		return trot.getRents();
	}
	
	public int getTrotTotalMinutes() {
		return trot.getTotalMinutes();
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
	
	public void deactivateTrot() {
		trot.deactivate();
	}
	
	public void activateTrot() {
		trot.activate();
	}
	
	public boolean trotIsActivated() {
		return trot.isActivated();
	}
	
}
