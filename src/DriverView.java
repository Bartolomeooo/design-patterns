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
		Order order = orderController.getAssignedOrder(driverId);

		displayOrder(order);

		do {
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();

		}
		boolean isCarProblem = true;
		if (isCarProblem) reportProblem(order); // Two loop iterations 'cause we don't have any user input

		displayOrder(order);
		isCarProblem = false;
		if (isCarProblem) reportProblem(order);

		for (int i = 0; i < 4; i++) {
			orderController.reportProgress(order).display(order.getStatus());
		}
	}

	private void displayOrder(Order order) {
		System.out.println(order);
	}

	private void reportProblem(Order order) {
		orderController.assignSubstituteVehicle(order);
	}
}