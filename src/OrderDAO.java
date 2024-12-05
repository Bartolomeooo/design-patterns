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
		// TODO - implement OrderDAO.findByDriverId
		throw new UnsupportedOperationException();
	}

	public Order findFirstPendingOrder() {
		// TODO - implement OrderDAO.findFirstPendingOrder
		throw new UnsupportedOperationException();
	}

}