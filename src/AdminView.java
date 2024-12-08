import java.util.List;

public class AdminView implements IAdminView {

	private Long adminId;
	private IOrderController orderController = new OrderController();
	private List<NotificationListener> listeners;

	/**
	 *
	 * @param adminId
	 */
	public AdminView(Long adminId) {
		this.adminId = adminId;
	}

	public void createOrder() {
		displayPendingOrder();
		Order order = orderController.getPendingOrder();

		boolean wantsToModifyAssignedProperties = true;
		if(wantsToModifyAssignedProperties) {
			modifyAutomaticalyAssignedProperties(order);
		}

		notifyListeners(orderController.createOrder(order), order);
	}

	private void modifyAutomaticalyAssignedProperties(Order order) {
		boolean wantsToModifyAssignedVehicle = true;
		if(wantsToModifyAssignedVehicle) {
			Long newVehicleId = 2L;
			orderController.assignVehicle(order, newVehicleId);
		}

		boolean wantsToModifyAssignedDriver = false;
		if(wantsToModifyAssignedDriver) {
			//Long newDriverId = 2L;
			//orderController.assignDriver(order, newDriverId);
		}
	}

	private void displayPendingOrder() {
		Order pendingOrder = orderController.getPendingOrder();
		if (pendingOrder != null) {
			System.out.println("Oczekujące zamówienie:");
			System.out.println(pendingOrder);
		} else {
			System.out.println("Brak oczekujących zamówień.");
		}
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
