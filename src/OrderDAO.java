import java.util.Map;

public class OrderDAO implements DAO<Order> {
	private final Map<Long, Order> orders = InMemoryDataStore.getInstance().getOrders();
	/**
	 *
	 * @param orderId
	 */
	public Order findById(Long orderId) {
		return orders.get(orderId);
	}

	/**
	 *
	 * @param order
	 */
	public void save(Order order) {
		orders.put(order.getOrderId(), order);
	}

	/**
	 *
	 * @param orderId
	 */
	public void delete(Long orderId) {
		orders.remove(orderId);
	}

	/**
	 * 
	 * @param driverId
	 */
	public Order findByDriverId(Long driverId) {
		return orders.values().stream()
				.filter(order -> driverId.equals(order.getDriverId()))
				.findFirst()
				.orElse(null);
	}

	public Order findFirstPendingOrder() {
		return orders.values().stream()
				.filter(order -> "Pending".equals(order.getStatus()))
				.findFirst()
				.orElse(null);
	}

}