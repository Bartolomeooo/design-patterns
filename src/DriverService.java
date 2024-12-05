public class DriverService {

	private final DAO<Driver> dao = new DriverDAO();

	public Driver findAvailable() {
		return ((DriverDAO) dao).findAvailable();
	}

}