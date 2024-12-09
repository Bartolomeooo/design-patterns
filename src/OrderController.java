import java.util.List;

public class OrderController implements IOrderController {

	private final OrderService orderService = new OrderService();

	/**
	 * 
	 * @param clientId
	 * @param orderDetails
	 */
	public void initializeOrder(Long clientId, OrderDetails orderDetails) {
		orderService.initializeOrder(clientId, orderDetails);
	}

	public Notification createOrder(Order order) {
		return orderService.createOrder(order);
	}

	public Order getPendingOrder() {
		return orderService.getPendingOrder();
	}

	/**
	 * 
	 * @param driverId
	 * @param vehicleId
	 * @param routeDescription
	 */
	public void modifyAutomaticalyAssignedProperties(Long driverId, Long vehicleId, Long routeDescription) {
		// TODO - implement OrderController.modifyAutomaticalyAssignedProperties
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param order
	 */
	public void cancelOrder(Order order) {
		orderService.cancelOrder(order);
	}

	/**
	 * 
	 * @param driverId
	 */
	public Order getAssignedOrder(Long driverId) {
		return orderService.getAssignedOrder(driverId);
	}

	public Notification reportProgress(Order order) {
		return orderService.reportProgress(order);
	}

	/**
	 *
	 * @param order
	 */
	public void assignSubstituteVehicle(Order order) {
		orderService.assignVehicle(order);
	}

	@Override
	public void assignVehicle(Order order, Long vehicleId) {
		orderService.assignVehicle(order, vehicleId);
	}
}