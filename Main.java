
/**
 * @author Goncalo Virginia N-56773
 * Interacts with the user and Manager class in order to control the Scooter renting system.
 */

import java.util.Scanner;

public class Main {

	/* Constants */
	private static final String INVALID_COMMAND = "Comando invalido.";
	private static final String CLIENT_EXISTS = "Cliente existente.";
	private static final String CLIENT_DOES_NOT_EXIST = "Cliente inexistente.";
	private static final String CLIENT_INSERTED = "Insercao de cliente com sucesso.";
	private static final String CLIENT_REMOVED = "Cliente removido com sucesso.";
	private static final String CLIENT_MOVING = "Cliente em movimento.";
	private static final String SCOOTER_EXISTS = "Trotinete existente.";
	private static final String SCOOTER_DOES_NOT_EXIST = "Trotinete inexistente.";
	private static final String SCOOTER_INSERTED = "Insercao de trotinete com sucesso.";
	private static final String CLIENT_WITHOUT_SCOOTER = "Cliente sem trotinete.";
	private static final String SCOOTER_NOT_RENTED = "Trotinete nao alugada.";
	private static final String BALANCE_LOADED = "Carregamento efectuado.";
	private static final String INVALID_AMOUNT = "Valor invalido.";
	private static final String RENT_INITIALIZED = "Aluguer efectuado com sucesso.";
	private static final String INSUFFICIENT_BALANCE = "Cliente sem saldo suficiente.";
	private static final String SCOOTER_CANNOT_BE_RENTED = "Trotinete nao pode ser alugada.";
	private static final String RENT_TERMINATED = "Aluguer terminado.";
	private static final String PROMOTION_APPLIED = "Promocao aplicada.";
	private static final String PROMOTION_ALREADY_APPLIED = "Promocao ja aplicada.";
	private static final String CLIENT_INITIALIZED_NEW_RENT = "Cliente iniciou novo aluguer.";
	private static final String SCOOTER_DEACTIVATED = "Trotinete desactivada.";
	private static final String SCOOTER_MOVING = "Trotinete em movimento.";
	private static final String SCOOTER_REACTIVATED = "Trotinete reactivada.";
	private static final String SCOOTER_NOT_INACTIVE = "Trotinete nao inactiva.";
	
	/**
	 * Creates and initializes the Scanner and Manager.
	 * Repeatedly reads inputs and interprets them until the command "SAIR" is typed.
	 */
	public static void main(String[] args) {
		Manager manager = new Manager();
		Scanner in = new Scanner(System.in);
		String option = "";

		while (!option.equals("SAIR")) {
			option = readOption(in);
			executeOption(in, option, manager);
		}
		
		System.out.println("Saindo...");
		managerStatus(in, manager);
		in.close();
	}

	/**
	 * @param in
	 * @return the first inserted word (before a space) in upper case.
	 */
	private static String readOption(Scanner in) {
		return in.next().toUpperCase();
	}

	/**
	 * Reads the inserted option and executes the correct method.
	 * Gives an error message in the case of an invalid command.
	 * @param in
	 * @param option
	 * @param manager
	 */
	private static void executeOption(Scanner in, String option, Manager manager) {

		switch (option) {
		case "ADCLIENTE":
			addClient(in, manager);
			break;
		case "REMCLIENTE":
			removeClient(in, manager);
			break;
		case "ADTROT":
			addTrot(in, manager);
			break;
		case "DADOSCLIENTE":
			clientInfo(in, manager);
			break;
		case "TROT":
			getTrotFromClient(in, manager);
			break;
		case "DADOSTROT":
			trotInfo(in, manager);
			break;
		case "CLIENTE":
			getClientFromTrot(in, manager);
			break;
		case "CARRSALDO":
			loadBalance(in, manager);
			break;
		case "ALUGAR":
			rentTrot(in, manager);
			break;
		case "LIBERTAR":
			releaseTrot(in, manager);
			break;
		case "ESTADOSISTEMA":
			managerStatus(in, manager);
			break;
		case "PROMOCAO":
			promotion(in, manager);
			break;
		case "DESTROT":
			deactivateTrot(in, manager);
			break;
		case "REACTROT":
			activateTrot(in, manager);
			break;
		case "SAIR":
			break;
		default:
			System.out.println(INVALID_COMMAND);
			in.nextLine();
			break;
		}
	}

	/**
	 * Creates a new scooter.
	 * @param in
	 * @param manager
	 */
	private static void addClient(Scanner in, Manager manager) {
		String nif = in.next();
		String email = in.next();
		String phone = in.next();
		String name = in.next() + in.nextLine();

		int position = manager.clientExists(nif);
		
		if (position == -1) {
			manager.addClient(nif, email, phone, name);
			System.out.println(CLIENT_INSERTED);
		} 
		else {
			System.out.println(CLIENT_EXISTS);
		}
	}

	/**
	 * Removes the existing client.
	 * @param in
	 * @param manager
	 */
	private static void removeClient(Scanner in, Manager manager) {
		String nif = in.next();
		in.nextLine();
		
		if (!(manager.clientExists() && manager.getClientNif().equalsIgnoreCase(nif))) {
			System.out.println(CLIENT_DOES_NOT_EXIST);
		}	
		else if (manager.clientHasTrot()) {
			System.out.println(CLIENT_MOVING);
		}
		else {
			manager.removeClient(nif);
			System.out.println(CLIENT_REMOVED);
		}
	}

	/**
	 * Creates a new scooter.
	 * @param in
	 * @param manager
	 */
	private static void addTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		String licensePlate = in.next();
		in.nextLine();
		
		if (!manager.trotExists()) {
			manager.addTrot(idTrot, licensePlate);
			System.out.println(SCOOTER_INSERTED);
		} 
		else if (manager.getTrotId().equalsIgnoreCase(idTrot)) {
			System.out.println(SCOOTER_EXISTS);
		}
		else {
			System.out.println(SCOOTER_EXISTS);
		}
	}

	/**
	 * Outputs the inserted clients' information.
	 * @param in
	 * @param manager
	 */
	private static void clientInfo(Scanner in, Manager manager) {
		String nif = in.next();
		in.nextLine();
		
		if (manager.clientExists() && manager.getClientNif().equalsIgnoreCase(nif)) {
			System.out.println(manager.getClientName() + ": " + manager.getClientNif() + ", " + manager.getClientEmail() + ", "
					+ manager.getClientPhone() + ", " + manager.getClientBalance() + ", " + manager.getClientTotalMinutes() + ", "
					+ manager.getClientRents() + ", " + manager.getClientMaxMinutes() + ", " + manager.getClientAvgMinutes() + ", "
					+ manager.getClientTotalSpent());
		} 
		else {
			System.out.println(CLIENT_DOES_NOT_EXIST);
		}
	}

	/**
	 * Outputs the inserted clients' scooter information.
	 * @param in
	 * @param manager
	 */
	private static void getTrotFromClient(Scanner in, Manager manager) {
		String nif = in.next();
		in.nextLine();

		if (!(manager.clientExists() && manager.getClientNif().equalsIgnoreCase(nif))) {
			System.out.println(CLIENT_DOES_NOT_EXIST);
		}
		else if (!manager.clientHasTrot()) {
			System.out.println(CLIENT_WITHOUT_SCOOTER);
		}
		else {
			System.out.println(manager.getTrotId() + ", " + manager.getTrotLicensePlate());
		}
	}

	/**
	 * Outputs the scooters' information.
	 * @param in
	 * @param manager
	 */
	private static void trotInfo(Scanner in, Manager manager) {
		String idTrot = in.next();
		in.nextLine();
		
		if (manager.trotExists() && manager.getTrotId().equalsIgnoreCase(idTrot)) {
			System.out.println(manager.getTrotLicensePlate() + ": " + manager.getTrotStatus() + ", " + manager.getTrotRents()
					+ ", " + manager.getTrotTotalMinutes());
		} 
		else {
			System.out.println(SCOOTER_DOES_NOT_EXIST);
		}
	}

	/**
	 * Outputs the inserted scooters' client information.
	 * @param in
	 * @param manager
	 */
	private static void getClientFromTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		in.nextLine();

		if (!(manager.trotExists() && manager.getTrotId().equalsIgnoreCase(idTrot))) {
			System.out.println(SCOOTER_DOES_NOT_EXIST);
		}
		else if (!(manager.clientExists() && manager.getClientIdTrot().equalsIgnoreCase(idTrot))) {
			System.out.println(SCOOTER_NOT_RENTED);
		}
		else {
			System.out.println(manager.getClientNif() + ", " + manager.getClientName());
		}
	}

	/**
	 * Loads the inserted clients' balance with the inserted amount.
	 * @param in
	 * @param manager
	 */
	private static void loadBalance(Scanner in, Manager manager) {
		String nif = in.next();
		int amount = in.nextInt();
		in.nextLine();
		
		if (amount <= 0) {
			System.out.println(INVALID_AMOUNT);
		}
		else if (!(manager.clientExists() && manager.getClientNif().equalsIgnoreCase(nif))) {
			System.out.println(CLIENT_DOES_NOT_EXIST);
		}
		else {
			manager.loadBalance(nif, amount);
			System.out.println(BALANCE_LOADED);
		}	
	}

	/**
	 * Rents a scooter (using the inserted client).
	 * @param in
	 * @param manager
	 */
	private static void rentTrot(Scanner in, Manager manager) {
		String nif = in.next();
		String idTrot = in.next();
		in.nextLine();
		
		if (!(manager.clientExists() && manager.getClientNif().equalsIgnoreCase(nif))) {
			System.out.println(CLIENT_DOES_NOT_EXIST);
		}
		else if (!(manager.trotExists() && manager.getTrotId().equalsIgnoreCase(idTrot))) {
			System.out.println(SCOOTER_DOES_NOT_EXIST);
		}
		else if (!(manager.trotIsActivated() && manager.getTrotStatus().equals("parada"))) {
			System.out.println(SCOOTER_CANNOT_BE_RENTED);
		}
		else if (manager.getClientBalance() < 100) {
			System.out.println(INSUFFICIENT_BALANCE);
		}
		else {
			manager.rentTrot(nif, idTrot);
			System.out.println(RENT_INITIALIZED);
		} 
	}
	
	/**
	 * Releases the scooter after a client uses it for a certain amount of minutes.
	 * @param in
	 * @param manager
	 */
	private static void releaseTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		int minutes = in.nextInt();
		in.nextLine();
		
		if (minutes <= 0) {
			System.out.println(INVALID_AMOUNT);
		}
		else if (!(manager.trotExists() && manager.getTrotId().equalsIgnoreCase(idTrot))) {
			System.out.println(SCOOTER_DOES_NOT_EXIST);
		}
		else if (!manager.getTrotStatus().equals("alugada")) {
			System.out.println(SCOOTER_NOT_RENTED);
		}
		else {
			manager.releaseTrot(idTrot, minutes);
			System.out.println(RENT_TERMINATED);
		}
	}

	/**
	 * Outputs the system managers' information.
	 * @param in
	 * @param manager
	 */
	private static void managerStatus(Scanner in, Manager manager) {
		System.out.println("Estado actual: " + manager.getTotalRents() + ", " + manager.getTotalSpent() + ", " + manager.getTotalMinutesLate());
	}

	/**
	 * Applies a promotion and resets everything to before the last rent.
	 * @param in
	 * @param manager
	 */
	private static void promotion(Scanner in, Manager manager) {
		String nif = in.next();
		in.nextLine();

		int position = manager.clientExists(nif);
		
		if (position == -1) {
			System.out.println(CLIENT_DOES_NOT_EXIST);
		}
		else if (!manager.getClientIdTrot().equals("")) {
			System.out.println(CLIENT_INITIALIZED_NEW_RENT);
		}
		else if (manager.usedPromotion()) {
			System.out.println(PROMOTION_ALREADY_APPLIED);
		}
		else {
			manager.applyPromotion(position);
			System.out.println(PROMOTION_APPLIED);
		}	
	}
	
	/**
	 * Deactivates the scooter (cannot be used for rents until reactivated).
	 * @param in
	 * @param manager
	 */
	private static void deactivateTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		in.nextLine();
		
		if (!(manager.trotExists() && manager.getTrotId().equalsIgnoreCase(idTrot))) {
			System.out.println(SCOOTER_DOES_NOT_EXIST);
		}
		else if (manager.getTrotStatus().equals("alugada")) {
			System.out.println(SCOOTER_MOVING);
		}
		else {
			manager.deactivateTrot();
			System.out.println(SCOOTER_DEACTIVATED);
		}
	}
	
	/**
	 * Activates the scooter.
	 * @param in
	 * @param manager
	 */
	private static void activateTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		in.nextLine();

		if (!(manager.trotExists() && manager.getTrotId().equalsIgnoreCase(idTrot))) {
			System.out.println(SCOOTER_DOES_NOT_EXIST);
		}
		else if (!manager.getTrotStatus().equals("inactiva")) {
			System.out.println(SCOOTER_NOT_INACTIVE);
		}
		else {
			manager.activateTrot();
			System.out.println(SCOOTER_REACTIVATED);
		}
	}
	
}
