package model;

import java.util.Map;

public class VehicleDAO implements DAO<Vehicle> {
	private final Map<Long, Vehicle> vehicles = InMemoryDataStore.getInstance().getVehicles();
	/**
	 * 
	 * @param vehicleId
	 */
	public Vehicle findById(Long vehicleId) {
		return vehicles.get(vehicleId);
	}

	/**
	 * 
	 * @param vehicle
	 */
	public void save(Vehicle vehicle) {
		vehicles.put(vehicle.getVehicleId(), vehicle);
	}

	/**
	 * 
	 * @param vehicleId
	 */
	public void delete(Long vehicleId) {
		vehicles.remove(vehicleId);
	}

	public Vehicle findAvailable() {
		return vehicles.values().stream()
				.filter(Vehicle::isAvailable)
				.findFirst()
				.orElse(null);
	}
}