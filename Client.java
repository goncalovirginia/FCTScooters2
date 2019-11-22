
/**
 * @author Goncalo Virginia N-56773
 * Handles all the information concerning the client.
 */

public class Client {

	/* Instance variables */
	private String nif, email, phone, name;
	private int balance, totalMinutes, rents, maxMinutes, totalSpent, lastMaxMinutes;
	private Trot trot;
	
	/* Constructor */
	public Client(String nif, String email, String phone, String name) {
		this.nif = nif;
		this.email = email;
		this.phone = phone;
		this.name = name;
		balance = 200;
		totalSpent = 0;
		rents = 0;
		totalMinutes = 0;
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
	 * Unloads balance by an inserted amount.
	 * @param amount - Money (cents) to remove from balance.
	 * @pre amount > 0
	 */
	public void unloadBalance(int amount) {
		balance -= amount;
		totalSpent += amount;
	}
	
	/**
	 * Releases the connection to a scooter.
	 * @param minutes - Time (minutes) the client spent using the Scooter.
	 * @pre minutes > 0
	 */
	public void release(int minutes) {
		trot = null;
		totalMinutes += minutes;
		rents++;
		
		if (minutes > maxMinutes) {
			lastMaxMinutes = maxMinutes;
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
		if (rents == 0) {
			return 0;
		}
		else {
			return (totalMinutes/rents);
		}
	}
	
	/**
	 * Resets the clients' information to how it was before the last rent.
	 * @param minutes - Time (minutes) spent in the last rent.
	 * @param amount - Money (cents) spent in the last rent.
	 * @pre minutes > 0 && amount > 0
	 */
	public void promotion(int minutes, int amount) {
		totalMinutes -= minutes;
		maxMinutes = lastMaxMinutes;
		totalSpent -= amount;
		rents--;
	}
	
	public boolean greaterThan(Client other) {
		return this.getNif().compareToIgnoreCase(other.getNif()) > 0;
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
