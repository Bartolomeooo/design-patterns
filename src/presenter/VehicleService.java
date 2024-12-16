package presenter;

import model.DAO;
import model.Vehicle;
import model.VehicleDAO;

public class VehicleService {

	private DAO<Vehicle> dao = new VehicleDAO();

	public Vehicle findAvailable() {
		return ((VehicleDAO)dao).findAvailable();
	}

	public Vehicle findById(Long vehicleId) {
		return  dao.findById(vehicleId);
	}

}