package presenter;

import model.Order;
import model.OrderDetails;

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

	/**
	 *
	 * @param order
	 */
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

	/**
	 *
	 * @param order
	 * @param vehicleId
	 */
	@Override
	public void replaceVehicle(Order order, Long vehicleId) {
		orderService.replaceVehicle(order, vehicleId);
	}

	/**
	 *
	 * @param order
	 * @param driverId
	 */
	@Override
	public void replaceDriver(Order order, Long driverId) {
		orderService.replaceDriver(order, driverId);
	}
}