
/**
 * Goncalo Virginia N-56773 e Afonso Batista N-57796
 * Handles all the information concerning the client.
 */

public class Client {

	/* Instance variables */
	private String nif, email, phone, name;
	private int balance, totalMinutes, rents, maxMinutes, totalSpent;
	private Trot trot;
	private static final int DEFAULT_VALUE = 0, NEW_ACCOUNT_BALANCE = 200;
	
	
	/* Constructor */
	public Client(String nif, String email, String phone, String name) {
		this.nif = nif;
		this.email = email;
		this.phone = phone;
		this.name = name;
		balance = NEW_ACCOUNT_BALANCE;
		totalSpent = DEFAULT_VALUE;
		rents = DEFAULT_VALUE;
		totalMinutes = DEFAULT_VALUE;
		trot = null;
	}
	
	/**
	 * Loads balance with an inserted amount.
	 * @param amount - Money (cents) to add to balance.
	 * @pre amount > 0
	 */
	public void loadBalance(int amount) {
		balance += amount;
	}
	
	/**
	 * Releases the connection to a scooter.
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
	 * Saves a scooters' ID.
	 * @param idTrot - The scooters' ID.
	 * @pre idTrot != null
	 */
	public void rent(Trot trot) {
		this.trot = trot;
	}
	
	/**
	 * Calculates the average time (minutes) spent per scooter rent.
	 * @return - Average time (minutes).
	 */
	public int getAvgMinutes() {
		
		int average = DEFAULT_VALUE;
		
		if (rents != DEFAULT_VALUE) {
			average = (totalMinutes/rents);
		}
		return average;
	}
	
	public boolean nifGreaterThan(Client other) {
		return this.getNif().compareToIgnoreCase(other.getNif()) > 0;
	}
	
	public boolean balanceGreaterThan(Client other) {
		return this.getBalance() > other.getBalance();
	}
	
	public boolean balanceEquals(Client other) {
		return this.getBalance() == other.getBalance();
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNif() {
		return nif;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public Trot getTrot() {
		return trot;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public int getTotalSpent() {
		return totalSpent;
	}
	
	public int getRents() {
		return rents;
	}
	
	public int getTotalMinutes() {
		return totalMinutes;
	}
	
	public int getMaxMinutes() {
		return maxMinutes;
	}

}
