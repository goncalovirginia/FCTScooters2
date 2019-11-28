
/**
 * @author Goncalo Virginia N-56773 & Afonso Batista N-57796.
 * 
 *         Interacts and receives inputs from the Main class. Manages the
 *         scooter renting system and all the interactions concerning the clients
 *         and scooters.
 */

public class Manager {

	/* Constants */
	private static final int FEE = 100, NORMAL_TIME = 60, TAX_TIME = 30;
	private static final double NORTE = 38.663964, SUL = 38.658475, OESTE = -9.209269, LESTE = -9.201978;

	/* Instance variables */
	private ClientList clients;
	private TrotList trots;
	private int totalRents, totalSpent, totalMinutesLate, tripCost;

	/* Constructor */
	public Manager() {
		clients = new ClientList();
		trots = new TrotList();
		totalRents = 0;
		totalSpent = 0;
		totalMinutesLate = 0;
	}

	/**
	 * Adds a new client to the list.
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
	 * Adds a new Scooter to the list.
	 * 
	 * @param idTrot       - The new scooters' ID.
	 * @param licensePlate - The new scooters' license plate.
	 * @pre idTrot != null && licensePlate != null
	 */
	public void addTrot(String idTrot, String licensePlate) {
		trots.addTrot(idTrot, licensePlate);
	}

	/**
	 * Removes a specified client.
	 * 
	 * @param nif - The clients' NIF (Identification number).
	 */
	public void removeClient(String nif) {
		clients.removeClient(nif);
	}

	/**
	 * Loads the clients' balance with a certain amount (cents).
	 * 
	 * @param amount - Money (cents) to add to the clients' balance.
	 * @param nif    - The clients' NIF (Identification number).
	 * @pre amount > 0
	 */
	public void loadBalance(String nif, int amount) {
		clients.loadBalance(nif, amount);
	}

	/**
	 * Rents a scooter (using the inserted client).
	 * 
	 * @param nif    - The clients' NIF (Identification number).
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
	 * @param minutes - Time (minutes) the client spent using a scooter.
	 * @pre idTrot != null && minutes > 0
	 */
	public void releaseTrot(String idTrot, int minutes) {
		int times = 0;
		int minutesLate = (minutes - NORMAL_TIME);

		if (minutesLate > 0) {
			if (minutesLate % TAX_TIME == 0) {
				times = minutesLate / TAX_TIME;
			} else {
				times = (minutesLate / TAX_TIME) + 1;
			}
		} else {
			minutesLate = 0;
		}

		tripCost = FEE * (times + 1);
		clients.release(trots.getTrot(idTrot).getClient().getNif(), minutes, tripCost);
		trots.release(idTrot, minutes);
		totalSpent += tripCost;
		totalMinutesLate += minutesLate;
		totalRents++;
	}

	/**
	 * Deactivate a specified scooter.
	 * 
	 * @param idTrot - The scooters' ID.
	 */
	public void deactivateTrot(String idTrot) {
		trots.getTrot(idTrot).deactivate();
	}

	/**
	 * Activate a specified scooter.
	 * 
	 * @param idTrot - The scooters' ID.
	 */
	public void activateTrot(String idTrot) {
		trots.getTrot(idTrot).activate();
	}

	/**
	 * Releases the scooter at the inserted location.
	 * 
	 * @param idTrot  - The scooters' ID.
	 * @param minutes - Time (minutes) the client spent using a scooter.
	 * @param y       - The scooters' latitude.
	 * @param x       - The scooters' longitude.
	 */
	public void releaseTrotLocation(String idTrot, int minutes, double y, double x) {
		releaseTrot(idTrot, minutes);
		trots.getTrot(idTrot).setCoordinates(y, x);
	}

	/**
	 * @param nif - The clients' NIF (Identification number).
	 * @return The clients' NIF.
	 */
	public String getClientNif(String nif) {
		return clients.getClient(nif).getNif();
	}

	/**
	 * @param nif - The clients' NIF (Identification number).
	 * @return The clients' name.
	 */
	public String getClientName(String nif) {
		return clients.getClient(nif).getName();
	}

	/**
	 * @param nif - The clients' NIF (Identification number).
	 * @return The clients' Email.
	 */
	public String getClientEmail(String nif) {
		return clients.getClient(nif).getEmail();
	}

	/**
	 * @param nif - The clients' NIF (Identification number).
	 * @return The clients' phone number.
	 */
	public String getClientPhone(String nif) {
		return clients.getClient(nif).getPhone();
	}

	/**
	 * @param nif - The clients' NIF (Identification number).
	 * @return The clients' rented scooter ID.
	 */
	public String getClientIdTrot(String nif) {
		return clients.getClient(nif).getTrot().getId();
	}

	/**
	 * 
	 * @param nif - The clients' NIF (Identification number).
	 * @return The clients' rented scooter license plate.
	 */
	public String getClientLicensePlate(String nif) {
		return clients.getClient(nif).getTrot().getLicensePlate();
	}

	/**
	 * @param nif - The clients' NIF (Identification number).
	 * @return The clients' balance.
	 */
	public int getClientBalance(String nif) {
		return clients.getClient(nif).getBalance();
	}

	/**
	 * @param nif - The clients' NIF (Identification number).
	 * @return The clients' total number of minutes renting.
	 */
	public int getClientTotalMinutes(String nif) {
		return clients.getClient(nif).getTotalMinutes();
	}

	/**
	 * @param nif - The clients' NIF (Identification number).
	 * @return The clients' total number of rents.
	 */
	public int getClientRents(String nif) {
		return clients.getClient(nif).getRents();
	}

	/**
	 * @param The clients' NIF (Identification number).
	 * @return The clients' maximum minutes spent on a single rent.
	 */
	public int getClientMaxMinutes(String nif) {
		return clients.getClient(nif).getMaxMinutes();
	}

	/**
	 * @param nif - The clients' NIF (Identification number).
	 * @return The clients' average minutes spent on all rents.
	 */
	public int getClientAvgMinutes(String nif) {
		return clients.getClient(nif).getAvgMinutes();
	}

	/**
	 * @param nif - The clients' NIF (Identification number).
	 * @return The clients' total amount spent (in cents).
	 */
	public int getClientTotalSpent(String nif) {
		return clients.getClient(nif).getTotalSpent();
	}

	/**
	 * @param idTrot - The scooters' ID.
	 * @return The scooters' saved client NIF.
	 */
	public String getTrotNif(String idTrot) {
		return trots.getTrot(idTrot).getClient().getNif();
	}

	/**
	 * @param idTrot - The scooters' ID.
	 * @return The scooters' saved client name.
	 */
	public String getTrotName(String idTrot) {
		return trots.getTrot(idTrot).getClient().getName();
	}

	/**
	 * @param idTrot - The scooters' ID.
	 * @return The scooters' ID.
	 */
	public String getTrotId(String idTrot) {
		return trots.getTrot(idTrot).getId();
	}

	/**
	 * @param idTrot - The scooters' ID.
	 * @return The scooters' license plate.
	 */
	public String getTrotLicensePlate(String idTrot) {
		return trots.getTrot(idTrot).getLicensePlate();
	}

	/**
	 * @param idTrot - The scooters' ID.
	 * @return The current status.
	 */
	public String getTrotStatus(String idTrot) {
		return trots.getTrot(idTrot).status();
	}

	/**
	 * @param idTrot - The scooters' ID.
	 * @return The scooters' total number of rents.
	 */
	public int getTrotRents(String idTrot) {
		return trots.getTrot(idTrot).getRents();
	}

	/**
	 * 
	 * @param idTrot - The scooters' ID.
	 * @return The scooters' total number of minutes rented.
	 */
	public int getTrotTotalMinutes(String idTrot) {
		return trots.getTrot(idTrot).getTotalMinutes();
	}

	/**
	 * @return The total number of rents saved in the scooter renting system.
	 */
	public int getTotalRents() {
		return totalRents;
	}

	/**
	 * @return The total amount (cents) spent by all the clients.
	 */
	public int getTotalSpent() {
		return totalSpent;
	}

	/**
	 * @return The total number of minutes late (after 60 minutes).
	 */
	public int getTotalMinutesLate() {
		return totalMinutesLate;
	}

	/**
	 * Searches the client list and verifies if a client with the inserted NIF
	 * exists.
	 * 
	 * @param nif - The clients' Identification number.
	 * @return True if a client with the inserted NIF exists.
	 */
	public boolean clientExists(String nif) {
		return clients.clientExists(nif);
	}

	/**
	 * Searches the scooter list and verifies if a scooter with the inserted ID
	 * exists.
	 * 
	 * @param idTrot - The scooters' ID.
	 * @return True if a scooter with the inserted ID exists.
	 */
	public boolean trotExists(String idTrot) {
		return trots.trotExists(idTrot);
	}

	/**
	 * Searches the client list and verifies if a client with the inserted NIF has
	 * enough money to rent a scooter.
	 * 
	 * @param nif - The clients' NIF (Identification number).
	 * @return True if the client with the inserted NIF has enough money to rent a
	 *         scooter.
	 */
	public boolean validBalance(String nif) {
		return clients.getClient(nif).getBalance() >= FEE;
	}

	/**
	 * @param minutes - Time (minutes) the client spent using a scooter.
	 * @return True if the inserted number of minutes is greater than zero.
	 */
	public boolean validMinutes(int minutes) {
		return minutes > 0;
	}

	/**
	 * Verifies if the client with the inserted NIF is renting a scooter.
	 * 
	 * @param nif - The clients' Identification number.
	 * @return True if the client with the inserted NIF is renting a scooter.
	 */
	public boolean clientHasTrot(String nif) {
		return clients.getClient(nif).getTrot() != null;
	}

	/**
	 * Verifies if a client is renting the scooter with the inserted ID.
	 * 
	 * @param idTrot - The scooters' ID.
	 * @return True if a client is renting the scooter with the inserted ID.
	 */
	public boolean trotHasClient(String idTrot) {
		return trots.getTrot(idTrot).getClient() != null;
	}

	/**
	 * Verifies if the specified scooter is activated.
	 * 
	 * @param idTrot - The scooters' ID.
	 * @return True if the scooter with the inserted ID is activated.
	 */
	public boolean trotIsActivated(String idTrot) {
		return trots.getTrot(idTrot).isActivated();
	}

	/**
	 * Verifies if the specified clients' balance is below 0.
	 * 
	 * @param nif - The clients' Identification number.
	 * @return True if the specified client has a negativa balance.
	 */
	public boolean clientHasNegativeBalance(String nif) {
		return clients.getClient(nif).getBalance() < 0;
	}

	/**
	 * Verifies if a scooter is within the limits of the campus.
	 * 
	 * @param y - The scooters' latitude.
	 * @param x - The scooters' longitude.
	 * @return True if the coordenates are valid.
	 */
	public boolean checkCoordinates(double y, double x) {
		return x >= OESTE && x <= LESTE && y >= SUL && y <= NORTE;
	}

	/**
	 * Creates a new iterator object for the scooter list.
	 * 
	 * @return The scooter iterator object.
	 */
	public TrotIterator newTrotIterator() {
		return new TrotIterator(trots.getTrotList(), trots.getCounter());
	}

	/**
	 * Creates a new iterator object for the client list.
	 * 
	 * @return The client iterator object.
	 */
	public ClientIterator newClientIterator() {
		return new ClientIterator(clients.getClientList(), clients.getCounter());
	}

	/**
	 * Creates a new iterator object that sorts the client list by negative balance
	 * in increasing order.
	 * 
	 * @return The client iterator.
	 */
	public ClientIteratorOrdNegBal newClientIteratorOrdNegBal() {
		return new ClientIteratorOrdNegBal(clients.getClientList(), clients.getCounter());
	}

	/**
	 * Creates a new iterator object that sorts the scooter list by distance in relation
	 * to thespecified client coordinates.
	 * 
	 * @param yClient - Client latitude.
	 * @param xClient - Client longitude.
	 * @return The scooter iterator.
	 */
	public TrotIteratorOrdDistance newTrotIteratorOrdDistance(double yClient, double xClient) {
		return new TrotIteratorOrdDistance(trots.getTrotList(), trots.getCounter(), yClient, xClient);
	}
}
