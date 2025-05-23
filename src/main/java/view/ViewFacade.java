package org.example.view;

public class ViewFacade {

	private IClientView clientView;
	private IDriverView driverView;
	private IAdminView adminView;

	/**
	 * 
	 * @param clientId
	 * @param driverId
	 * @param adminId
	 */
	public ViewFacade(Long clientId, Long driverId, Long adminId) {
		clientView = new ClientView(clientId);
		driverView = new DriverView(driverId);
		adminView = new AdminView(adminId);
		((AdminView) adminView).addNotificationListener((NotificationListener) clientView);
		((DriverView) driverView).addNotificationListener((NotificationListener) clientView);
	}

	public void initializeOrder() {
		clientView.initializeOrder();
		adminView.createOrder();
	}

	public void reportProgress() {
		driverView.reportProgress();
	}

}