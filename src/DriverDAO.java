import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DriverDAO implements DAO<Driver> {
	private final Map<Long, Driver> drivers = InMemoryDataStore.getInstance().getDrivers();
	/**
	 * 
	 * @param driverId
	 */
	public Driver findById(Long driverId) {
		return drivers.get(driverId);
	}

	/**
	 * 
	 * @param driver
	 */
	public void save(Driver driver) {
		drivers.put(driver.getDriverId(), driver);
	}

	/**
	 * 
	 * @param driverId
	 */
	public void delete(Long driverId) {
		drivers.remove(driverId);
	}

	public Driver findAvailable() {
		return drivers.values().stream()
				.filter(Driver::isAvailable)
				.findFirst()
				.orElse(null);
	}
}