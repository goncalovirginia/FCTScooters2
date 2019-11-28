
/**
 * @author Goncalo Virginia N-56773 e Afonso Batista N-57796
 * 
 * Handles all the information concerning the scooter.
 */

import java.lang.Math;

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
		x = 0;
		y = 0;
	}
	
	/**
	 * Saves a client.
	 * 
	 * @param client - The object client.
	 */
	public void rent(Client client) {
		this.client = client;
	}
	
	/**
	 * Removes the scooters' connection with the client it was being used by,
	 * adds to the total minutes and increases the total rents.
	 *  
	 * @param minutes - Time (minutes) spent on the last rent.
	 */
	public void release(int minutes) {
		client = null;
		totalMinutes += minutes;
		rents++;
	}
	
	/**
	 * Deactivate a scooter.
	 */
	public void deactivate() {
		activated = false;
	}
	
	/**
	 * Activate a scooter.
	 */
	public void activate() {
		activated = true;
	}
	
	/**
	 * Sets the location (latitude and longitude) to a scooter.
	 * 
	 * @param y - The scooters' latitude.
	 * @param x - The scooters' longitude.
	 */
	public void setCoordinates(double y, double x) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return The scooters' ID.
	 */
	public String getId() {
		return idTrot;
	}
	
	/**
	 * @return The scooters' license plate.
	 */
	public String getLicensePlate() {
		return licensePlate;
	}
	
	/**
	 * Checks the scooters' current status.
	 * @return The current status.
	 */
	public String status() {
		String status = "parada";
		
		if (isActivated() && client != null) {
			status = "alugada";
		}
		else {
			status = "inactiva";
		}
		
		return status;
	}
	
	/**
	 * @return The scooters' rented client.
	 */
	public Client getClient() {
		return client;
	}
	
	/**
	 * 
	 * @return The scooters' total number of rents.
	 */
	public int getRents() {
		return rents;
	}
	
	/**
	 * @return The scooters' total number of minutes rented.
	 */
	public int getTotalMinutes() {
		return totalMinutes;
	}
	
	/**
	 * @return The scooters' longitude.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @return The scooters' latitude.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Calculates the scooters' distance in relation to the clients' coordinates.
	 * @param yClient - client latitude.
	 * @param xClient - client longitude.
	 * @return The distance between a scooter and a client. 
	 */
	public double distance(double yClient, double xClient) {
		return Math.sqrt((x - xClient)*(x - xClient) + (y - yClient)*(y - yClient));
	}
	
	/**
	 * @return True is the scooter is activated.
	 */
	public boolean isActivated() {
		return activated;
	}
	
}
