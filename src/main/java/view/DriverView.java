package org.example.view;

import model.Order;
import presenter.IOrderController;
import presenter.Notification;
import presenter.OrderController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverView implements IDriverView {

	private Long driverId;
	private IOrderController orderController = new OrderController();
	private List<NotificationListener> listeners = new ArrayList<>();

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

			System.out.println("Do you want to report a problem with the car? (yes/no):");
			String userInput = scanner.nextLine().trim().toLowerCase();

			if ("yes".equals(userInput)) {
				reportProblem(order);
				System.out.println("Problem reported.");
			} else if ("no".equals(userInput)) {
				System.out.println("No problems with the car. Proceeding.");
				break;
			} else {
				System.out.println("Invalid response. Please type 'yes' or 'no'.");
			}
		}

		for (int i = 0; i < 2; i++) {
			orderController.reportProgress(order);
		}
		for (int i = 0; i < 2; i++) {
			notifyListeners(orderController.reportProgress(order), order);
		}

		scanner.close();
	}

	private void displayOrder(Order order) {
		System.out.println(order);
	}

	private void reportProblem(Order order) {
		orderController.assignSubstituteVehicle(order);
	}

	/**
	 *
	 * @param listener
	 */
	public void addNotificationListener(NotificationListener listener) {
		listeners.add(listener);
	}

	/**
	 *
	 * @param notification
	 */
	public void notifyListeners(Notification notification, Order order) {
		for (NotificationListener listener : listeners) {
			listener.notify(notification, order);
		}
	}
}