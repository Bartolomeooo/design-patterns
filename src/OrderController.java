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
	 * @param orderId
	 */
	public void cancelOrder(Long orderId) {
		// TODO - implement OrderController.cancelOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param driverId
	 */
	public Order getAssignedOrder(Long driverId) {
		// TODO - implement OrderController.getAssignedOrder
		throw new UnsupportedOperationException();
	}

	public Notification reportProgress() {
		// TODO - implement OrderController.reportProgress
		throw new UnsupportedOperationException();
	}

	@Override
	public void assignVehicle(Order order, Long vehicleId) {
		orderService.assignVehicle(order, vehicleId);
	}
}