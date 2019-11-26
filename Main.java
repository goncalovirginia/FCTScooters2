
/**
 * @author Goncalo Virginia N-56773 e Afonso Batista N-57796.
 * Interacts with the user and Manager class in order to control the Scooter renting system.
 */

import java.util.Scanner;

public class Main {

	/* Constants  Errors */
	private static final String ERROR_INVALID_COMMAND = "Comando invalido.";
	private static final String ERROR_CLIENT_EXISTS = "Cliente existente.";
	private static final String ERROR_CLIENT_DOES_NOT_EXIST = "Cliente inexistente.";
	private static final String ERROR_CLIENT_MOVING = "Cliente em movimento.";
	private static final String ERROR_SCOOTER_EXISTS = "Trotinete existente.";
	private static final String ERROR_SCOOTER_DOES_NOT_EXIST = "Trotinete inexistente.";
	private static final String ERROR_CLIENT_WITHOUT_SCOOTER = "Cliente sem trotinete.";
	private static final String ERROR_SCOOTER_NOT_RENTED = "Trotinete nao alugada.";
	private static final String ERROR_INVALID_AMOUNT = "Valor invalido.";
	private static final String ERROR_INSUFFICIENT_BALANCE = "Cliente sem saldo suficiente.";
	private static final String ERROR_SCOOTER_CANNOT_BE_RENTED = "Trotinete nao pode ser alugada.";
	private static final String ERROR_SCOOTER_MOVING = "Trotinete em movimento.";
	private static final String ERROR_SCOOTER_NOT_INACTIVE = "Trotinete nao inactiva.";
	private static final String ERROR_INVALID_LOCATION = "Localizacao invalida.";

	/* Constants  Successes */
	private static final String SUCCESS_CLIENT_INSERTED = "Insercao de cliente com sucesso.";
	private static final String SUCCESS_CLIENT_REMOVED = "Cliente removido com sucesso.";
	private static final String SUCCESS_SCOOTER_INSERTED = "Insercao de trotinete com sucesso.";
	private static final String SUCCESS_BALANCE_LOADED = "Carregamento efectuado.";
	private static final String SUCCESS_RENT_INITIALIZED = "Aluguer efectuado com sucesso.";
	private static final String SUCCESS_RENT_TERMINATED = "Aluguer terminado.";
	private static final String SUCCESS_SCOOTER_DEACTIVATED = "Trotinete desactivada.";
	private static final String SUCCESS_SCOOTER_REACTIVATED = "Trotinete reactivada.";
	private static final String SUCCESS_EXIT = "Saindo...";

	/* Constants  Options */
	private static final String ADD_CLIENT = "ADCLIENTE";
	private static final String REM_CLIENT = "REMCLIENTE";
	private static final String ADD_SCOOTER = "ADTROT";
	private static final String CLIENT_DATA = "DADOSCLIENTE";
	private static final String SCOOTER = "TROT";
	private static final String SCOOTER_DATA = "DADOSTROT";
	private static final String CLIENT = "CLIENTE";
	private static final String LOAD_BALANCE = "CARRSALDO";
	private static final String RENT = "ALUGAR";
	private static final String RELEASE = "LIBERTAR";
	private static final String DEACTIVATE_SCOOTER = "DESTROT";
	private static final String ACTIVATE_SCOOTER = "REACTROT";
	private static final String LIST_TROTS = "LISTTROT";
	private static final String LIST_CLIENTS = "LISTCLIENTE";
	private static final String LIST_CLIENTS_NEGATIVE_BALANCE = "LISTDEV";
	private static final String SYS_STATE = "ESTADOSISTEMA";
	private static final String RELEASE_LOCATION = "LIBLOC";
	private static final String LOCATE_TROTS = "LOCTROT";
	private static final String EXIT = "SAIR";

	/**
	 * Creates and initializes the Scanner and Manager. Repeatedly reads inputs and
	 * interprets them until the command "SAIR" is typed.
	 */
	public static void main(String[] args) {
		Manager manager = new Manager();
		Scanner in = new Scanner(System.in);
		String option;

		do {

			option = readOption(in);
			executeOption(in, option, manager);

		} while (!option.equals(EXIT));

		System.out.println(SUCCESS_EXIT);
		managerStatus(manager);
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
	 * Reads the inserted option and executes the correct method. Gives an error
	 * message in the case of an invalid command.
	 * 
	 * @param in
	 * @param option
	 * @param manager
	 */
	private static void executeOption(Scanner in, String option, Manager manager) {

		switch (option) {
		case ADD_CLIENT:
			addClient(in, manager);
			break;
		case REM_CLIENT:
			removeClient(in, manager);
			break;
		case ADD_SCOOTER:
			addTrot(in, manager);
			break;
		case CLIENT_DATA:
			clientInfo(in, manager);
			break;
		case SCOOTER:
			getTrotFromClient(in, manager);
			break;
		case SCOOTER_DATA:
			trotInfo(in, manager);
			break;
		case CLIENT:
			getClientFromTrot(in, manager);
			break;
		case LOAD_BALANCE:
			loadBalance(in, manager);
			break;
		case RENT:
			rentTrot(in, manager);
			break;
		case RELEASE:
			releaseTrot(in, manager);
			break;
		case SYS_STATE:
			managerStatus(manager);
			break;
		case DEACTIVATE_SCOOTER:
			deactivateTrot(in, manager);
			break;
		case ACTIVATE_SCOOTER:
			activateTrot(in, manager);
			break;
		case LIST_TROTS:
			listTrots(manager);
			break;
		case LIST_CLIENTS:
			listClientsOrdNif(manager);
			break;
		case LIST_CLIENTS_NEGATIVE_BALANCE:
			listClientsNegativeBalance(manager);
			break;
		case RELEASE_LOCATION:
			releaseTrotLocation(in, manager);
			break;
		case LOCATE_TROTS:
			locateTrots(in, manager);
			break;
		case EXIT:
			break;
		default:
			System.out.println(ERROR_INVALID_COMMAND);
			in.nextLine();
			break;
		}
	}

	/**
	 * Creates a new scooter.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void addClient(Scanner in, Manager manager) {
		String nif = in.next();
		String email = in.next();
		String phone = in.next();
		String name = in.next() + in.nextLine();

		if (manager.clientExists(nif)) {
			System.out.println(ERROR_CLIENT_EXISTS);
		} else {
			manager.addClient(nif, email, phone, name);
			System.out.println(SUCCESS_CLIENT_INSERTED);
		}
	}

	/**
	 * Removes the existing client.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void removeClient(Scanner in, Manager manager) {
		String nif = in.next();
		in.nextLine();

		if (!manager.clientExists(nif)) {
			System.out.println(ERROR_CLIENT_DOES_NOT_EXIST);
		} else if (manager.clientHasTrot(nif)) {
			System.out.println(ERROR_CLIENT_MOVING);
		} else {
			manager.removeClient(nif);
			System.out.println(SUCCESS_CLIENT_REMOVED);
		}
	}

	/**
	 * Creates a new scooter.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void addTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		String licensePlate = in.next();
		in.nextLine();

		if (manager.trotExists(idTrot)) {
			System.out.println(ERROR_SCOOTER_EXISTS);
		} else {
			manager.addTrot(idTrot, licensePlate);
			System.out.println(SUCCESS_SCOOTER_INSERTED);
		}
	}

	/**
	 * Outputs the inserted clients' information.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void clientInfo(Scanner in, Manager manager) {
		String nif = in.next();
		in.nextLine();

		if (manager.clientExists(nif)) {
			System.out.println(manager.getClientName(nif) + ": " + manager.getClientNif(nif) + ", "
					+ manager.getClientEmail(nif) + ", " + manager.getClientPhone(nif) + ", "
					+ manager.getClientBalance(nif) + ", " + manager.getClientTotalMinutes(nif) + ", "
					+ manager.getClientRents(nif) + ", " + manager.getClientMaxMinutes(nif) + ", "
					+ manager.getClientAvgMinutes(nif) + ", " + manager.getClientTotalSpent(nif));
		} else {
			System.out.println(ERROR_CLIENT_DOES_NOT_EXIST);
		}
	}

	/**
	 * Outputs the inserted clients' scooter information.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void getTrotFromClient(Scanner in, Manager manager) {
		String nif = in.next();
		in.nextLine();

		if (!manager.clientExists(nif)) {
			System.out.println(ERROR_CLIENT_DOES_NOT_EXIST);
		} else if (!manager.clientHasTrot(nif)) {
			System.out.println(ERROR_CLIENT_WITHOUT_SCOOTER);
		} else {
			System.out.println(manager.getClientIdTrot(nif) + ", " + manager.getClientLicensePlate(nif));
		}
	}

	/**
	 * Outputs the scooters' information.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void trotInfo(Scanner in, Manager manager) {
		String idTrot = in.next();
		in.nextLine();

		if (!manager.trotExists(idTrot)) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		} else {
			System.out.println(manager.getTrotLicensePlate(idTrot) + ": " + manager.getTrotStatus(idTrot) + ", "
					+ manager.getTrotRents(idTrot) + ", " + manager.getTrotTotalMinutes(idTrot));
		}
	}

	/**
	 * Outputs the inserted scooters' client information.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void getClientFromTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		in.nextLine();

		if (!manager.trotExists(idTrot)) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		} else if (!manager.trotHasClient(idTrot)) {
			System.out.println(ERROR_SCOOTER_NOT_RENTED);
		} else {
			System.out.println(manager.getTrotNif(idTrot) + ", " + manager.getTrotName(idTrot));
		}
	}

	/**
	 * Loads the inserted clients' balance with the inserted amount.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void loadBalance(Scanner in, Manager manager) {
		String nif = in.next();
		int amount = in.nextInt();
		in.nextLine();

		if (amount <= 0) {
			System.out.println(ERROR_INVALID_AMOUNT);
		} else if (!manager.clientExists(nif)) {
			System.out.println(ERROR_CLIENT_DOES_NOT_EXIST);
		} else {
			manager.loadBalance(nif, amount);
			System.out.println(SUCCESS_BALANCE_LOADED);
		}
	}

	/**
	 * Rents a scooter (using the inserted client).
	 * 
	 * @param in
	 * @param manager
	 */
	private static void rentTrot(Scanner in, Manager manager) {
		String nif = in.next();
		String idTrot = in.next();
		in.nextLine();

		if (!manager.clientExists(nif)) {
			System.out.println(ERROR_CLIENT_DOES_NOT_EXIST);
		} else if (!manager.trotExists(idTrot)) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		} else if (!(manager.trotIsActivated(idTrot) && manager.getTrotStatus(idTrot).equals("parada"))) {
			System.out.println(ERROR_SCOOTER_CANNOT_BE_RENTED);
		} else if (manager.clientHasTrot(nif)) {
			System.out.println(ERROR_CLIENT_MOVING);
		} else if (manager.enoughBalance(nif)) {
			System.out.println(ERROR_INSUFFICIENT_BALANCE);
		} else {
			manager.rentTrot(nif, idTrot);
			System.out.println(SUCCESS_RENT_INITIALIZED);
		}
	}

	/**
	 * Releases the scooter after a client uses it for a certain amount of minutes.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void releaseTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		int minutes = in.nextInt();
		in.nextLine();

		if (manager.areValidMinutes(minutes)) {
			System.out.println(ERROR_INVALID_AMOUNT);
		} else if (!manager.trotExists(idTrot)) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		} else if (!manager.getTrotStatus(idTrot).equals("alugada")) {
			System.out.println(ERROR_SCOOTER_NOT_RENTED);
		} else {
			manager.releaseTrot(idTrot, minutes);
			System.out.println(SUCCESS_RENT_TERMINATED);
		}
	}

	/**
	 * Outputs the system managers' information.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void managerStatus(Manager manager) {
		System.out.println("Estado actual: " + manager.getTotalRents() + ", " + manager.getTotalSpent() + ", "
				+ manager.getTotalMinutesLate());
	}

	/**
	 * Deactivates the scooter (cannot be used for rents until reactivated).
	 * 
	 * @param in
	 * @param manager
	 */
	private static void deactivateTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		in.nextLine();

		if (!manager.trotExists(idTrot)) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		} else if (manager.getTrotStatus(idTrot).equals("alugada")) {
			System.out.println(ERROR_SCOOTER_MOVING);
		} else {
			manager.deactivateTrot(idTrot);
			System.out.println(SUCCESS_SCOOTER_DEACTIVATED);
		}
	}

	/**
	 * Activates the scooter.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void activateTrot(Scanner in, Manager manager) {
		String idTrot = in.next();
		in.nextLine();

		if (!manager.trotExists(idTrot)) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		} else if (!manager.getTrotStatus(idTrot).equals("inactiva")) {
			System.out.println(ERROR_SCOOTER_NOT_INACTIVE);
		} else {
			manager.activateTrot(idTrot);
			System.out.println(SUCCESS_SCOOTER_REACTIVATED);
		}
	}

	private static void listTrots(Manager manager) {
		TrotIterator iterator = manager.newTrotIterator();

		while (iterator.hasNext()) {
			Trot trot = iterator.next();

			System.out.println(trot.getLicensePlate() + ": " + trot.status() + ", " + trot.getRents() + ", "
					+ trot.getTotalMinutes());
		}
	}

	private static void listClientsOrdNif(Manager manager) {
		ClientIterator iterator = manager.newClientIterator();

		while (iterator.hasNext()) {
			Client client = iterator.next();

			System.out.println(client.getName() + ": " + client.getNif() + ", " + client.getEmail() + ", "
					+ client.getPhone() + ", " + client.getBalance() + ", " + client.getTotalMinutes() + ", "
					+ client.getRents() + ", " + client.getMaxMinutes() + ", " + client.getAvgMinutes() + ", "
					+ client.getTotalSpent());
		}
	}

	private static void listClientsNegativeBalance(Manager manager) {
		ClientIteratorOrdNegBal iterator = manager.newClientIteratorOrdNegBal();

		while (iterator.hasNext()) {
			Client client = iterator.next();

			System.out.println(client.getName() + ": " + client.getNif() + ", " + client.getEmail() + ", "
					+ client.getPhone() + ", " + client.getBalance() + ", " + client.getTotalMinutes() + ", "
					+ client.getRents() + ", " + client.getMaxMinutes() + ", " + client.getAvgMinutes() + ", "
					+ client.getTotalSpent());
		}
	}

	/**
	 * Releases the scooter after a client uses it for a certain amount of minutes.
	 * 
	 * @param in
	 * @param manager
	 */
	private static void releaseTrotLocation(Scanner in, Manager manager) {
		String idTrot = in.next();
		int minutes = in.nextInt();
		double y = in.nextDouble();
		double x = in.nextDouble();
		in.nextLine();

		if (!manager.checkCoordinates(y, x)) {
			System.out.println(ERROR_INVALID_LOCATION);
		} else if (manager.areValidMinutes(minutes)) {
			System.out.println(ERROR_INVALID_AMOUNT);
		} else if (!manager.trotExists(idTrot)) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		} else if (!manager.getTrotStatus(idTrot).equals("alugada")) {
			System.out.println(ERROR_SCOOTER_NOT_RENTED);
		} else {
			manager.releaseTrotLocation(idTrot, minutes, y, x);
			System.out.println(SUCCESS_RENT_TERMINATED);
		}
	}

	private static void locateTrots(Scanner in, Manager manager) {
		double yClient = in.nextDouble();
		double xClient = in.nextDouble();
		in.nextLine();

		TrotIteratorOrdDistance iterator = manager.newTrotIteratorOrdDistance(xClient, yClient);

		while (iterator.hasNext()) {
			Trot trot = iterator.next();

			System.out.printf("Distancia: %.6f\n", trot.distance(yClient, xClient));
			System.out.println(trot.getLicensePlate() + ": " + trot.status() + ", " + trot.getRents() + ", "
					+ trot.getTotalMinutes() + ", ");
			System.out.printf("%.6f, %.6f\n", trot.getY(), trot.getX());
		}
	}

}
