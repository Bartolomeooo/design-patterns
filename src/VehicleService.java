public class VehicleService {

	private DAO<Vehicle> dao = new VehicleDAO();

	public Vehicle findAvailable() {
		return ((VehicleDAO)dao).findAvailable();
	}

}