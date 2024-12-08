import java.util.List;

public class OrderService {

	private RoutingStrategy routingStrategy;
	private final ClientService clientService = new ClientService();
	private final DriverService driverService = new DriverService();
	private final VehicleService vehicleService = new VehicleService();
	private DAO<Order> dao = new OrderDAO();
	private NotificationCreator notificationCreator;

	/**
	 * 
	 * @param orderDetails
	 */
	public void initializeOrder(Long clientId, OrderDetails orderDetails) {
		Order order = new Order(orderDetails);
		assignDriver(order);
		assignVehicle(order);
		setStrategy(order);
		setRoute(order);
		order.setClient(clientService.findById(clientId));
		order.setOrderId(1L);
		order.setStatus("Pending");
		dao.save(order);
	}

	/**
	 *
	 * @param order
	 */
	private void assignDriver(Order order) {
		order.setDriver(driverService.findAvailable());
	}

	/**
	 *
	 * @param order
	 * @param driverId
	 */
	private void assignDriver(Order order, Long driverId) {
		order.setDriver(driverService.findById(driverId));
	}

	/**
	 *
	 * @param order
	 */
	private void assignVehicle(Order order) {
		order.setVehicle(vehicleService.findAvailable());
	}

	/**
	 *
	 * @param order
	 */
	public void assignVehicle(Order order, Long vehicleId) {
		order.setVehicle(vehicleService.findById(vehicleId));
	}

	/**
	 *
	 * @param order
	 */
	private void setRoute(Order order) {
		order.setRoute(routingStrategy.calculateRoute());
	}

	/**
	 *
	 * @param order
	 */
	private void setStrategy(Order order) {
		routingStrategy = new FastestRouteStrategy(); // TODO - implement logic
	}

	public Notification createOrder(Order order) {
		order.setStatus("Active");
		notificationCreator = new OrderProgressNotificationCreator();

		return new OrderCreatedNotification();
	}

	public Order getPendingOrder() {
		return ((OrderDAO) dao).findFirstPendingOrder();
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