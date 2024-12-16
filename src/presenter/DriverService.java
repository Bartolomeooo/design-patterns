package presenter;

import model.DAO;
import model.Driver;
import model.DriverDAO;

public class DriverService {

	private final DAO<Driver> dao = new DriverDAO();

	public Driver findAvailable() {
		return ((DriverDAO) dao).findAvailable();
	}

	/**
	 *
	 * @param driverId
	 */
	public Driver findById(Long driverId) {
		return dao.findById(driverId);
	}
}