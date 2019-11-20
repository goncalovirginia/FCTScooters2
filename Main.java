
/**
 * @author Goncalo Virginia N-56773
 * Interacts with the user and Manager class in order to control the Scooter renting system.
 */

import java.util.Scanner;

public class Main {

	/* Constants Errors */
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
	private static final String ERROR_PROMOTION_ALREADY_APPLIED = "Promocao ja aplicada.";
	private static final String ERROR_CLIENT_INITIALIZED_NEW_RENT = "Cliente iniciou novo aluguer.";
	private static final String ERROR_SCOOTER_MOVING = "Trotinete em movimento.";
	private static final String ERROR_SCOOTER_NOT_INACTIVE = "Trotinete nao inactiva.";
	
	/* Constants Successes */
	private static final String SUCCESS_CLIENT_INSERTED = "Insercao de cliente com sucesso.";
	private static final String SUCCESS_CLIENT_REMOVED = "Cliente removido com sucesso.";
	private static final String SUCCESS_SCOOTER_INSERTED = "Insercao de trotinete com sucesso.";
	private static final String SUCCESS_BALANCE_LOADED = "Carregamento efectuado.";
	private static final String SUCCESS_RENT_INITIALIZED = "Aluguer efectuado com sucesso.";
	private static final String SUCCESS_RENT_TERMINATED = "Aluguer terminado.";
	private static final String SUCCESS_PROMOTION_APPLIED = "Promocao aplicada.";
	private static final String SUCCESS_SCOOTER_DEACTIVATED = "Trotinete desactivada.";
	private static final String SUCCESS_SCOOTER_REACTIVATED = "Trotinete reactivada.";
	private static final String SUCCESS_EXIT = "Saindo...";
	
	/* Constants Options */
	private static final String AD_CLIENT = "ADCLIENTE";
	private static final String REM_CLIENT = "REMCLIENTE";
	private static final String AD_SCOOTER = "ADTROT";
	private static final String CLIENT_DATA = "DADOSCLIENTE";
	private static final String SCOOTER = "TROT";
	private static final String SCOOTER_DATA = "DADOSTROT";
	private static final String CLIENT = "CLIENTE";
	private static final String INC_BALANCE = "CARRSALDO";
	private static final String RENT = "ALUGAR";
	private static final String RELEASE = "LIBERTAR";
	private static final String PROMOTION = "PROMOCAO";
	private static final String DIS_SCOOTER = "DESTROT";
	private static final String ACT_SCOOTER = "REACTROT";
	private static final String CURRENT_STATE = "ESTADOSISTEMA";
	private static final String EXIT = "SAIR";
	
	private static final int DOES_NOT_EXIST = -1;
	
	/**
	 * Creates and initializes the Scanner and Manager.
	 * Repeatedly reads inputs and interprets them until the command "SAIR" is typed.
	 */
	public static void main(String[] args) {
		Manager manager = new Manager();
		Scanner in = new Scanner(System.in);

		while (!readOption(in).equals(EXIT)) {
			
			executeOption(in, readOption(in), manager);
			
		}
		
		System.out.println(SUCCESS_EXIT);
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
		case AD_CLIENT:
			addClient(in, manager);
			break;
		case REM_CLIENT:
			removeClient(in, manager);
			break;
		case AD_SCOOTER:
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
		case INC_BALANCE:
			loadBalance(in, manager);
			break;
		case RENT:
			rentTrot(in, manager);
			break;
		case RELEASE:
			releaseTrot(in, manager);
			break;
		case CURRENT_STATE:
			managerStatus(in, manager);
			break;
		case PROMOTION:
			promotion(in, manager);
			break;
		case DIS_SCOOTER:
			deactivateTrot(in, manager);
			break;
		case ACT_SCOOTER:
			activateTrot(in, manager);
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
	 * @param in
	 * @param manager
	 */
	private static void addClient(Scanner in, Manager manager) {
		String nif = in.next();
		String email = in.next();
		String phone = in.next();
		String name = in.next() + in.nextLine();

		int position = manager.findClient(nif);
		
		if (position == DOES_NOT_EXIST) {
			manager.addClient(nif, email, phone, name);
			System.out.println(SUCCESS_CLIENT_INSERTED);
		} 
		else {
			System.out.println(ERROR_CLIENT_EXISTS);
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
		
		int position = manager.findClient(nif);
		
		if (position == DOES_NOT_EXIST) {
			System.out.println(ERROR_CLIENT_DOES_NOT_EXIST);
		}	
		else if (manager.clientHasTrot(position)) {
			System.out.println(ERROR_CLIENT_MOVING);
		}
		else {
			manager.removeClient(position);
			System.out.println(SUCCESS_CLIENT_REMOVED);
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
		
		int position = manager.findTrot(idTrot);
		
		if (position == DOES_NOT_EXIST) {
			manager.addTrot(idTrot, licensePlate);
			System.out.println(SUCCESS_SCOOTER_INSERTED);
		} 
		else {
			System.out.println(ERROR_SCOOTER_EXISTS);
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
		
		int position = manager.findClient(nif);
		
		if (position != DOES_NOT_EXIST) {
			System.out.println(manager.getClientName(position) + ": " + manager.getClientNif(position) + ", " + manager.getClientEmail(position) + ", "
					+ manager.getClientPhone(position) + ", " + manager.getClientBalance(position) + ", " + manager.getClientTotalMinutes(position) + ", "
					+ manager.getClientRents(position) + ", " + manager.getClientMaxMinutes(position) + ", " + manager.getClientAvgMinutes(position) + ", "
					+ manager.getClientTotalSpent(position));
		} 
		else {
			System.out.println(ERROR_CLIENT_DOES_NOT_EXIST);
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
		
		int position = manager.findClient(nif);

		if (position == DOES_NOT_EXIST) {
			System.out.println(ERROR_CLIENT_DOES_NOT_EXIST);
		}
		else if (!manager.clientHasTrot(position)) {
			System.out.println(ERROR_CLIENT_WITHOUT_SCOOTER);
		}
		else {
			System.out.println(manager.getClientIdTrot(position) + ", " + manager.getClientLicensePlate(position));
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
		
		int position = manager.findTrot(idTrot);
		
		if (position == DOES_NOT_EXIST) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		} 
		else {
			System.out.println(manager.getTrotLicensePlate(position) + ": " + manager.getTrotStatus(position) + ", " + manager.getTrotRents(position)
			+ ", " + manager.getTrotTotalMinutes(position));
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
		
		int position = manager.findTrot(idTrot);

		if (position == DOES_NOT_EXIST) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		}
		else if (!manager.trotHasClient(position)) {
			System.out.println(ERROR_SCOOTER_NOT_RENTED);
		}
		else {
			System.out.println(manager.getClientNif(position) + ", " + manager.getClientName(position));
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
		
		int position = manager.findClient(nif);
		
		if (amount <= 0) {
			System.out.println(ERROR_INVALID_AMOUNT);
		}
		else if (position == DOES_NOT_EXIST) {
			System.out.println(ERROR_CLIENT_DOES_NOT_EXIST);
		}
		else {
			manager.loadBalance(position, amount);
			System.out.println(SUCCESS_BALANCE_LOADED);
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
		
		int positionClient = manager.findClient(nif);
		int positionTrot = manager.findTrot(idTrot);
		
		if (positionClient == DOES_NOT_EXIST) {
			System.out.println(ERROR_CLIENT_DOES_NOT_EXIST);
		}
		else if (positionTrot == DOES_NOT_EXIST) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		}
		else if (!(manager.trotIsActivated(positionTrot) && manager.getTrotStatus(positionTrot).equals("parada"))) {
			System.out.println(ERROR_SCOOTER_CANNOT_BE_RENTED);
		}
		else if (manager.getClientBalance(positionClient) < 100) {
			System.out.println(ERROR_INSUFFICIENT_BALANCE);
		}
		else {
			manager.rentTrot(positionClient, positionTrot);
			System.out.println(SUCCESS_RENT_INITIALIZED);
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
		
		int position = manager.findTrot(idTrot);
		
		if (minutes <= 0) {
			System.out.println(ERROR_INVALID_AMOUNT);
		}
		else if (position == DOES_NOT_EXIST) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		}
		else if (!manager.getTrotStatus(position).equals("alugada")) {
			System.out.println(ERROR_SCOOTER_NOT_RENTED);
		}
		else {
			manager.releaseTrot(position, minutes);
			System.out.println(SUCCESS_RENT_TERMINATED);
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

		int position = manager.findClient(nif);
		
		if (position == DOES_NOT_EXIST) {
			System.out.println(ERROR_CLIENT_DOES_NOT_EXIST);
		}
		else if (!manager.getClientIdTrot(position).equals("")) {
			System.out.println(ERROR_CLIENT_INITIALIZED_NEW_RENT);
		}
		else if (manager.usedPromotion()) {
			System.out.println(ERROR_PROMOTION_ALREADY_APPLIED);
		}
		else {
			manager.applyPromotion(position);
			System.out.println(SUCCESS_PROMOTION_APPLIED);
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
		
		int position = manager.findTrot(idTrot);
		
		if (position == DOES_NOT_EXIST) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		}
		else if (manager.getTrotStatus(position).equals("alugada")) {
			System.out.println(ERROR_SCOOTER_MOVING);
		}
		else {
			manager.deactivateTrot(position);
			System.out.println(SUCCESS_SCOOTER_DEACTIVATED);
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
		
		int position = manager.findTrot(idTrot);

		if (position == DOES_NOT_EXIST) {
			System.out.println(ERROR_SCOOTER_DOES_NOT_EXIST);
		}
		else if (!manager.getTrotStatus(position).equals("inactiva")) {
			System.out.println(ERROR_SCOOTER_NOT_INACTIVE);
		}
		else {
			manager.activateTrot(position);
			System.out.println(SUCCESS_SCOOTER_REACTIVATED);
		}
	}
	
}
