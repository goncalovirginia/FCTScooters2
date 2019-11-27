
/**
 * @author Goncalo Virginia N-56773 e Afonso Batista N-57796
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
		String status = "parada";
		
		if (isActivated() && client != null) {
			status = "alugada";
		}
		else {
			status = "inactiva";
		}
		
		return status;
	}
	
	public Client getClient() {
		return client;
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
	
	public void setCoordinates(double y, double x) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double distance(double yClient, double xClient) {
		return Math.sqrt((x - xClient)*(x - xClient) + (y - yClient)*(y - yClient));
	}
	
}
