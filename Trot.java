
/**
 * @author Goncalo Virginia N-56773
 * Handles all the information concerning the scooter.
 */

public class Trot {

	/* Instance variables */
	private String idTrot, licensePlate;
	private Client client;
	private int rents, totalMinutes;
	private boolean activated;
	private double x, y;
	
	/* Constructor */
	public Trot(String idTrot, String licensePlate) {
		this.idTrot = idTrot;
		this.licensePlate = licensePlate;
		rents = 0;
		totalMinutes = 0;
		client = null;
		activated = true;
	}
	
	public String getId() {
		return idTrot;
	}
	
	public int getRents() {
		return rents;
	}
	
	public int getTotalMinutes() {
		return totalMinutes;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}
	
	/**
	 * Saves a clients' Identification number.
	 * @param idClient - The clients' Identification number (NIF).
	 * @pre idClient != null
	 */
	public void rent(Client client) {
		this.client = client;
	}
	
	public void release(int minutes) {
		client = null;
		totalMinutes += minutes;
		rents++;
	}
	
	/**
	 * Checks the scooters' current status.
	 * @return the current status.
	 */
	public String status() {
		if (isActivated()) {
			if (client == null) {
				return "parada";
			}
			else {
				return "alugada";
			}
		}
		else {
			return "inactiva";
		}
	}
	
	public Client getClient() {
		return client;
	}
	
	public void promotion(int minutes) {
		totalMinutes -= minutes;
		rents--;
	}
	
	public void deactivate() {
		activated = false;
	}
	
	public void activate() {
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}
	
	public void setCoordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
}
