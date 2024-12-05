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

	public Notification createOrder() {
		// TODO - implement OrderController.createOrder
		throw new UnsupportedOperationException();
	}

	public Order getPendingOrder() {
		// TODO - implement OrderController.getPendingOrder
		throw new UnsupportedOperationException();
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

}