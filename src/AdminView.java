import java.util.List;

public class AdminView implements IAdminView {

	private Long adminId;
	private IOrderController orderController;
	private List<NotificationListener> listeners;

	/**
	 * 
	 * @param adminId
	 */
	public AdminView(Long adminId) {
		// TODO - implement AdminView.AdminView
		throw new UnsupportedOperationException();
	}

	public void createOrder() {

	}

	private void modifyAutomaticalyAssignedProperties() {
		// TODO - implement AdminView.modifyAutomaticalyAssignedProperties
		throw new UnsupportedOperationException();
	}

	private void displayPendingOrder() {
		// TODO - implement AdminView.displayPendingOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param listener
	 */
	public void addNotificationListener(NotificationListener listener) {
		// TODO - implement AdminView.addNotificationListener
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param notification
	 */
	public void notifyListeners(Notification notification) {
		// TODO - implement AdminView.notifyListeners
		throw new UnsupportedOperationException();
	}

}