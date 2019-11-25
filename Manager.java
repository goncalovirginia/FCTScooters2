
/**
 * @author Goncalo Virginia N-56773 & Afonso Batista N-57796.
 * 
 * Interacts and receives inputs from the Main class.
 * Manages the scooter renting system and all the interactions concerning the Client and Trot (Scooter) classes.
 */

public class Manager {

	/* Constants */
	private static final int FEE = 100, DEFAULT_VALUE = 0, NOT_FOUND = -1, NORMAL_TIME = 60;
	private static final double NORTE = 38.663964, SUL = 38.658475, OESTE = -9.209269, LESTE = -9.201978;
	
	/* Instance variables */
	private ClientList clients;
	private TrotList trots;
	private int totalRents, totalSpent, totalMinutesLate, minutesLate, tripCost, clientCounter, trotCounter;

	/* Constructor */
	public Manager() {
		
		totalRents = DEFAULT_VALUE;
		totalSpent = DEFAULT_VALUE;
		totalMinutesLate = DEFAULT_VALUE;

	}
	
	/**
	 * Searches the list of clients for a client with a certain NIF.
	 * @param nif - The clients' NIF (Identification number).
	 * @return Position of the client on the client list.
	 */
	private int findClient(String nif) {
		return clients.findClient(nif);
	}
	
	/**
	 * Searches the list of scooters for a scooter with a certain ID.
	 * @param id - The scooters' ID.
	 * @return Position of the scooter on the scooter list. (-1 if it doesn't exist).
	 */
	private int findTrot(String id) {
		return trots.findTrot(id);
	}
	
	public boolean clientExists(String nif) {
		return findClient(nif) != NOT_FOUND;
	}
	
	public boolean trotExists(String idTrot) {
		return findTrot(idTrot) != NOT_FOUND;
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
		clients.addClient(nif, email, phone, name);
	}

	/**
	 * Removes the client.
	 * @param position - Position of the client on the list of clients.
	 */
	public void removeClient(String nif) {
		for (int i = findClient(nif); i < clientCounter-1; i++) {
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
		trots.addTrot(idTrot, licensePlate);
	}

	/**
	 * Loads the clients' balance with a certain amount.
	 * @param amount - Money (cents) to add to the clients' balance.
	 * @param position - Position of the client on the list of clients.
	 * @pre amount > 0
	 */
	public void loadBalance(String nif, int amount) {
		clients[findClient(nif)].loadBalance(amount);
	}

	/**
	 * Rents a scooter (using the inserted client).
	 * @param nif - The clients' Identification number.
	 * @param idTrot - The scooters' ID.
	 * @pre nif != null && idTrot != null
	 */
	public void rentTrot(String nif, String idTrot) {
		
		clients.rentClient(nif).rent(trots.rentTrot(idTrot));   //MUDARRRR
		trots.rentTrot(idTrot).rent(clients.rentClient(nif));
	}
	
	/**
	 * Releases the scooter and unloads the clients' balance depending on the amount of minutes.
	 * @param minutes - Time (minutes) the client spent using the Scooter.
	 * @pre minutes > 0
	 */
	public void releaseTrot(String idTrot, int minutes) {
		int times = DEFAULT_VALUE;
		int positionTrot = findTrot(idTrot);
		int positionClient = findClient(trots[positionTrot].getClient().getNif());
		
		clients[positionClient].release(minutes);
		trots[positionTrot].release(minutes);
		
		minutesLate = (minutes - NORMAL_TIME);
		totalRents++;
		
		if (minutesLate > DEFAULT_VALUE) {
			if (minutesLate % 30 == DEFAULT_VALUE) {
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
	
	private boolean trotListIsFull() {
		return trotCounter == trots.length;
	}
	
	private void resizeTrotList() {
		Trot[] bigTrots = new Trot[ARRAY_GROWTH * trots.length];
		
		for (int i = DEFAULT_VALUE; i < trotCounter; i++) {
			bigTrots[i] = trots[i];
		}
		
		trots = bigTrots;
	}
	
	public String getClientNif(String nif) {
		return clients[findClient(nif)].getNif();
	}
	
	public String getClientName(String nif) {
		return clients[findClient(nif)].getName();
	}
	
	public String getClientEmail(String nif) {
		return clients[findClient(nif)].getEmail();
	}
	
	public String getClientPhone(String nif) {
		return clients[findClient(nif)].getPhone();
	}
	
	public int getClientBalance(String nif) {
		return clients[findClient(nif)].getBalance();
	}
	
	public int getClientTotalMinutes(String nif) {
		return clients[findClient(nif)].getTotalMinutes();
	}
	
	public int getClientRents(String nif) {
		return clients[findClient(nif)].getRents();
	}
	
	public int getClientMaxMinutes(String nif) {
		return clients[findClient(nif)].getMaxMinutes();
	}
	
	public int getClientAvgMinutes(String nif) {
		return clients[findClient(nif)].getAvgMinutes();
	}
	
	public int getClientTotalSpent(String nif) {
		return clients[findClient(nif)].getTotalSpent();
	}
	
	public boolean clientHasTrot(String nif) {
		return (clients[findClient(nif)].getTrot() != null);
	}
	
	public String getClientIdTrot(String nif) {
		return clients[findClient(nif)].getTrot().getId();
	}
	
	public String getClientLicensePlate(String nif) {
		return clients[findClient(nif)].getTrot().getLicensePlate();
	}
	
	public boolean trotHasClient(String idTrot) {
		return (trots[findTrot(idTrot)].getClient() != null);
	}
	
	public String getTrotNif(String idTrot) {
		return trots[findTrot(idTrot)].getClient().getNif();
	}
	
	public String getTrotName(String idTrot) {
		return trots[findTrot(idTrot)].getClient().getName();
	}
	
	public String getTrotId(String idTrot) {
		return trots[findTrot(idTrot)].getId();
	}
	
	public String getTrotLicensePlate(String idTrot) {
		return trots[findTrot(idTrot)].getLicensePlate();
	}
	
	public String getTrotStatus(String idTrot) {
		return trots[findTrot(idTrot)].status();
	}
	
	public int getTrotRents(String idTrot) {
		return trots[findTrot(idTrot)].getRents();
	}
	
	public int getTrotTotalMinutes(String idTrot) {
		return trots[findTrot(idTrot)].getTotalMinutes();
	}
	
	public void deactivateTrot(String idTrot) {
		trots[findTrot(idTrot)].deactivate();
	}
	
	public void activateTrot(String idTrot) {
		trots[findTrot(idTrot)].activate();
	}
	
	public boolean trotIsActivated(String idTrot) {
		return trots[findTrot(idTrot)].isActivated();
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
	
	public boolean clientHasNegativeBalance(String nif) { 
		return clients[findClient(nif)].getBalance() < DEFAULT_VALUE; 
	}
	
	public TrotIterator newTrotIterator() {
		return new TrotIterator(trots, trotCounter);
	}
	
	public ClientIteratorOrdNif newClientIteratorOrdNif() {
		return new ClientIteratorOrdNif(clients, clientCounter);
	}
	
	public ClientIteratorOrdNegBal newClientIteratorOrdNegBal() {
		return new ClientIteratorOrdNegBal(clients, clientCounter);
	}
	
	public boolean checkCoordinates(double x, double y) {
		return x >= OESTE && x <= LESTE && y >= SUL && y <= NORTE;
	}
	
	public void releaseTrotLocation(String idTrot, int minutes, double x, double y) {
		releaseTrot(idTrot, minutes);
		trots[findTrot(idTrot)].setCoordinates(x, y);
	}
	
	public TrotIteratorOrdDistance newTrotIteratorOrdDistance(double xClient, double yClient) {
		return new TrotIteratorOrdDistance(trots, trotCounter, xClient, yClient);
	}
	
}
