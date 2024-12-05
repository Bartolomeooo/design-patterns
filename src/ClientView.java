import javax.management.Notification;
import java.time.LocalDateTime;

public class ClientView implements IClientView, NotificationListener {

	private Long clientId;
	private IOrderController orderController = new OrderController();;

	public void initializeOrder() {
		orderController.initializeOrder(clientId, enterOrderDetails());
	}

	private OrderDetails enterOrderDetails() {
		LocalDateTime pickupTime = LocalDateTime.of(2025, 5, 3, 15, 0);
		LocalDateTime delvieryTime = LocalDateTime.of(2025, 5, 3, 12, 0);

        return new OrderDetails("Opole", "Wrocław", "Liquid", pickupTime, delvieryTime, 1000.0);
	}

	private boolean verifyOrderDetails() {
		return true; // TODO - implement the logic
	}

	private void displayError() {
		// TODO - implement ClientView.displayError
		throw new UnsupportedOperationException();
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
	 */
	@Override
	public void notify(Notification notification) {

	}


}