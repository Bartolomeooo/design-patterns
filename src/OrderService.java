import java.time.Duration;
import java.time.LocalDateTime;
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
	public void assignVehicle(Order order) {
		Vehicle vehicle = vehicleService.findAvailable();
		order.setVehicle(vehicle);
		vehicle.setAvailable(false);
	}

	/**
	 *
	 * @param order
	 */
	public void assignVehicle(Order order, Long vehicleId) {
		Vehicle vehicle = vehicleService.findById(vehicleId);
		order.getVehicle().setAvailable(true); // Previous vehicle
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
		LocalDateTime pickupTime = order.getOrderDetails().getDeliveryDateTime();
		LocalDateTime deliveryTime = order.getOrderDetails().getDeliveryDateTime();
		Duration timeToDelivery = Duration.between(pickupTime, deliveryTime);

		if (timeToDelivery.toHours() < 3) routingStrategy = new FastestRouteStrategy();
		else {
			routingStrategy = new ShortestDistanceStrategy();
		}
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
	 * @param order
	 */
	public void cancelOrder(Order order) {
		dao.delete(order.getOrderId());
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
				order.setStatus("Shipment Confirmed");
				break;

			case "Shipment Confirmed":
				order.setStatus("Shipment Received");
				break;

			case "Shipment Received":
				order.setStatus("Order Completed");
				break;

			default:
				System.out.println("Order already completed");
				order.getVehicle().setAvailable(true);
				return null;
		}

		return notificationCreator.createNotification();
	}

}