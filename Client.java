
/**
 * @author Goncalo Virginia N-56773 e Afonso Batista N-57796 
 * 
 * Handles all the information concerning the client.
 */

public class Client {

	/* Instance variables */
	private String nif, email, phone, name;
	private Trot trot;
	private int balance, totalMinutes, rents, maxMinutes, totalSpent;
	private static final int DEFAULT_VALUE = 0, NEW_ACCOUNT_BALANCE = 200;

	/* Constructor */
	public Client(String nif, String email, String phone, String name) {
		this.nif = nif;
		this.email = email;
		this.phone = phone;
		this.name = name;
		trot = null;
		balance = NEW_ACCOUNT_BALANCE;
		totalSpent = DEFAULT_VALUE;
		rents = DEFAULT_VALUE;
		totalMinutes = DEFAULT_VALUE;
	}

	/**
	 * Loads balance with an inserted amount.
	 * 
	 * @param amount - Money (cents) to add to balance.
	 * @pre amount > 0
	 */
	public void loadBalance(int amount) {
		balance += amount;
	}

	/**
	 * Releases the connection to a scooter.
	 * 
	 * @param minutes - Time (minutes) the client spent using the Scooter.
	 * @pre minutes > 0
	 */
	public void release(int minutes, int amount) {
		trot = null;
		totalMinutes += minutes;
		balance -= amount;
		totalSpent += amount;
		rents++;

		if (minutes > maxMinutes) {
			maxMinutes = minutes;
		}
	}

	/**
	 * Saves a scooter.
	 * 
	 * @param trot - The object trot.
	 */
	public void rent(Trot trot) {
		this.trot = trot;
	}

	/**
	 * @return The clients' name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The clients' Email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return The clients' NIF (Identification number).
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * @return The clients' phone number.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return The clients' rented scooter.
	 */
	public Trot getTrot() {
		return trot;
	}

	/**
	 * Verifies if the clients' NIF is lexicographically bigger than another clients' NIF.
	 * 
	 * @param other - Client object for comparison. 
	 * @return True if the clients' NIF is lexicographically bigger than the other. 
	 */
	public boolean nifGreaterThan(Client other) {
		return this.getNif().compareToIgnoreCase(other.getNif()) > 0;
	}

	/**
	 * Verifies if the clients' balance is greater than another clients' balance.
	 *  
	 * @param other - Client object for comparison.
	 * @return True if the clients' balance is greater than the other. 
	 */
	public boolean balanceGreaterThan(Client other) {
		return this.getBalance() > other.getBalance();
	}

	/**
	 * Verifies if the clients' balance is equals to another clients' balance.
	 * 
	 * @param other - Client object for comparison.
	 * @return True if the clients' balance is equals to the other.
	 */
	public boolean balanceEquals(Client other) {
		return this.getBalance() == other.getBalance();
	}

	/**
	 * Calculates the average time (minutes) spent per scooter rent.
	 * 
	 * @return - Average time (minutes).
	 */
	public int getAvgMinutes() {

		int average = DEFAULT_VALUE;

		if (rents != DEFAULT_VALUE) {
			average = (totalMinutes / rents);
		}
		return average;
	}

	/**
	 * @return The clients' balance.
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * @return The clients' total amount spent (in cents).
	 */
	public int getTotalSpent() {
		return totalSpent;
	}

	/**
	 * @return The clients' total number of rents.
	 */
	public int getRents() {
		return rents;
	}

	/**
	 * @return The clients' total number of minutes renting. 
	 */
	public int getTotalMinutes() {
		return totalMinutes;
	}

	/**
	 * @return The clients' maximum number of minutes spent in a single rent.
	 */
	public int getMaxMinutes() {
		return maxMinutes;
	}
}
