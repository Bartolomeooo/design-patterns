package model;

import java.util.Map;
import java.util.NoSuchElementException;

public class VehicleDAO implements DAO<Vehicle> {
	private final Map<Long, Vehicle> vehicles = InMemoryDataStore.getInstance().getVehicles();
	/**
	 * 
	 * @param vehicleId
	 */
	public Vehicle findById(Long vehicleId) {
		if (!vehicles.containsKey(vehicleId)) {
			throw new NoSuchElementException("Vehicle with ID " + vehicleId + " not found.");
		}

		return vehicles.get(vehicleId);
	}

	/**
	 * 
	 * @param vehicle
	 */
	public void save(Vehicle vehicle) {
		if (vehicle == null) {
			throw new IllegalArgumentException("Cannot save null vehicle!");
		}

		vehicles.put(vehicle.getVehicleId(), vehicle);
	}

	/**
	 * 
	 * @param vehicleId
	 */
	public void delete(Long vehicleId) {
		if (!vehicles.containsKey(vehicleId)) {
			throw new NoSuchElementException("Vehicle with ID " + vehicleId + " not found, cannot delete.");
		}

		vehicles.remove(vehicleId);
	}

	public Vehicle findAvailable() {
		return vehicles.values().stream()
				.filter(Vehicle::isAvailable)
				.findFirst()
				.orElse(null);
	}
}