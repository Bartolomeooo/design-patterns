public class Driver {

	private Long driverId;
	private boolean isAvailable;
	private String name;

	public Driver(Long driverId, boolean isAvailable, String name) {
		this.driverId = driverId;
		this.isAvailable = isAvailable;
		this.name = name;
	}

	public Long getDriverId() {
		return driverId;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public Long getId() {
		return driverId;
	}

	public String getName() {
		return name;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
}