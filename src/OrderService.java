import java.time.Duration;
import java.time.LocalDateTime;

public class OrderService {

	private RoutingStrategy routingStrategy;
	private final ClientService clientService = new ClientService();
	private final DriverService driverService = new DriverService();
	private final VehicleService vehicleService = new VehicleService();
	private final DAO<Order> dao = new OrderDAO();
	private NotificationCreator notificationCreator;

	/**
	 *
	 * @param clientId
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
		Driver driver = driverService.findAvailable();
		driver.setAvailable(false);
		order.setDriver(driver);
	}

	/**
	 *
	 * @param order
	 * @param driverId
	 */
	public void replaceDriver(Order order, Long driverId) {
		Driver driver = driverService.findById(driverId);
		order.getDriver().setAvailable(true); // Current driver
		order.setDriver(driver);
		driver.setAvailable(false); // New assigned driver
	}

	/**
	 *
	 * @param order
	 */
	public void assignVehicle(Order order) {
		Vehicle vehicle = vehicleService.findAvailable();
		order.setVehicle(vehicle);
		vehicle.setAvailable(false);
	}

	/**
	 *
	 * @param order
	 * @param vehicleId
	 */
	public void replaceVehicle(Order order, Long vehicleId) {
		Vehicle vehicle = vehicleService.findById(vehicleId);
		order.getVehicle().setAvailable(true); // Current vehicle
		order.setVehicle(vehicle);
		vehicle.setAvailable(false); // New assigned vehicle
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
		OrderDetails orderDetails = order.getOrderDetails();
		LocalDateTime pickupTime = orderDetails.getPickupDateTime();
		LocalDateTime deliveryTime = orderDetails.getDeliveryDateTime();
		Duration timeToDelivery = Duration.between(pickupTime, deliveryTime);

		routingStrategy = timeToDelivery.toHours() < 3 ?
				new FastestRouteStrategy() :
				new ShortestDistanceStrategy();
	}

	public Notification createOrder(Order order) {
		order.setStatus("Active");
		notificationCreator = new OrderCreatedNotificationCreator();

		return notificationCreator.createNotification();
	}

	public Order getPendingOrder() {
		return ((OrderDAO) dao).findFirstPendingOrder();
	}

	/**
	 * 
	 * @param order
	 */
	public void cancelOrder(Order order) {
		order.setStatus("Canceled");
	}

	/**
	 * 
	 * @param driverId
	 */
	public Order getAssignedOrder(Long driverId) {
		return ((OrderDAO) dao).findByDriverId(driverId, "Active");
	}

	public Notification reportProgress(Order order) {
		notificationCreator = new OrderProgressNotificationCreator();

		String currentStatus = order.getStatus();

		switch (currentStatus) {
			case "Active":
				order.setStatus("Execution Started");
				break;

			case "Execution Started":
				order.setStatus("Conformity confirmed");
				break;

			case "Conformity confirmed":
				order.setStatus("Shipment Started");
				break;

			case "Shipment Started":
				order.setStatus("Order Completed");
				order.getVehicle().setAvailable(true);
				order.getDriver().setAvailable(true);
				break;
		}

		return notificationCreator.createNotification();
	}
}