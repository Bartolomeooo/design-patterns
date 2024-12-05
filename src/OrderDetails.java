import java.time.LocalDateTime;

public class OrderDetails {

	private String pickupLocation;
	private String deliveryLocation;
	private String cargoType;
	private LocalDateTime pickupDateTime;
	private LocalDateTime deliveryDateTime;
	private Double cargoWeight;
	private Double cost;

	public OrderDetails(String pickupLocation, String deliveryLocation, String cargoType, LocalDateTime pickupDateTime, LocalDateTime deliveryDateTime, Double cargoWeight) {
		this.pickupLocation = pickupLocation;
		this.deliveryLocation = deliveryLocation;
		this.cargoType = cargoType;
		this.pickupDateTime = pickupDateTime;
		this.deliveryDateTime = deliveryDateTime;
		this.cargoWeight = cargoWeight;
		this.cost = cargoWeight * 10.0;
	}

	public boolean areValid() {
		// TODO - implement OrderDetails.areValid
		throw new UnsupportedOperationException();
	}

	public Double calculateCost() {
		return cargoWeight * cost; // to be deleted
	}

}