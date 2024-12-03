import java.time.LocalDateTime;

public class OrderDetails {

	private String pickupLocation;
	private String deliveryLocation;
	private String cargoType;
	private LocalDateTime pickupDateTime;
	private LocalDateTime deliveryDateTime;
	private Double cargoWeight;
	private Double cost;

	public boolean areValid() {
		// TODO - implement OrderDetails.areValid
		throw new UnsupportedOperationException();
	}

	public Double calculateCost() {
		// TODO - implement OrderDetails.calculateCost
		throw new UnsupportedOperationException();
	}

}