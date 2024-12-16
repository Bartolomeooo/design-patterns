import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ClientView implements IClientView, NotificationListener {

	private final Long clientId;
	private final IOrderController orderController = new OrderController();

	public void initializeOrder() {
		System.out.println("Client's view: ");
		orderController.initializeOrder(clientId, enterOrderDetails());
	}

	private OrderDetails enterOrderDetails() {
		OrderDetails orderDetails;

		do {
			LocalDateTime pickupTime = LocalDateTime.of(2024, 12, 20, 15, 0);
			LocalDateTime deliveryTime = LocalDateTime.of(2024, 12, 20, 18, 0);

			orderDetails = new OrderDetails("Opole", "Wrocław", "Liquid", pickupTime, deliveryTime, 1000.0);
		} while (!isValid(orderDetails));

		return orderDetails;
	}

	/**
	 *
	 * @param orderDetails
	 */
	private boolean isValid(OrderDetails orderDetails) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime maxAllowedDate = now.plus(6, ChronoUnit.MONTHS);

		LocalDateTime pickupTime = orderDetails.getPickupDateTime();
		LocalDateTime deliveryTime = orderDetails.getDeliveryDateTime();

		if (pickupTime.isAfter(maxAllowedDate) || deliveryTime.isAfter(maxAllowedDate)) {
			System.out.println("Pickup or delivery time exceeds the allowed date range.");
			return false;
		}

		if (!pickupTime.isBefore(deliveryTime)) {
			System.out.println("Pickup time must be before delivery time.");
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param clientId
	 */
	public ClientView(Long clientId) {
		this.clientId = clientId;
	}

	/**
	 *
	 * @param notification
	 * @param order
	 */
	@Override
	public void notify(Notification notification, Order order) {
		notification.display(order.getStatus());
	}


}