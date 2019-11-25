
/**
 * @author Goncalo Virginia N-56773 & Afonso Batista N-57796.
 * 
 *         Interacts and receives inputs from the Main class. Manages the
 *         scooter renting system and all the interactions concerning the Client
 *         and Trot (Scooter) classes.
 */

public class Manager {

	/* Constants */
	private static final int FEE = 100, DEFAULT_VALUE = 0, NORMAL_TIME = 60;
	private static final double NORTE = 38.663964, SUL = 38.658475, OESTE = -9.209269, LESTE = -9.201978;

	/* Instance variables */
	private ClientList clients;
	private TrotList trots;
	private int totalRents, totalSpent, totalMinutesLate, tripCost;

	/* Constructor */
	public Manager() {
		totalRents = DEFAULT_VALUE;
		totalSpent = DEFAULT_VALUE;
		totalMinutesLate = DEFAULT_VALUE;
	}

	public boolean clientExists(String nif) {
		return clients.clientExists(nif);
	}

	public boolean trotExists(String idTrot) {
		return trots.trotExist(idTrot);
	}

	/**
	 * Creates a new client using the following parameters:
	 * 
	 * @param nif   - The new clients' NIF (Identification number).
	 * @param email - The new clients' Email.
	 * @param phone - The new clients' phone number.
	 * @param name  - The new clients' name (can be more than 1 word).
	 * @pre nif != null && email != null && phone != null && name != null
	 */
	public void addClient(String nif, String email, String phone, String name) {
		clients.addClient(nif, email, phone, name);
		
	}

	/**
	 * Removes the client.
	 * 
	 * @param position - Position of the client on the list of clients.
	 */
	public void removeClient(String nif) {
		clients.removeClient(nif);
	}

	/**
	 * Creates a new Scooter using the following parameters:
	 * 
	 * @param idTrot       - The new scooters' ID.
	 * @param licensePlate - The new scooters' license plate.
	 * @pre idTrot != null && licensePlate != null
	 */
	public void addTrot(String idTrot, String licensePlate) {
		trots.addTrot(idTrot, licensePlate);
	}

	/**
	 * Loads the clients' balance with a certain amount.
	 * 
	 * @param amount   - Money (cents) to add to the clients' balance.
	 * @param position - Position of the client on the list of clients.
	 * @pre amount > 0
	 */
	public void loadBalance(String nif, int amount) {
		clients.loadBalance(nif, amount);
	}

	/**
	 * Rents a scooter (using the inserted client).
	 * 
	 * @param nif    - The clients' Identification number.
	 * @param idTrot - The scooters' ID.
	 * @pre nif != null && idTrot != null
	 */
	public void rentTrot(String nif, String idTrot) {

		clients.rent(nif, trots.getTrot(idTrot));
		trots.rent(idTrot, clients.getClient(nif));
	}

	/**
	 * Releases the scooter and unloads the clients' balance depending on the amount
	 * of minutes.
	 * 
	 * @param minutes - Time (minutes) the client spent using the Scooter.
	 * @pre minutes > 0
	 */
	public void releaseTrot(String idTrot, int minutes) {
		int times = DEFAULT_VALUE;
		int minutesLate = (minutes - NORMAL_TIME);

		if (minutesLate > DEFAULT_VALUE) {
			if (minutesLate % 30 == DEFAULT_VALUE) {
				times = minutesLate / 30;
			} else {
				times = (minutesLate / 30) + 1;
			}
		}

		tripCost = FEE * (times + 1);
		trots.release(idTrot, minutes);
		clients.release(trots.getTrot(idTrot).getClient().getNif(), minutes, tripCost);
		totalSpent += tripCost;
		totalMinutesLate += minutesLate;
		totalRents++;
	}

	public String getClientNif(String nif) {
		return clients.getClient(nif).getNif();
	}

	public String getClientName(String nif) {
		return clients.getClient(nif).getName();
	}

	public String getClientEmail(String nif) {
		return clients.getClient(nif).getEmail();
	}

	public String getClientPhone(String nif) {
		return clients.getClient(nif).getPhone();
	}

	public int getClientBalance(String nif) {
		return clients.getClient(nif).getBalance();
	}

	public int getClientTotalMinutes(String nif) {
		return clients.getClient(nif).getTotalMinutes();
	}

	public int getClientRents(String nif) {
		return clients.getClient(nif).getRents();
	}

	public int getClientMaxMinutes(String nif) {
		return clients.getClient(nif).getMaxMinutes();
	}

	public int getClientAvgMinutes(String nif) {
		return clients.getClient(nif).getAvgMinutes();
	}

	public int getClientTotalSpent(String nif) {
		return clients.getClient(nif).getTotalSpent();
	}

	public boolean clientHasTrot(String nif) {
		return clients.getClient(nif).getTrot() != null;
	}

	public String getClientIdTrot(String nif) {
		return clients.getClient(nif).getTrot().getId();
	}

	public String getClientLicensePlate(String nif) {
		return clients.getClient(nif).getTrot().getLicensePlate();
	}

	public boolean trotHasClient(String idTrot) {
		return trots.getTrot(idTrot).getClient() != null;
	}

	public String getTrotNif(String idTrot) {
		return trots.getTrot(idTrot).getClient().getNif();
	}

	public String getTrotName(String idTrot) {
		return trots.getTrot(idTrot).getClient().getName();
	}

	public String getTrotId(String idTrot) {
		return trots.getTrot(idTrot).getId();
	}

	public String getTrotLicensePlate(String idTrot) {
		return trots.getTrot(idTrot).getLicensePlate();
	}

	public String getTrotStatus(String idTrot) {
		return trots.getTrot(idTrot).status();
	}

	public int getTrotRents(String idTrot) {
		return trots.getTrot(idTrot).getRents();
	}

	public int getTrotTotalMinutes(String idTrot) {
		return trots.getTrot(idTrot).getTotalMinutes();
	}

	public void deactivateTrot(String idTrot) {
		trots.getTrot(idTrot).deactivate();
	}

	public void activateTrot(String idTrot) {
		trots.getTrot(idTrot).activate();
	}

	public boolean trotIsActivated(String idTrot) {
		return trots.getTrot(idTrot).isActivated();
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
		return clients.getClient(nif).getBalance() < DEFAULT_VALUE;
	}
	
	public boolean checkCoordinates(double x, double y) {
		return x >= OESTE && x <= LESTE && y >= SUL && y <= NORTE;
	}

	public void releaseTrotLocation(String idTrot, int minutes, double x, double y) {
		releaseTrot(idTrot, minutes);
		trots.getTrot(idTrot).setCoordinates(x, y);
	}

	public TrotIterator newTrotIterator() {
		return new TrotIterator(trots.getTrotList(), trots.getCounter());
	}
	
	public ClientIterator newClientIterator() {
		return new ClientIterator(clients.getClientList(), clients.getCounter());
	}
	
	public ClientIteratorOrdNegBal newClientIteratorOrdNegBal() {
		return new ClientIteratorOrdNegBal(clients.getClientList(), clients.getCounter());
	}

	public TrotIteratorOrdDistance newTrotIteratorOrdDistance(double xClient, double yClient) {
		return new TrotIteratorOrdDistance(trots.getTrotList(), trots.getCounter(), xClient, yClient);
	}

}
