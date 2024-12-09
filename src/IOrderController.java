import java.util.List;

public interface IOrderController {

	/**
	 * 
	 * @param clientId
	 * @param orderDetails
	 */
	void initializeOrder(Long clientId, OrderDetails orderDetails);

	Notification createOrder(Order order);

	Order getPendingOrder();

	/**
	 * 
	 * @param driverId
	 * @param vehicleId
	 * @param routeDescription
	 */
	void modifyAutomaticalyAssignedProperties(Long driverId, Long vehicleId, Long routeDescription);

	/**
	 * 
	 * @param order
	 */
	void cancelOrder(Order order);

	/**
	 * 
	 * @param driverId
	 */
	Order getAssignedOrder(Long driverId);

	Notification reportProgress(Order order);

	void assignVehicle(Order order, Long vehicleId);

	void assignSubstituteVehicle(Order order);
}