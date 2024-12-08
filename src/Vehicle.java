public class Vehicle {

	private Long vehicleId;
	private boolean isAvailable;

	public Vehicle(Long vehicleId, boolean isAvailable) {
		this.vehicleId = vehicleId;
		this.isAvailable = isAvailable;
	}
	public Long getVehicleId() {
		return vehicleId;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public Long getId() {
		return vehicleId;
	}
}