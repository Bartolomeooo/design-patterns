public class OrderService {

	private RoutingStrategy routingStrategy;
	private ClientService clientService;
	private DriverService driverService;
	private VehicleService vehicleService;
	private DAO<Order> dao;
	private NotificationCreator notificationCreator;

	/**
	 * 
	 * @param orderDetails
	 */
	public void initializeOrder(OrderDetails orderDetails) {
		// TODO - implement OrderService.initializeOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param order
	 */
	private void assignDriver(Order order) {
		// TODO - implement OrderService.assignDriver
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param order
	 */
	private void assignVehicle(Order order) {
		// TODO - implement OrderService.assignVehicle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param order
	 */
	private void setStrategy(Order order) {
		// TODO - implement OrderService.setStrategy
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param order
	 */
	private void setRoute(Order order) {
		// TODO - implement OrderService.setRoute
		throw new UnsupportedOperationException();
	}

	public Notification createOrder() {
		// TODO - implement OrderService.createOrder
		throw new UnsupportedOperationException();
	}

	public Order getPendingOrder() {
		// TODO - implement OrderService.getPendingOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param driverId
	 * @param vehicleId
	 * @param routeDescription
	 */
	public void modifyAutomaticalyAssignedProperties(Long driverId, Long vehicleId, Long routeDescription) {
		// TODO - implement OrderService.modifyAutomaticalyAssignedProperties
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderId
	 */
	public void cancelOrder(Long orderId) {
		// TODO - implement OrderService.cancelOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param driverId
	 */
	public Order getAssignedOrder(Long driverId) {
		// TODO - implement OrderService.getAssignedOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderId
	 */
	public void assignSubstituteVehicle(Long orderId) {
		// TODO - implement OrderService.assignSubstituteVehicle
		throw new UnsupportedOperationException();
	}

	public Notification reportProgress() {
		// TODO - implement OrderService.reportProgress
		throw new UnsupportedOperationException();
	}

}