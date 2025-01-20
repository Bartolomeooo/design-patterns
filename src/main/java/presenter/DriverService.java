package presenter;

import model.DAO;
import model.Driver;
import model.DriverDAO;

public class DriverService {

	private DAO<Driver> dao = new DriverDAO();

	public DriverService() {}

	public DriverService(DriverDAO mockDriverDAO) {
		dao = mockDriverDAO;
	}

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

	public void setDao(DAO<Driver> dao) {
		this.dao = dao;
	}

	public String getDao() {
		return dao.toString();
	}
}