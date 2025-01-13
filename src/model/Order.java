package model;

public class Order {

	private Long orderId;
	private Client client;
	private OrderDetails orderDetails;
	private Driver driver;
	private Vehicle vehicle;
	private Route route;
	private String status;

	/**
	 * 
	 * @param orderDetails
	 */
	public Order(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setOrderId(Long orderId) {
		if (orderId == null) {
			throw new IllegalArgumentException("OrderId cannot be null");
		}

		this.orderId = orderId;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Long getOrderId() {
		return orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getDriverId() {
		return driver.getDriverId();
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	@Override
	public String toString() {
		return "Order{" +
				"orderId=" + orderId +
				", client=" + (client != null ? client.getClientId() : "null") +
				", orderDetails=" + (orderDetails != null ? orderDetails.toString() : "null") +
				", driver=" + (driver != null ? driver.getDriverId() : "null") +
				", vehicle=" + (vehicle != null ? vehicle.getVehicleId() : "null") +
				", route=" + (route != null ? route.getDescription(): "null") +
				", status='" + status + '\'' +
				'}';
	}

	public Driver getDriver() {
		return driver;
	}
}