import java.util.Scanner;

public class DriverView implements IDriverView {

	private Long driverId;
	private IOrderController orderController = new OrderController();

	/**
	 *
	 * @param driverId
	 */
	public DriverView(Long driverId) {
		this.driverId = driverId;
	}

	public void reportProgress() {
		System.out.println("Driver's view: ");
		Scanner scanner = new Scanner(System.in);

		Order order = orderController.getAssignedOrder(driverId);

		while (true) {
			displayOrder(order);

			System.out.println("Czy chcesz zgłosić problem z samochodem? (tak/nie):");
			String userInput = scanner.nextLine().trim().toLowerCase();

			if ("tak".equals(userInput)) {
				reportProblem(order);
				System.out.println("Problem zgłoszony.");
			} else if ("nie".equals(userInput)) {
				System.out.println("Brak problemów z samochodem. Przechodzimy dalej.");
				break;
			} else {
				System.out.println("Nieprawidłowa odpowiedź. Wpisz 'tak' lub 'nie'.");
			}
		}

		for (int i = 0; i < 4; i++) {
			orderController.reportProgress(order).display(order.getStatus());
		}

		scanner.close();
	}

	private void displayOrder(Order order) {
		System.out.println(order);
	}

	private void reportProblem(Order order) {
		orderController.assignSubstituteVehicle(order);
	}
}