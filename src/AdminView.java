import java.util.ArrayList;
import java.util.List;

public class AdminView implements IAdminView {

	private Long adminId;
	private IOrderController orderController = new OrderController();
	private List<NotificationListener> listeners = new ArrayList<>();

	/**
	 *
	 * @param adminId
	 */
	public AdminView(Long adminId) {
		this.adminId = adminId;
	}

	public void createOrder() {
		System.out.println("Admin's view: ");
		displayPendingOrder();
		Order order = orderController.getPendingOrder();

		boolean wantsToCancelOrderBasedOnOrderDetails = false;
		if (wantsToCancelOrderBasedOnOrderDetails) {
			orderController.cancelOrder(order);
		}

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
			orderController.replaceVehicle(order, newVehicleId);
		}

		boolean wantsToModifyAssignedDriver = false;
		if(wantsToModifyAssignedDriver) {
			Long newDriverId = 2L;
			orderController.replaceDriver(order, newDriverId);
		}
	}

	private void displayPendingOrder() {
		Order pendingOrder = orderController.getPendingOrder();
		if (pendingOrder != null) {
			System.out.println("Pending order:");
			System.out.println(pendingOrder);
		} else {
			System.out.println("No pending orders.");
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
